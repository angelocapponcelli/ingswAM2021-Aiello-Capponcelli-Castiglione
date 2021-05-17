package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.player.Lorenzo;
import it.polimi.ingsw.server.model.soloToken.BlackCross1;
import it.polimi.ingsw.server.model.soloToken.BlackCross2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LorenzoTest {
    Lorenzo lorenzo;

    @BeforeEach
    void init() {
        lorenzo = new Lorenzo("Lorenzo Von Matterhorn");
        lorenzo.setSoloTokenDeck();

    }

    @Test
    void removeFromSoloTokenDeck() {
        lorenzo.removeFromSoloTokenDeck(lorenzo.reveal());
        assertEquals(6, lorenzo.getSoloTokenDeck().size());
        lorenzo.removeFromSoloTokenDeck(lorenzo.reveal());
        assertEquals(5, lorenzo.getSoloTokenDeck().size());
    }

    @Test
    void setSoloTokenDeck() {
        lorenzo.setSoloTokenDeck();
        assertEquals(7, lorenzo.getSoloTokenDeck().size());
        BlackCross1 blackCross1 = new BlackCross1();
        BlackCross2 blackCross2 = new BlackCross2();
        int tmp1 = 0;
        int tmp2;
        int tmp3 = 0;
        for (tmp2 = 0; tmp2 < 7; tmp2++) {
            if (blackCross1.getClass().equals(lorenzo.getSoloTokenDeck().get(tmp2).getClass())) {
                tmp1++;
            }
            if (blackCross2.getClass().equals(lorenzo.getSoloTokenDeck().get(tmp2).getClass())) {
                tmp3++;
            }
        }
        assertEquals(2, tmp1);
        assertEquals(1, tmp3);
    }
}