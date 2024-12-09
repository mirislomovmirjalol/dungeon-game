package com.game.dungeon.repository;

import com.game.dungeon.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {

    /**
     * @param playerName Name of the player
     * @return List of all games played by the player
     */
    List<Game> findByPlayerName(String playerName);

    /**
     * @param playerName Name of the player
     * @param status     Status of the game to find
     * @return Optional containing the game if found
     */
    Optional<Game> findByPlayerNameAndStatus(String playerName, Game.GameStatus status);

    /**
     * @param status Status of the games to find
     * @return List of games with the specified status
     */
    List<Game> findByStatus(Game.GameStatus status);
}