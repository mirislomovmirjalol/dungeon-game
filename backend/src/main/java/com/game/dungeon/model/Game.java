package com.game.dungeon.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

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
    private Instant startedAt;
    private Instant lastSavedAt;
    private GameStatus status;

    public enum GameLevel {
        EASY(10),
        MEDIUM(15),
        HARD(20);

        private final int mapSize;

        GameLevel(int mapSize) {
            this.mapSize = mapSize;
        }

        public int getMapSize() {
            return mapSize;
        }
    }

    public enum GameStatus {
        IN_PROGRESS,
        WON,
        LOST
    }
} 