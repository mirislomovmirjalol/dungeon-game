package com.game.dungeon.observer;

import com.game.dungeon.model.Game;
import com.game.dungeon.model.Leaderboard;
import com.game.dungeon.repository.LeaderboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * Event listener for game-related events.
 * Implements the Observer pattern to react to changes in game state.
 * Currently handles game completion events by updating the leaderboard.
 */
@Component
@RequiredArgsConstructor
public class GameEventListener {
    private final LeaderboardRepository leaderboardRepository;

    /**
     * Handles game status change events.
     * When a game is won, updates the leaderboard with the player's score.
     *
     * @param event The game status change event containing the updated game state
     */
    @EventListener
    public void handleGameStatusChange(GameStatusChangeEvent event) {
        if (event.getNewStatus() == Game.GameStatus.WON) {
            updateLeaderboard(event.getGame());
        }
    }

    /**
     * Updates the leaderboard with a completed game's information.
     * Creates a new leaderboard entry with the player's name, level, score,
     * and completion time.
     *
     * @param game The completed game to add to the leaderboard
     */
    private void updateLeaderboard(Game game) {
        Leaderboard entry = new Leaderboard();
        entry.setPlayerName(game.getPlayerName());
        entry.setLevel(game.getLevel());
        entry.setScore(game.getScore());
        entry.setCompletedAt(Instant.now());
        leaderboardRepository.save(entry);
    }
}