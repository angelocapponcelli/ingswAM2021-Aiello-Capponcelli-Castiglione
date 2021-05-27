package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.globalBoard.MarketTray;
import it.polimi.ingsw.server.model.resources.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MarketTrayTest {
    MarketTray marketTray;

    @BeforeEach
    void populateMarketTray() {
        Resource[][] marketTrayTemp = new Resource[][]{
                {Coin.getInstance(), Any.getInstance(), Stone.getInstance(), Servant.getInstance()},
                {Stone.getInstance(), Shield.getInstance(), Faith.getInstance(), Any.getInstance()},
                {Any.getInstance(), Any.getInstance(), Servant.getInstance(), Coin.getInstance()},
        };
        marketTray = new MarketTray(marketTrayTemp, Shield.getInstance());

    }

    @Test
    void randomSelectRow() {

        MarketTray marketTray1 = null;
        try {
            marketTray1 = new MarketTray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert marketTray1 != null;
        Resource[][] oldTray = marketTray1.getMarketTray();
        Resource oldSlide = marketTray1.getSlide();

        Random r = new Random();
        int a = r.nextInt(2 + 1);
        List<Resource> expectedRow = marketTray1.getRow(a);

        /*System.out.printf("[%s]\n", marketTray1.getSlide());
        for(int i = 0; i< marketTray1.getMarketTray().length; i++){
            for(int j = 0; j < marketTray1.getMarketTray()[i].length; j++){
                System.out.printf("%5s\t|  ", marketTray1.getMarketTray()[i][j]);
            }
            System.out.printf("\n");
        }*/

        assertEquals(expectedRow, marketTray1.selectRow(a));

        List<Resource> newRow = new ArrayList<>();
        for (int i = 1; i < expectedRow.size(); i++) {
            newRow.add(expectedRow.get(i));
        }
        newRow.add(oldSlide);


        assertEquals(newRow, marketTray1.getRow(a));
        assertEquals(expectedRow.get(0), marketTray1.getSlide());


    }

    @Test
    void randomSelectColumn() {

        MarketTray marketTray1 = null;
        try {
            marketTray1 = new MarketTray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert marketTray1 != null;
        Resource[][] oldTray = marketTray1.getMarketTray();
        Resource oldSlide = marketTray1.getSlide();

        Random r = new Random();
        int a = r.nextInt(3 + 1);
        List<Resource> expectedColumn = marketTray1.getColumn(a);


        assertEquals(expectedColumn, marketTray1.selectColumn(a));

        List<Resource> newColumn = new ArrayList<>();
        for (int i = 1; i < expectedColumn.size(); i++) {
            newColumn.add(expectedColumn.get(i));
        }
        newColumn.add(oldSlide);


        assertEquals(newColumn, marketTray1.getColumn(a));
        assertEquals(marketTray1.getSlide(), expectedColumn.get(0));
    }


    @Test
    void selectRowTest() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> marketTray.selectRow(3));

        List<Resource> expectedRow = Arrays.asList(Coin.getInstance(), Any.getInstance(), Stone.getInstance(), Servant.getInstance());
        assertEquals(expectedRow, marketTray.selectRow(0));

        List<Resource> newRow = Arrays.asList(Any.getInstance(), Stone.getInstance(), Servant.getInstance(), Shield.getInstance());
        assertEquals(newRow, marketTray.getRow(0));

        assertEquals(Coin.getInstance(), marketTray.getSlide());
    }

    @Test
    void selectColumnTest() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> marketTray.selectColumn(4));

        List<Resource> expectedList = Arrays.asList(Coin.getInstance(), Stone.getInstance(), Any.getInstance());
        assertEquals(expectedList, marketTray.selectColumn(0));

        List<Resource> newColumn = Arrays.asList(Stone.getInstance(), Any.getInstance(), Shield.getInstance());
        assertEquals(newColumn, marketTray.getColumn(0));

        assertEquals(Coin.getInstance(), marketTray.getSlide());
    }

}