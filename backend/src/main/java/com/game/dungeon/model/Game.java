package com.game.dungeon.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/*
 * Game model contains all the state information for a player's game including
 * their progress, inventory, position, and score.
 */
@Data
@Document(collection = "games")
public class Game {
    @Id
    private String id;

    private String playerName;
    private GameLevel level;
    private int powerPoints;
    private GameMap map;
    private Inventory inventory;
    private Position currentPosition;
    private GameStatus status;
    private Question currentQuestion;
    private Instant startedAt;
    private int questionsAnswered;
    private int correctAnswers;
    private List<String> usedQuestionIds = new ArrayList<>();
    private int score;
    private String message;

    /**
     * Calculates and updates the player's score based on:
     * - Base score from map size
     * - Bonus from remaining power points
     * - Bonus from correctly answered questions
     */
    public void updateScore() {
        int baseScore = level.getMapSize() * 100;
        int powerBonus = powerPoints * 2;
        int accuracyBonus = correctAnswers * 50;
        this.score = baseScore + powerBonus + accuracyBonus;
    }

    public boolean canWin() {
        int requiredQuestions = switch (level) {
            case EASY -> 1; // Easy level requires 1 question
            case MEDIUM -> 3; // Medium level requires 3 questions
            case HARD -> 5; // Hard level requires 5 questions
        };
        return correctAnswers >= requiredQuestions;
    }

    @Data
    public static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public enum GameLevel {
        EASY(3, 15, 1, 2), // 3x3 grid, 15 power points, 1 point per move, 2 questions minimum
        MEDIUM(5, 25, 2, 8), // 5x5 grid, 25 power points, 2 points per move, 8 questions minimum
        HARD(10, 50, 3, 25); // 10x10 grid, 50 power points, 3 points per move, 25 questions minimum

        private final int mapSize;
        private final int initialPowerPoints;
        private final int moveCost;
        private final int minQuestions;

        GameLevel(int mapSize, int initialPowerPoints, int moveCost, int minQuestions) {
            this.mapSize = mapSize;
            this.initialPowerPoints = initialPowerPoints;
            this.moveCost = moveCost;
            this.minQuestions = minQuestions;
        }

        public int getMapSize() {
            return mapSize;
        }

        public int getInitialPowerPoints() {
            return initialPowerPoints;
        }

        public int getMoveCost() {
            return moveCost;
        }

        public int getMinQuestions() {
            return minQuestions;
        }
    }

    public enum GameStatus {
        IN_PROGRESS,
        LEVEL_COMPLETE,
        WON,
        LOST
    }
}