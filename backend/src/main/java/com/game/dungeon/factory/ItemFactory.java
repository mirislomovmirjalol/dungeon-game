package com.game.dungeon.factory;

import com.game.dungeon.model.Item;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ItemFactory {
    private final Random random = new Random();

    public Item createItem(String difficulty) {
        Item item = new Item();
        double chance = random.nextDouble();

        if (adjustProbability(chance, difficulty) < 0.4) {
            item.setType(Item.ItemType.EMPTY);
        } else if (adjustProbability(chance, difficulty) < 0.7) {
            item.setType(Item.ItemType.TEACHER);
        } else {
            item.setType(Item.ItemType.FRIEND);
        }

        return item;
    }

    private double adjustProbability(double chance, String difficulty) {
        return switch (difficulty.toUpperCase()) {
            case "EASY" -> chance * 1.2;
            case "HARD" -> chance * 0.8;
            default -> chance;
        };
    }
} 