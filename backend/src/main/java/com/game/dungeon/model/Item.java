package com.game.dungeon.model;

import lombok.Data;

@Data
public class Item {
    private ItemType type;
    private String questionId;
    private String hint;

    public enum ItemType {
        TEACHER,
        FRIEND,
        EMPTY
    }
} 