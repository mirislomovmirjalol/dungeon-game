package com.game.dungeon.controller;

import com.game.dungeon.dto.AnswerQuestionRequest;
import com.game.dungeon.dto.CreateGameRequest;
import com.game.dungeon.dto.MoveRequest;
import com.game.dungeon.model.Game;
import com.game.dungeon.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
@CrossOrigin(origins = "${app.cors.allowed-origins:http://localhost:3000}")
public class GameController {
    private final GameService gameService;

    @PostMapping
    public ResponseEntity<Game> createGame(@Valid @RequestBody CreateGameRequest request) {
        Game game = gameService.createGame(request.getPlayerName(), request.getLevel());
        return ResponseEntity.ok(game);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGame(@PathVariable String gameId) {
        Game game = gameService.getGame(gameId);
        return ResponseEntity.ok(game);
    }

    @PostMapping("/{gameId}/move")
    public ResponseEntity<Game> movePlayer(
            @PathVariable String gameId,
            @Valid @RequestBody MoveRequest request) {
        Game game = gameService.movePlayer(gameId, request.getDirection());
        return ResponseEntity.ok(game);
    }

    @PostMapping("/{gameId}/answer")
    public ResponseEntity<Game> answerQuestion(
            @PathVariable String gameId,
            @Valid @RequestBody AnswerQuestionRequest request) {
        Game game = gameService.answerQuestion(gameId, request.getQuestionId(), request.getAnswer());
        return ResponseEntity.ok(game);
    }
} 