package com.game.dungeon.exception;

public class ResourceNotFoundException extends GameException {
    public ResourceNotFoundException(String resource, String identifier) {
        super(String.format("%s not found with identifier: %s", resource, identifier));
    }
} 