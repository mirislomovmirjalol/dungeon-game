package com.game.dungeon.service;

import com.game.dungeon.model.Direction;
import com.game.dungeon.model.Game;
import com.game.dungeon.model.Game.GameLevel;
import com.game.dungeon.model.Game.GameStatus;
import com.game.dungeon.model.GameMap;
import com.game.dungeon.model.Cell;
import com.game.dungeon.model.Question;
import com.game.dungeon.model.Inventory;
import com.game.dungeon.repository.GameRepository;
import com.game.dungeon.factory.ItemFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private ItemFactory itemFactory;

    @InjectMocks
    private GameServiceImpl gameService;

    private Game testGame;
    private static final String TEST_GAME_ID = "test-game-id";
    private static final String TEST_PLAYER_NAME = "testPlayer";
    private static final String TEST_QUESTION_ID = "test-question-id";

    @BeforeEach
    void setUp() {
        testGame = new Game();
        testGame.setId(TEST_GAME_ID);
        testGame.setPlayerName(TEST_PLAYER_NAME);
        testGame.setLevel(GameLevel.EASY);
        testGame.setPowerPoints(GameLevel.EASY.getInitialPowerPoints());
        testGame.setStatus(GameStatus.IN_PROGRESS);
        testGame.setCurrentPosition(new Game.Position(0, 0));
        
        Inventory inventory = new Inventory();
        inventory.setAnsweredQuestions(new ArrayList<>());
        inventory.setHints(new ArrayList<>());
        testGame.setInventory(inventory);
        
        GameMap gameMap = new GameMap();
        gameMap.setSize(GameLevel.EASY.getMapSize());
        List<Cell> cells = new ArrayList<>();
        
        for (int y = 0; y < GameLevel.EASY.getMapSize(); y++) {
            for (int x = 0; x < GameLevel.EASY.getMapSize(); x++) {
                Cell cell = new Cell();
                cell.setX(x);
                cell.setY(y);
                cell.setType(Cell.CellType.EMPTY);
                cell.setExplored(false);
                cells.add(cell);
            }
        }
        gameMap.setCells(cells);
        testGame.setMap(gameMap);
    }

    @Test
    void whenCreateGame_thenGameInitializedCorrectly() {
        when(itemFactory.createItem(anyString(), anyList(), anyBoolean())).thenReturn(null);
        when(gameRepository.save(any(Game.class))).thenReturn(testGame);

        Game result = gameService.createGame(TEST_PLAYER_NAME, GameLevel.EASY);

        assertNotNull(result);
        assertEquals(TEST_PLAYER_NAME, result.getPlayerName());
        assertEquals(GameLevel.EASY, result.getLevel());
        assertEquals(GameStatus.IN_PROGRESS, result.getStatus());
        assertNotNull(result.getMap());
        assertEquals(GameLevel.EASY.getMapSize(), result.getMap().getSize());
    }

    @Test
    void whenGetGame_thenReturnCorrectGame() {
        when(gameRepository.findById(TEST_GAME_ID)).thenReturn(Optional.of(testGame));

        Game result = gameService.getGame(TEST_GAME_ID);

        assertNotNull(result);
        assertEquals(TEST_GAME_ID, result.getId());
        assertEquals(TEST_PLAYER_NAME, result.getPlayerName());
    }

    @Test
    void whenMovePlayer_thenPositionUpdated() {
        when(gameRepository.findById(TEST_GAME_ID)).thenReturn(Optional.of(testGame));
        when(gameRepository.save(any(Game.class))).thenReturn(testGame);

        Game result = gameService.movePlayer(TEST_GAME_ID, Direction.RIGHT);

        assertNotNull(result);
        assertNotNull(result.getCurrentPosition());
        assertTrue(result.getCurrentPosition().getX() > 0 || result.getCurrentPosition().getY() > 0);
    }

    @Test
    void whenAnswerQuestion_thenGameStateUpdated() {
        Question question = new Question();
        question.setId(TEST_QUESTION_ID);
        question.setQuestion("Test question?");
        question.setCorrectAnswer("correct answer");
        question.setCategory("OOP");
        question.setDifficulty("EASY");
        question.setPoints(10);
        testGame.setCurrentQuestion(question);

        when(gameRepository.findById(TEST_GAME_ID)).thenReturn(Optional.of(testGame));
        when(gameRepository.save(any(Game.class))).thenReturn(testGame);

        Game result = gameService.answerQuestion(TEST_GAME_ID, TEST_QUESTION_ID, "correct answer");

        assertNotNull(result);
        assertTrue(result.getQuestionsAnswered() > 0);
        assertTrue(result.getCorrectAnswers() > 0);
        assertNotNull(result.getInventory().getAnsweredQuestions());
        assertFalse(result.getInventory().getAnsweredQuestions().isEmpty());
    }
} 