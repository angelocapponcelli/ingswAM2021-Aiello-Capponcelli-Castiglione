package it.polimi.ingsw.server.model.globalBoard;

import it.polimi.ingsw.server.model.misc.Deck;

/**
 * TO DO
 */
public class DevelopmentCardGrid {
    private final Deck[][] grid;

    public DevelopmentCardGrid() {
        this.grid = new Deck[3][4];
    }


    public Deck getDeck(Integer integer1, Integer integer2) {
        return this.grid[integer1][integer2];
    }

    public Deck[][] getGrid() {
        return grid;
    }
}
