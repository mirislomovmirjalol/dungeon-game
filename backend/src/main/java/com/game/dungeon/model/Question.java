package com.game.dungeon.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Question model contains the question text, possible answers, correct answer,
 * explanation, and points awarded for correctly answering the question.
 */
@Data
@Document(collection = "questions")
public class Question {
    @Id
    private String id;
    private String category;
    private String difficulty;
    private String question;
    private List<String> options;
    private String correctAnswer;
    private String explanation;
    private Integer points;
}