package com.game.dungeon.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AnswerQuestionRequest {
    @NotBlank(message = "Question ID is required")
    private String questionId;
    
    @NotBlank(message = "Answer is required")
    private String answer;
} 