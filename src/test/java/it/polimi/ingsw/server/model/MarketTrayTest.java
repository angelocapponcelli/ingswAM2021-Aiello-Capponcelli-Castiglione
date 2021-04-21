package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.interfaces.Takeable;
import it.polimi.ingsw.server.model.resources.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MarketTrayTest {
    MarketTray marketTray;
    @BeforeEach
    void populateMarketTray() {
        Takeable[][] marketTrayTemp = new Takeable[][]{
                {Coin.getInstance(),    Any.getInstance(),    Stone.getInstance(),   Servant.getInstance()},
                {Stone.getInstance(),   Shield.getInstance(), Faith.getInstance(),   Any.getInstance()},
                {Any.getInstance(),     Any.getInstance(),    Servant.getInstance(), Coin.getInstance()},
        };
        marketTray = new MarketTray(marketTrayTemp, Shield.getInstance());
    }

    @Test
    void selectRowTest(){
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> marketTray.selectRow(3));

        List<Takeable> expectedRow = Arrays.asList(Coin.getInstance(), Any.getInstance(), Stone.getInstance(), Servant.getInstance());
        assertEquals(expectedRow, marketTray.selectRow(0));

        List<Takeable> newRow = Arrays.asList(Any.getInstance(), Stone.getInstance(), Servant.getInstance(), Shield.getInstance());
        assertEquals(newRow, marketTray.getRow(0));

        assertEquals(Coin.getInstance(), marketTray.getSlide());
    }

    @Test
    void selectColumnTest(){
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> marketTray.selectColumn(4));

        List<Takeable> expectedList = Arrays.asList(Coin.getInstance(), Stone.getInstance(), Any.getInstance());
        assertEquals(expectedList, marketTray.selectColumn(0));

        List<Takeable> newColumn = Arrays.asList(Stone.getInstance(), Any.getInstance(), Shield.getInstance());
        assertEquals(newColumn, marketTray.getColumn(0));

        assertEquals(Coin.getInstance(), marketTray.getSlide());
    }

}