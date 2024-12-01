package com.game.dungeon.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document(collection = "leaderboard")
public class Leaderboard {
    @Id
    private String id;
    private String playerName;
    private Game.GameLevel level;
    private int score;
    private Instant completedAt;
} 