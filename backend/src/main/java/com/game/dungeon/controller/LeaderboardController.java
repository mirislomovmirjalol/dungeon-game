package com.game.dungeon.controller;

import com.game.dungeon.model.Game;
import com.game.dungeon.model.Leaderboard;
import com.game.dungeon.repository.LeaderboardRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing leaderboard
 */
@RestController
@RequestMapping("/api/leaderboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Leaderboard", description = "Leaderboard management endpoints")
public class LeaderboardController {
    private final LeaderboardRepository leaderboardRepository;

    /**
     * Get top scores from the leaderboard.
     *
     * @param level Optional game level filter
     * @param limit Maximum number of scores to return
     * @return List of top scores
     */
    @Operation(summary = "Get top scores", description = "Get top scores from the leaderboard")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved top scores")
    @GetMapping
    public ResponseEntity<List<Leaderboard>> getTopScores(
            @Parameter(description = "Game level filter") 
            @RequestParam(required = false) Game.GameLevel level,
            @Parameter(description = "Maximum number of scores to return") 
            @RequestParam(defaultValue = "10") int limit) {
        if (level != null) {
            return ResponseEntity.ok(
                leaderboardRepository.findByLevelOrderByScoreDesc(level, PageRequest.of(0, limit))
            );
        }
        return ResponseEntity.ok(
            leaderboardRepository.findAll(PageRequest.of(0, limit)).getContent()
        );
    }

    /**
     * Get all scores for a specific player.
     *
     * @param playerName Name of the player
     * @return List of player's scores
     */
    @Operation(summary = "Get player scores", description = "Get all scores for a specific player")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved player scores")
    @GetMapping("/player/{playerName}")
    public ResponseEntity<List<Leaderboard>> getPlayerScores(
            @Parameter(description = "Name of the player") 
            @PathVariable String playerName) {
        return ResponseEntity.ok(leaderboardRepository.findByPlayerNameOrderByScoreDesc(playerName));
    }
} 