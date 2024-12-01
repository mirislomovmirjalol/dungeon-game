package com.game.dungeon.controller;

import com.game.dungeon.model.Game;
import com.game.dungeon.model.Leaderboard;
import com.game.dungeon.repository.LeaderboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "${app.cors.allowed-origins:http://localhost:3000}")
public class LeaderboardController {
    private final LeaderboardRepository leaderboardRepository;

    @GetMapping
    public ResponseEntity<List<Leaderboard>> getTopScores(
            @RequestParam(required = false) Game.GameLevel level,
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

    @GetMapping("/player/{playerName}")
    public ResponseEntity<List<Leaderboard>> getPlayerScores(@PathVariable String playerName) {
        return ResponseEntity.ok(leaderboardRepository.findByPlayerNameOrderByScoreDesc(playerName));
    }
} 