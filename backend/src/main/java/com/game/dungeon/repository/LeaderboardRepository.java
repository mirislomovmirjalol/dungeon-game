package com.game.dungeon.repository;

import com.game.dungeon.model.Game;
import com.game.dungeon.model.Leaderboard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaderboardRepository extends MongoRepository<Leaderboard, String> {
    List<Leaderboard> findByLevelOrderByScoreDesc(Game.GameLevel level, Pageable pageable);
    List<Leaderboard> findByPlayerNameOrderByScoreDesc(String playerName);
} 