package com.game.dungeon.model;

import lombok.Data;

import java.util.List;

@Data
public class Inventory {
    private List<String> hints;
    private List<String> answeredQuestions;
} 