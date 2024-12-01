package com.game.dungeon.service;

import com.game.dungeon.model.Game;

public interface GameService {
    Game createGame(String playerName, Game.GameLevel level);
    Game getGame(String gameId);
    Game movePlayer(String gameId, Direction direction);
    Game answerQuestion(String gameId, String questionId, String answer);
    Game saveGame(Game game);

    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
} 