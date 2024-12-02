package com.game.dungeon.observer;

import com.game.dungeon.model.Game;
import com.game.dungeon.model.Leaderboard;
import com.game.dungeon.repository.LeaderboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class GameEventListener {
    private final LeaderboardRepository leaderboardRepository;

    @EventListener
    public void handleGameStatusChange(GameStatusChangeEvent event) {
        if (event.getNewStatus() == Game.GameStatus.WON) {
            updateLeaderboard(event.getGame());
        }
    }

    private void updateLeaderboard(Game game) {
        Leaderboard entry = new Leaderboard();
        entry.setPlayerName(game.getPlayerName());
        entry.setLevel(game.getLevel());
        entry.setScore(calculateScore(game));
        entry.setCompletedAt(Instant.now());
        leaderboardRepository.save(entry);
    }

    private int calculateScore(Game game) {
        int score = game.getPowerPoints();
        
        score += game.getInventory().getAnsweredQuestions().size() * 50;
        
        score *= switch (game.getLevel()) {
            case EASY -> 1;
            case MEDIUM -> 1.5;
            case HARD -> 2;
        };
        
        return score;
    }
} 