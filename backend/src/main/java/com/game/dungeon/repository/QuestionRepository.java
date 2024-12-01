package com.game.dungeon.repository;

import com.game.dungeon.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    List<Question> findByDifficulty(String difficulty);
    List<Question> findByCategory(String category);
    List<Question> findByDifficultyAndCategory(String difficulty, String category);
} 