package com.game.dungeon.service.impl;

import com.game.dungeon.exception.InvalidGameStateException;
import com.game.dungeon.exception.ResourceNotFoundException;
import com.game.dungeon.model.*;
import com.game.dungeon.repository.GameRepository;
import com.game.dungeon.repository.QuestionRepository;
import com.game.dungeon.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final QuestionRepository questionRepository;

    @Value("${game.initial.power-points}")
    private int initialPowerPoints;

    @Value("${game.points.move-cost}")
    private int moveCost;

    @Override
    public Game createGame(String playerName, Game.GameLevel level) {
        Game game = new Game();
        game.setPlayerName(playerName);
        game.setLevel(level);
        game.setPowerPoints(initialPowerPoints);
        game.setStatus(Game.GameStatus.IN_PROGRESS);
        game.setStartedAt(Instant.now());
        game.setLastSavedAt(Instant.now());
        
        Inventory inventory = new Inventory();
        inventory.setHints(new ArrayList<>());
        inventory.setAnsweredQuestions(new ArrayList<>());
        game.setInventory(inventory);
        
        game.setMap(generateMap(level.getMapSize()));
        
        game.setCurrentPosition(new Game.Position(0, 0));
        
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

        Game.Position newPosition = calculateNewPosition(game.getCurrentPosition(), direction);
        
        validateMove(game, newPosition);
        
        game.setCurrentPosition(newPosition);
        Cell currentCell = getCellAt(game.getMap(), newPosition.getX(), newPosition.getY());
        currentCell.setExplored(true);
        
        game.setPowerPoints(game.getPowerPoints() - moveCost);
        if (game.getPowerPoints() <= 0) {
            game.setStatus(Game.GameStatus.LOST);
            throw new InvalidGameStateException("Game over: Out of power points");
        }

        if (currentCell.getType() == Cell.CellType.TREASURE) {
            game.setStatus(Game.GameStatus.WON);
        }

        game.setLastSavedAt(Instant.now());
        return gameRepository.save(game);
    }

    @Override
    public Game answerQuestion(String gameId, String questionId, String answer) {
        Game game = getGame(gameId);
        Question question = questionRepository.findById(questionId)
            .orElseThrow(() -> new ResourceNotFoundException("Question", questionId));

        if (game.getStatus() != Game.GameStatus.IN_PROGRESS) {
            throw new InvalidGameStateException("Game is not in progress");
        }

        boolean isCorrect = question.getCorrectAnswer().equals(answer);
        if (isCorrect) {
            game.setPowerPoints(game.getPowerPoints() + question.getPoints());
            game.getInventory().getAnsweredQuestions().add(questionId);
        } else {
            game.setPowerPoints(game.getPowerPoints() - question.getPoints());
            if (game.getPowerPoints() <= 0) {
                game.setStatus(Game.GameStatus.LOST);
                throw new InvalidGameStateException("Game over: Wrong answer depleted power points");
            }
        }

        game.setLastSavedAt(Instant.now());
        return gameRepository.save(game);
    }

    @Override
    public Game saveGame(Game game) {
        game.setLastSavedAt(Instant.now());
        return gameRepository.save(game);
    }

    private GameMap generateMap(int size) {
        GameMap map = new GameMap();
        map.setSize(size);
        List<Cell> cells = new ArrayList<>();
        
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Cell cell = new Cell();
                cell.setX(x);
                cell.setY(y);
                cell.setExplored(false);
                
                if (x == 0 && y == 0) {
                    cell.setType(Cell.CellType.ENTRANCE);
                } else if (x == size-1 && y == size-1) {
                    cell.setType(Cell.CellType.TREASURE);
                } else {
                    cell.setType(generateRandomCellType());
                    if (cell.getType() == Cell.CellType.EMPTY) {
                        cell.setItem(generateRandomItem());
                    }
                }
                
                cells.add(cell);
            }
        }
        
        map.setCells(cells);
        return map;
    }

    private Cell.CellType generateRandomCellType() {
        return Math.random() < 0.2 ? Cell.CellType.WALL : Cell.CellType.EMPTY;
    }

    private Item generateRandomItem() {
        Item item = new Item();
        double random = Math.random();
        
        if (random < 0.4) {
            item.setType(Item.ItemType.EMPTY);
        } else if (random < 0.7) {
            item.setType(Item.ItemType.TEACHER);
        } else {
            item.setType(Item.ItemType.FRIEND);
        }
        
        return item;
    }

    private Game.Position calculateNewPosition(Game.Position current, Direction direction) {
        int newX = current.getX();
        int newY = current.getY();
        
        switch (direction) {
            case UP -> newY--;
            case DOWN -> newY++;
            case LEFT -> newX--;
            case RIGHT -> newX++;
        }
        
        return new Game.Position(newX, newY);
    }

    private void validateMove(Game game, Game.Position newPosition) {
        if (newPosition.getX() < 0 || newPosition.getX() >= game.getMap().getSize() ||
            newPosition.getY() < 0 || newPosition.getY() >= game.getMap().getSize()) {
            throw new InvalidGameStateException("Cannot move outside the map boundaries");
        }
        
        Cell targetCell = getCellAt(game.getMap(), newPosition.getX(), newPosition.getY());
        if (targetCell.getType() == Cell.CellType.WALL) {
            throw new InvalidGameStateException("Cannot move through walls");
        }
    }

    private Cell getCellAt(GameMap map, int x, int y) {
        return map.getCells().stream()
            .filter(cell -> cell.getX() == x && cell.getY() == y)
            .findFirst()
            .orElseThrow(() -> new InvalidGameStateException("Invalid cell position"));
    }
} 