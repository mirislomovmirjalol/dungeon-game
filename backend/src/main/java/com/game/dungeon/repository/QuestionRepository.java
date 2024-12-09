package com.game.dungeon.repository;

import com.game.dungeon.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    
    /**
     * @param difficulty The difficulty level to filter by
     * @return List of questions matching the difficulty
     */
    List<Question> findByDifficulty(String difficulty);
    
    /**
     * @param category The category to filter by (e.g., "OOP", "Design Patterns")
     * @return List of questions in the specified category
     */
    List<Question> findByCategory(String category);
    
    /**
     * @param difficulty The difficulty level to filter by
     * @param category The category to filter by
     * @return List of questions matching both criteria
     */
    List<Question> findByDifficultyAndCategory(String difficulty, String category);
} 