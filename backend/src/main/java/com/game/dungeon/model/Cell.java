package com.game.dungeon.model;

import lombok.Data;

@Data
public class Cell {
    private int x;
    private int y;
    private CellType type;
    private Item item;
    private boolean explored;

    public enum CellType {
        EMPTY,
        ENTRANCE,
        EXIT,
        TEACHER,
        WALL,
        FRIEND
    }

    public void setType(CellType type) {
        this.type = type;
    }
}