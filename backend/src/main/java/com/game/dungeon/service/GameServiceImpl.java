package com.game.dungeon.service;

import com.game.dungeon.exception.InvalidGameStateException;
import com.game.dungeon.exception.ResourceNotFoundException;
import com.game.dungeon.factory.ItemFactory;
import com.game.dungeon.model.*;
import com.game.dungeon.model.Game.Position;
import com.game.dungeon.observer.GameStatusChangeEvent;
import com.game.dungeon.repository.GameRepository;
import com.game.dungeon.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final QuestionRepository questionRepository;
    private final ItemFactory itemFactory;
    private final ApplicationEventPublisher eventPublisher;
    private final Random random = new Random();

    @Override
    public Game createGame(String playerName, Game.GameLevel level) {
        Game game = new Game();
        game.setPlayerName(playerName);
        game.setLevel(level);
        game.setPowerPoints(level.getInitialPowerPoints());
        game.setMap(generateMap(game));
        game.setInventory(new Inventory());
        game.setStatus(Game.GameStatus.IN_PROGRESS);
        game.setStartedAt(Instant.now());
        game.setScore(0);
        game.setUsedQuestionIds(new ArrayList<>());
        game.setCurrentPosition(findStartingPosition(game.getMap()));

        return gameRepository.save(game);
    }

    @Override
    public Game getGame(String gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Game", gameId));
    }

    @Override
    public Game movePlayer(String gameId, Direction direction) {
        Game game = getGame(gameId);
        if (game.getStatus() != Game.GameStatus.IN_PROGRESS) {
            throw new InvalidGameStateException("Game is not in progress");
        }

        if (game.getCurrentQuestion() != null) {
            throw new InvalidGameStateException("You must answer the current question before moving");
        }

        Position newPosition = calculateNewPosition(game.getCurrentPosition(), direction);
        validateMove(game, newPosition);

        game.setPowerPoints(game.getPowerPoints() - game.getLevel().getMoveCost());
        if (game.getPowerPoints() <= 0) {
            handleGameOver(game, Game.GameStatus.LOST);
            return gameRepository.save(game);
        }

        Cell currentCell = findCell(game.getMap(), newPosition);
        game.setCurrentPosition(newPosition);
        currentCell.setExplored(true);

        if (currentCell.getItem() != null) {
            handleItemInteraction(game, currentCell);
        }

        if (currentCell.getType() == Cell.CellType.EXIT) {
            if (game.canWin()) {
                game.setStatus(Game.GameStatus.LEVEL_COMPLETE);
                game.updateScore();
            } else {
                int requiredQuestions = switch (game.getLevel()) {
                    case EASY -> 1;
                    case MEDIUM -> 2;
                    case HARD -> 3;
                };
                throw new InvalidGameStateException(
                        "You need to answer at least " + requiredQuestions + " questions correctly before exiting!");
            }
            return game;
        }

        return gameRepository.save(game);
    }

    @Override
    public Game answerQuestion(String gameId, String questionId, String answer) {
        Game game = getGame(gameId);
        if (game.getStatus() != Game.GameStatus.IN_PROGRESS) {
            throw new InvalidGameStateException("Game is not in progress");
        }

        Question question = game.getCurrentQuestion();
        if (question == null || !question.getId().equals(questionId)) {
            throw new InvalidGameStateException("No active question or question ID mismatch");
        }

        boolean isCorrect = answer.equals(question.getCorrectAnswer());
        game.getInventory().getAnsweredQuestions().add(
                new Inventory.AnsweredQuestion(questionId, answer, isCorrect));

        if (isCorrect) {
            int reward = switch (game.getLevel()) {
                case EASY -> 10;
                case MEDIUM -> 15;
                case HARD -> 20;
            };
            game.setPowerPoints(game.getPowerPoints() + reward);
            game.setCorrectAnswers(game.getCorrectAnswers() + 1);
            game.updateScore();
        } else {
            int penalty = switch (game.getLevel()) {
                case EASY -> 5;
                case MEDIUM -> 10;
                case HARD -> 15;
            };
            game.setPowerPoints(game.getPowerPoints() - penalty);
            game.updateScore();

            if (game.getPowerPoints() <= 0) {
                handleGameOver(game, Game.GameStatus.LOST);
                return gameRepository.save(game);
            }
        }

        game.setCurrentQuestion(null);
        game.setQuestionsAnswered(game.getQuestionsAnswered() + 1);
        Cell currentCell = findCell(game.getMap(), game.getCurrentPosition());
        currentCell.setItem(null);

        return gameRepository.save(game);
    }

    @Override
    public Game progressToNextLevel(String gameId) {
        Game game = getGame(gameId);

        if (game.getStatus() != Game.GameStatus.LEVEL_COMPLETE) {
            throw new InvalidGameStateException("Cannot progress to next level - current level not completed!");
        }

        switch (game.getLevel()) {
            case EASY -> game.setLevel(Game.GameLevel.MEDIUM);
            case MEDIUM -> game.setLevel(Game.GameLevel.HARD);
            case HARD -> {
                game.setStatus(Game.GameStatus.WON);
                handleGameOver(game, Game.GameStatus.WON);
                return game;
            }
        }

        GameMap newMap = generateMap(game);
        game.setMap(newMap);
        game.setStatus(Game.GameStatus.IN_PROGRESS);
        game.setPowerPoints(game.getLevel().getInitialPowerPoints());
        game.setCurrentPosition(findStartingPosition(newMap));

        return gameRepository.save(game);
    }

    private Position findStartingPosition(GameMap map) {
        List<Cell> cells = map.getCells();
        for (int y = 0; y < map.getSize(); y++) {
            for (int x = 0; x < map.getSize(); x++) {
                if (cells.get(y * map.getSize() + x).getType() == Cell.CellType.ENTRANCE) {
                    return new Position(y, x);
                }
            }
        }
        throw new InvalidGameStateException("No entrance found in generated map!");
    }

    private GameMap generateMap(Game game) {
        int size = game.getLevel().getMapSize();
        GameMap map = new GameMap();
        map.setSize(size);
        map.setCells(new ArrayList<>());

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Cell cell = new Cell();
                cell.setX(x);
                cell.setY(y);
                cell.setType(Cell.CellType.EMPTY);
                cell.setExplored(false);
                map.getCells().add(cell);
            }
        }

        Position entrance = new Position(random.nextInt(size), 0);
        Position exit;
        do {
            exit = new Position(random.nextInt(size), size - 1);
        } while (exit.getX() == entrance.getX());

        findCell(map, entrance).setType(Cell.CellType.ENTRANCE);
        findCell(map, entrance).setExplored(true);
        findCell(map, exit).setType(Cell.CellType.EXIT);

        int minTeachers = game.getLevel().getMinQuestions();
        int teachersPlaced = 0;
        int maxAttempts = size * size;
        int attempts = 0;

        while (teachersPlaced < minTeachers && attempts < maxAttempts) {
            int x = random.nextInt(size);
            int y = 1 + random.nextInt(size - 2);

            Cell cell = findCell(map, new Position(x, y));
            if (cell.getType() == Cell.CellType.EMPTY && cell.getItem() == null) {
                cell.setItem(itemFactory.createItem(game.getLevel().toString(), game.getUsedQuestionIds(), false));
                cell.setType(Cell.CellType.TEACHER);
                teachersPlaced++;
            }
            attempts++;
        }

        if (teachersPlaced < minTeachers) {
            throw new InvalidGameStateException("Could not place minimum required questions on the map");
        }

        int numFriends = size / 2;
        for (int i = 0; i < numFriends; i++) {
            attempts = 0;
            while (attempts < 10) {
                int x = random.nextInt(size);
                int y = random.nextInt(size);
                Cell cell = findCell(map, new Position(x, y));

                if (cell.getType() == Cell.CellType.EMPTY && cell.getItem() == null) {
                    cell.setItem(itemFactory.createItem(game.getLevel().toString(), game.getUsedQuestionIds(), true));
                    cell.setType(Cell.CellType.FRIEND);
                    break;
                }
                attempts++;
            }
        }

        return map;
    }

    private Position calculateNewPosition(Position current, Direction direction) {
        int newX = current.getX();
        int newY = current.getY();

        switch (direction) {
            case UP -> newX--;
            case DOWN -> newX++;
            case LEFT -> newY--;
            case RIGHT -> newY++;
        }

        return new Position(newX, newY);
    }

    private void validateMove(Game game, Position newPosition) {
        if (newPosition.getX() < 0 || newPosition.getX() >= game.getMap().getSize() ||
                newPosition.getY() < 0 || newPosition.getY() >= game.getMap().getSize()) {
            throw new InvalidGameStateException("Cannot move outside the map");
        }

        Cell targetCell = findCell(game.getMap(), newPosition);
        if (targetCell.getType() == Cell.CellType.WALL) {
            throw new InvalidGameStateException("Cannot move through walls");
        }
    }

    private Cell findCell(GameMap map, Position position) {
        return map.getCells().stream()
                .filter(cell -> cell.getX() == position.getX() && cell.getY() == position.getY())
                .findFirst()
                .orElseThrow(() -> new InvalidGameStateException("Cell not found"));
    }

    private void handleItemInteraction(Game game, Cell cell) {
        if (cell.getItem() == null)
            return;

        switch (cell.getItem().getType()) {
            case TEACHER -> {
                Question question = getRandomQuestion();
                game.setCurrentQuestion(question);
                game.getUsedQuestionIds().add(question.getId());
            }
            case FRIEND -> {
                String hint = "This is a helpful hint!";
                game.getInventory().getHints().add(hint);
                cell.setItem(null);
            }
            default -> cell.setItem(null);
        }
    }

    private Question getRandomQuestion() {
        List<Question> questions = questionRepository.findAll();
        return questions.get(random.nextInt(questions.size()));
    }

    private void handleGameOver(Game game, Game.GameStatus status) {
        game.setStatus(status);
        game.updateScore();

        if (status == Game.GameStatus.WON) {
            int powerBonus = game.getPowerPoints() * 2;
            game.setScore(game.getScore() + powerBonus);

            int accuracyBonus = (int) ((game.getCorrectAnswers() / (double) game.getQuestionsAnswered()) * 100);
            game.setScore(game.getScore() + accuracyBonus);
        }

        eventPublisher.publishEvent(new GameStatusChangeEvent(this, game, status));
    }
}