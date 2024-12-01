package com.game.dungeon.model;

import lombok.Data;

import java.util.List;

@Data
public class GameMap {
    private int size;
    private List<Cell> cells;
} 