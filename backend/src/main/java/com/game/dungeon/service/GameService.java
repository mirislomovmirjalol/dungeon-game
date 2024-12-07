package com.game.dungeon.service;

import com.game.dungeon.model.Direction;
import com.game.dungeon.model.Game;
import com.game.dungeon.model.Game.GameLevel;

public interface GameService {
    Game createGame(String playerName, GameLevel level);
    Game getGame(String gameId);
    Game movePlayer(String gameId, Direction direction);
    Game answerQuestion(String gameId, String questionId, String answer);
    Game progressToNextLevel(String gameId);
} 