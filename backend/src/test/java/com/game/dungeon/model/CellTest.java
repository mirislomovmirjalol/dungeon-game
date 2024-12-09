package com.game.dungeon.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    @DisplayName("New cell should have default values")
    void newCell_ShouldHaveDefaultValues() {
        Cell cell = new Cell();
        
        assertEquals(0, cell.getX());
        assertEquals(0, cell.getY());
        assertFalse(cell.isExplored());
        assertNull(cell.getItem());
    }

    @ParameterizedTest
    @EnumSource(Cell.CellType.class)
    @DisplayName("Cell should accept all valid cell types")
    void setType_ShouldAcceptAllValidTypes(Cell.CellType type) {
        Cell cell = new Cell();
        cell.setType(type);
        assertEquals(type, cell.getType());
    }

    @Test
    @DisplayName("Cell should store position correctly")
    void cell_ShouldStorePosition() {
        Cell cell = new Cell();
        cell.setX(5);
        cell.setY(3);
        
        assertEquals(5, cell.getX());
        assertEquals(3, cell.getY());
    }

    @Test
    @DisplayName("Cell should store and update item")
    void cell_ShouldHandleItem() {
        Cell cell = new Cell();
        Item item = new Item();
        item.setType(Item.ItemType.TEACHER);
        
        cell.setItem(item);
        assertNotNull(cell.getItem());
        assertEquals(Item.ItemType.TEACHER, cell.getItem().getType());
    }

    @Test
    @DisplayName("Cell exploration status should be tracked")
    void cell_ShouldTrackExploration() {
        Cell cell = new Cell();
        assertFalse(cell.isExplored());
        
        cell.setExplored(true);
        assertTrue(cell.isExplored());
    }

    @Test
    @DisplayName("Cell should handle wall type correctly")
    void cell_ShouldHandleWallType() {
        Cell cell = new Cell();
        cell.setType(Cell.CellType.WALL);
        
        assertEquals(Cell.CellType.WALL, cell.getType());
        assertNull(cell.getItem());
    }
} 