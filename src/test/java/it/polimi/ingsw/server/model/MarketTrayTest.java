package it.polimi.ingsw.server.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MarketTrayTest {

    @Test
    void selectRow() {
        Resource coin1 = new Resource(ResourceType.COIN);
        Resource coin2 = new Resource(ResourceType.COIN);
        Resource stone1 = new Resource(ResourceType.STONE);
        Resource stone2 = new Resource(ResourceType.STONE);
        Resource servant1 = new Resource(ResourceType.SERVANT);
        Resource servant2 = new Resource(ResourceType.SERVANT);
        Resource shield1 = new Resource(ResourceType.SHIELD);
        Takeable[][] marketContent = new Takeable[][]{
                {coin1, coin2, stone1},
                {stone2, servant1, shield1}
        };
        MarketTray marketTray = new MarketTray(marketContent, servant2);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> marketTray.selectRow(2));
        List<Takeable> expectedList = Arrays.asList(coin1, coin2, stone1);
        assertEquals(expectedList, marketTray.selectRow(0));
        expectedList = Arrays.asList(coin2, stone1, servant2);
        assertEquals(expectedList, marketTray.selectRow(0));
    }

    @Test
    void selectColumn() {
        Resource coin1 = new Resource(ResourceType.COIN);
        Resource coin2 = new Resource(ResourceType.COIN);
        Resource stone1 = new Resource(ResourceType.STONE);
        Resource stone2 = new Resource(ResourceType.STONE);
        Resource servant1 = new Resource(ResourceType.SERVANT);
        Resource servant2 = new Resource(ResourceType.SERVANT);
        Resource shield1 = new Resource(ResourceType.SHIELD);
        Takeable[][] marketContent = new Takeable[][]{
                {coin1, coin2, stone1},
                {stone2, servant1, shield1}
        };
        MarketTray marketTray = new MarketTray(marketContent, servant2);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> marketTray.selectColumn(3));
        List<Takeable> expectedList = Arrays.asList(coin1, stone2);
        assertEquals(expectedList, marketTray.selectColumn(0));
        expectedList = Arrays.asList(stone1, shield1);
        assertEquals(expectedList, marketTray.selectColumn(2));
    }

    @Test
    void multipleSelect() {
        Resource coin1 = new Resource(ResourceType.COIN);
        Resource coin2 = new Resource(ResourceType.COIN);
        Resource stone1 = new Resource(ResourceType.STONE);
        Resource stone2 = new Resource(ResourceType.STONE);
        Resource servant1 = new Resource(ResourceType.SERVANT);
        Resource servant2 = new Resource(ResourceType.SERVANT);
        Resource shield1 = new Resource(ResourceType.SHIELD);
        Takeable[][] marketContent = new Takeable[][]{
                {coin1, coin2, stone1},
                {stone2, servant1, shield1}
        };
        MarketTray marketTray = new MarketTray(marketContent, servant2);
        List<Takeable> expectedList = Arrays.asList(stone2, servant1, shield1);
        assertEquals(expectedList, marketTray.selectRow(1));
        expectedList = Arrays.asList(stone1, servant2);
        assertEquals(expectedList, marketTray.selectColumn(2));
        expectedList = Arrays.asList(coin1, coin2,servant2);
        assertEquals(expectedList, marketTray.selectRow(0));
        expectedList = Arrays.asList(servant2,shield1);
        assertEquals(expectedList, marketTray.selectColumn(1));
    }
}