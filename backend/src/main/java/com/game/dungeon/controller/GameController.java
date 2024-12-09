package com.game.dungeon.controller;

import com.game.dungeon.dto.AnswerQuestionRequest;
import com.game.dungeon.dto.CreateGameRequest;
import com.game.dungeon.dto.MoveRequest;
import com.game.dungeon.model.Game;
import com.game.dungeon.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing main game functionality
 */
@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
@Tag(name = "Game", description = "Game management endpoints")
public class GameController {
    private final GameService gameService;

    /**
     * Create a new game instance for a player.
     *
     * @param request Contains player name and game level
     * @return The newly created game
     */
    @Operation(summary = "Create a new game", description = "Create a new game instance with specified player name and level")
    @ApiResponse(responseCode = "200", description = "Game successfully created")
    @PostMapping
    public ResponseEntity<Game> createGame(@Valid @RequestBody CreateGameRequest request) {
        Game game = gameService.createGame(request.getPlayerName(), request.getLevel());
        return ResponseEntity.ok(game);
    }

    /**
     * Get the current state of a game.
     *
     * @param gameId The ID of the game to retrieve
     * @return The current game state
     */
    @Operation(summary = "Get game state", description = "Get the current state of a game by ID")
    @ApiResponse(responseCode = "200", description = "Game found and returned")
    @ApiResponse(responseCode = "404", description = "Game not found")
    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGame(
            @Parameter(description = "ID of the game to retrieve") 
            @PathVariable String gameId) {
        Game game = gameService.getGame(gameId);
        return ResponseEntity.ok(game);
    }

    /**
     * Move the player in the specified direction.
     *
     * @param gameId The ID of the game
     * @param request Contains the direction to move
     * @return Updated game state after movement
     */
    @Operation(summary = "Move player", description = "Move the player in the specified direction")
    @ApiResponse(responseCode = "200", description = "Player moved successfully")
    @ApiResponse(responseCode = "400", description = "Invalid move")
    @PostMapping("/{gameId}/move")
    public ResponseEntity<Game> movePlayer(
            @Parameter(description = "ID of the game") 
            @PathVariable String gameId,
            @Valid @RequestBody MoveRequest request) {
        Game game = gameService.movePlayer(gameId, request.getDirection());
        return ResponseEntity.ok(game);
    }

    /**
     * Submit an answer to a question in the game.
     *
     * @param gameId The ID of the game
     * @param request Contains the question ID and player's answer
     * @return Updated game state after answering
     */
    @Operation(summary = "Answer question", description = "Submit an answer to a question in the game")
    @ApiResponse(responseCode = "200", description = "Answer submitted successfully")
    @ApiResponse(responseCode = "400", description = "Invalid answer submission")
    @PostMapping("/{gameId}/answer")
    public ResponseEntity<Game> answerQuestion(
            @Parameter(description = "ID of the game") 
            @PathVariable String gameId,
            @Valid @RequestBody AnswerQuestionRequest request) {
        Game game = gameService.answerQuestion(gameId, request.getQuestionId(), request.getAnswer());
        return ResponseEntity.ok(game);
    }

    /**
     * Progress the game to the next level.
     *
     * @param gameId The ID of the game
     * @return Updated game state in the new level
     */
    @Operation(summary = "Progress to next level", description = "Move the game to the next level if conditions are met")
    @ApiResponse(responseCode = "200", description = "Successfully progressed to next level")
    @ApiResponse(responseCode = "400", description = "Cannot progress to next level")
    @PostMapping("/{gameId}/next-level")
    public ResponseEntity<Game> progressToNextLevel(
            @Parameter(description = "ID of the game") 
            @PathVariable String gameId) {
        Game game = gameService.progressToNextLevel(gameId);
        return ResponseEntity.ok(game);
    }
} 