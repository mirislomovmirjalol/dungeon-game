package com.game.dungeon.model;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.ArrayList;

@Data
public class Inventory {
    private List<String> hints = new ArrayList<>();
    private List<AnsweredQuestion> answeredQuestions = new ArrayList<>();

    @Data
    @AllArgsConstructor
    public static class AnsweredQuestion {
        private String questionId;
        private String answer;
        private boolean correct;
    }
} 