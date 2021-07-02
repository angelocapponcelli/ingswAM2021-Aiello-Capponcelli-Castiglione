package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.server.model.player.Lorenzo;
import it.polimi.ingsw.server.model.soloToken.BlackCross2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests Black cross 2 solo token
 */
class BlackCross2Test {
    Lorenzo lorenzo;
    BlackCross2 blackCross2;

    /**
     * Initializes lorenzo and a black cross 2
     */
    @BeforeEach
    void init() {

        lorenzo = new Lorenzo(new GameController(1, 1));
        blackCross2 = new BlackCross2();
    }

    /**
     * checks that lorenzo advances by two positions when revealed
     */
    @Test
    void onReveal() {
        assertEquals(0, lorenzo.getFaithPosition());
        blackCross2.onReveal(lorenzo);
        assertEquals(2, lorenzo.getFaithPosition());
        lorenzo.increaseFaithPosition();
        blackCross2.onReveal(lorenzo);
        assertEquals(5, lorenzo.getFaithPosition());

    }
}