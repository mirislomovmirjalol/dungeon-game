package com.game.dungeon.observer;

import com.game.dungeon.model.Game;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class GameStatusChangeEvent extends ApplicationEvent {
    private final Game game;
    private final Game.GameStatus newStatus;

    public GameStatusChangeEvent(Object source, Game game, Game.GameStatus newStatus) {
        super(source);
        this.game = game;
        this.newStatus = newStatus;
    }
} 