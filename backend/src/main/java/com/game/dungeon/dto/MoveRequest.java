package com.game.dungeon.dto;

import com.game.dungeon.service.GameService;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MoveRequest {
    @NotNull(message = "Direction is required")
    private GameService.Direction direction;
} 