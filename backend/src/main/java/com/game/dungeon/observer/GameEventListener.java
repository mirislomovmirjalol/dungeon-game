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
        entry.setScore(game.getScore());
        entry.setCompletedAt(Instant.now());
        leaderboardRepository.save(entry);
    }
} 