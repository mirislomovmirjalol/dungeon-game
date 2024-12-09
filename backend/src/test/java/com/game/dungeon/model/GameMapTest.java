package com.game.dungeon.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameMapTest {

    private GameMap gameMap;
    private static final int TEST_MAP_SIZE = 3;

    @BeforeEach
    void setUp() {
        gameMap = new GameMap();
        gameMap.setSize(TEST_MAP_SIZE);
        
        List<Cell> cells = new ArrayList<>();
        for (int y = 0; y < TEST_MAP_SIZE; y++) {
            for (int x = 0; x < TEST_MAP_SIZE; x++) {
                Cell cell = new Cell();
                cell.setX(x);
                cell.setY(y);
                cell.setType(Cell.CellType.EMPTY);
                cells.add(cell);
            }
        }
        gameMap.setCells(cells);
    }

    @Test
    @DisplayName("GameMap should be initialized with correct size")
    void gameMap_ShouldHaveCorrectSize() {
        assertEquals(TEST_MAP_SIZE, gameMap.getSize());
        assertEquals(TEST_MAP_SIZE * TEST_MAP_SIZE, gameMap.getCells().size());
    }

    @Test
    @DisplayName("GameMap cells should have correct positions")
    void gameMap_CellsShouldHaveCorrectPositions() {
        List<Cell> cells = gameMap.getCells();
        
        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            int expectedX = i % TEST_MAP_SIZE;
            int expectedY = i / TEST_MAP_SIZE;
            
            assertEquals(expectedX, cell.getX());
            assertEquals(expectedY, cell.getY());
        }
    }

    @Test
    @DisplayName("GameMap should handle cell updates")
    void gameMap_ShouldHandleCellUpdates() {
        Cell cell = gameMap.getCells().get(0);
        cell.setType(Cell.CellType.WALL);
        cell.setExplored(true);
        
        Cell updatedCell = gameMap.getCells().get(0);
        assertEquals(Cell.CellType.WALL, updatedCell.getType());
        assertTrue(updatedCell.isExplored());
    }

    @Test
    @DisplayName("GameMap should maintain cell references")
    void gameMap_ShouldMaintainCellReferences() {
        List<Cell> originalCells = gameMap.getCells();
        List<Cell> newCells = new ArrayList<>(originalCells);
        
        gameMap.setCells(newCells);
        
        assertSame(newCells, gameMap.getCells());
        assertEquals(originalCells.size(), gameMap.getCells().size());
    }

    @Test
    @DisplayName("GameMap should handle items in cells")
    void gameMap_ShouldHandleItemsInCells() {
        Cell cell = gameMap.getCells().get(0);
        Item item = new Item();
        item.setType(Item.ItemType.TEACHER);
        
        cell.setItem(item);
        
        Cell cellWithItem = gameMap.getCells().get(0);
        assertNotNull(cellWithItem.getItem());
        assertEquals(Item.ItemType.TEACHER, cellWithItem.getItem().getType());
    }
} 