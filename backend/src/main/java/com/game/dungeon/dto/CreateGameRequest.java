package com.game.dungeon.dto;

import com.game.dungeon.model.Game;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateGameRequest {
    @NotBlank(message = "Player name is required")
    private String playerName;
    
    @NotNull(message = "Game level is required")
    private Game.GameLevel level;
} 