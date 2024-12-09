package com.game.dungeon.repository;

import com.game.dungeon.model.Game;
import com.game.dungeon.model.Leaderboard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for managing leaderboard persistence.
 * Provides methods to save and retrieve leaderboard entries from MongoDB.
 * Supports filtering and sorting by game level and player name.
 */
@Repository
public interface LeaderboardRepository extends MongoRepository<Leaderboard, String> {
    
    /**
     * Finds top scores for a specific game level, ordered by score descending.
     *
     * @param level The game level to filter by
     * @param pageable Pagination information for limiting results
     * @return List of top scores for the specified level
     */
    List<Leaderboard> findByLevelOrderByScoreDesc(Game.GameLevel level, Pageable pageable);
    
    /**
     * Finds all scores for a specific player, ordered by score descending.
     *
     * @param playerName Name of the player
     * @return List of all scores for the player
     */
    List<Leaderboard> findByPlayerNameOrderByScoreDesc(String playerName);
} 