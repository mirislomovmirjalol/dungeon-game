package com.game.dungeon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    @DisplayName("Score should be calculated correctly based on level, power points, and correct answers")
    void updateScore_ShouldCalculateCorrectly() {
        game.setLevel(Game.GameLevel.EASY);
        game.setPowerPoints(10);
        game.setCorrectAnswers(2);

        game.updateScore();

        int expectedScore = (3 * 100) + (10 * 2) + (2 * 50);
        assertEquals(expectedScore, game.getScore());
    }

    @ParameterizedTest
    @EnumSource(Game.GameLevel.class)
    @DisplayName("Can win should be based on correct answers threshold for each level")
    void canWin_ShouldDependOnLevelAndCorrectAnswers(Game.GameLevel level) {
        game.setLevel(level);
        int requiredAnswers = switch (level) {
            case EASY -> 1;
            case MEDIUM -> 3;
            case HARD -> 5;
        };

        game.setCorrectAnswers(requiredAnswers - 1);
        assertFalse(game.canWin());

        game.setCorrectAnswers(requiredAnswers);
        assertTrue(game.canWin());

        game.setCorrectAnswers(requiredAnswers + 1);
        assertTrue(game.canWin());
    }

    @Test
    @DisplayName("Initial game state should be properly set")
    void initialGameState_ShouldBeProperlySet() {
        assertEquals(0, game.getQuestionsAnswered());
        assertEquals(0, game.getCorrectAnswers());
        assertNotNull(game.getUsedQuestionIds());
        assertTrue(game.getUsedQuestionIds().isEmpty());
    }

    @Test
    @DisplayName("Game level should provide correct initial values")
    void gameLevel_ShouldProvideCorrectValues() {
        Game.GameLevel level = Game.GameLevel.MEDIUM;
        
        assertEquals(5, level.getMapSize());
        assertEquals(25, level.getInitialPowerPoints());
        assertEquals(2, level.getMoveCost());
        assertEquals(8, level.getMinQuestions());
    }
} 