package com.game.dungeon.service;

import com.game.dungeon.model.Direction;
import com.game.dungeon.model.Game;
import com.game.dungeon.model.Game.GameLevel;

/**
 * Service interface for managing game operations.
 * Handles game creation, state management, player movement, and game progression.
 */
public interface GameService {
    
    /**
     * Creates a new game instance for a player.
     *
     * @param playerName Name of the player
     * @param level Difficulty level of the game
     * @return Newly created game instance
     */
    Game createGame(String playerName, GameLevel level);

    /**
     * Retrieves the current state of a game.
     *
     * @param gameId ID of the game to retrieve
     * @return Current game state
     * @throws com.game.dungeon.exception.ResourceNotFoundException if game not found
     */
    Game getGame(String gameId);

    /**
     * Moves the player in the specified direction.
     *
     * @param gameId ID of the game
     * @param direction Direction to move the player
     * @return Updated game state after movement
     * @throws com.game.dungeon.exception.InvalidGameStateException if move is invalid
     */
    Game movePlayer(String gameId, Direction direction);

    /**
     * Processes a player's answer to a question.
     *
     * @param gameId ID of the game
     * @param questionId ID of the question being answered
     * @param answer Player's answer to the question
     * @return Updated game state after processing the answer
     * @throws com.game.dungeon.exception.InvalidGameStateException if answer submission is invalid
     */
    Game answerQuestion(String gameId, String questionId, String answer);

    /**
     * Progresses the game to the next level if conditions are met.
     *
     * @param gameId ID of the game
     * @return Updated game state in the new level
     * @throws com.game.dungeon.exception.InvalidGameStateException if progression is not allowed
     */
    Game progressToNextLevel(String gameId);
} 