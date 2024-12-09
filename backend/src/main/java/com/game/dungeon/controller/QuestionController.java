package com.game.dungeon.controller;

import com.game.dungeon.model.Question;
import com.game.dungeon.repository.QuestionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing questions
 */
@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
@Tag(name = "Questions", description = "Question management endpoints")
public class QuestionController {
    private final QuestionRepository questionRepository;

    /**
     * Get questions filtered by difficulty and/or category.
     *
     * @param difficulty Optional difficulty level filter
     * @param category Optional category filter
     * @return List of questions matching the criteria
     */
    @Operation(summary = "Get questions", description = "Get questions filtered by difficulty and/or category")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved questions")
    @GetMapping
    public ResponseEntity<List<Question>> getQuestions(
            @Parameter(description = "Difficulty level filter") 
            @RequestParam(required = false) String difficulty,
            @Parameter(description = "Category filter") 
            @RequestParam(required = false) String category) {
        if (difficulty != null && category != null) {
            return ResponseEntity.ok(questionRepository.findByDifficultyAndCategory(difficulty, category));
        } else if (difficulty != null) {
            return ResponseEntity.ok(questionRepository.findByDifficulty(difficulty));
        } else if (category != null) {
            return ResponseEntity.ok(questionRepository.findByCategory(category));
        }
        return ResponseEntity.ok(questionRepository.findAll());
    }

    /**
     * Get a specific question by ID.
     *
     * @param id ID of the question to retrieve
     * @return The requested question
     */
    @Operation(summary = "Get question by ID", description = "Get a specific question by its ID")
    @ApiResponse(responseCode = "200", description = "Question found and returned")
    @ApiResponse(responseCode = "404", description = "Question not found")
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestion(
            @Parameter(description = "ID of the question to retrieve") 
            @PathVariable String id) {
        return questionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}