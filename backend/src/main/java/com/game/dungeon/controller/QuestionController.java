package com.game.dungeon.controller;

import com.game.dungeon.model.Question;
import com.game.dungeon.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionRepository questionRepository;

    @GetMapping
    public ResponseEntity<List<Question>> getQuestions(
            @RequestParam(required = false) String difficulty,
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

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable String id) {
        return questionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}