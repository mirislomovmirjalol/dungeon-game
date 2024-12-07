package com.game.dungeon.dto;

import com.game.dungeon.model.Direction;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MoveRequest {
    @NotNull(message = "Direction is required")
    private Direction direction;
} 