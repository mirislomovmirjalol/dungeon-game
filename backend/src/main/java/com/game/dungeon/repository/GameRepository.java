package com.game.dungeon.repository;

import com.game.dungeon.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
    List<Game> findByPlayerName(String playerName);
    Optional<Game> findByPlayerNameAndStatus(String playerName, Game.GameStatus status);
    List<Game> findByStatus(Game.GameStatus status);
} 