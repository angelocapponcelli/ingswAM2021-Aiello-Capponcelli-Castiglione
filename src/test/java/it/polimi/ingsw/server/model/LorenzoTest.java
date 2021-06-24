package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.server.model.interfaces.Revealable;
import it.polimi.ingsw.server.model.player.Lorenzo;
import it.polimi.ingsw.server.model.soloToken.BlackCross1;
import it.polimi.ingsw.server.model.soloToken.BlackCross2;
import it.polimi.ingsw.server.model.soloToken.Discarder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LorenzoTest {
    Lorenzo lorenzo;

    @BeforeEach
    void init() {
        lorenzo = new Lorenzo("Lorenzo Von Matterhorn", new GameController(1,1));
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
        int tmp2;
        int countBlack1=0;
        int countBlack2=0;
        for (tmp2 = 0; tmp2 < 7; tmp2++) {
            if (blackCross1.getClass().equals(lorenzo.getSoloTokenDeck().get(tmp2).getClass())) {
                lorenzo.increaseFaithPosition();
                countBlack1++;
            }
            if (blackCross2.getClass().equals(lorenzo.getSoloTokenDeck().get(tmp2).getClass())) {
                lorenzo.increaseFaithPosition();
                lorenzo.increaseFaithPosition();
                countBlack2++;
            }
        }
        assertEquals(4,lorenzo.getFaithPosition() );
        assertEquals(2, countBlack1);
        assertEquals(1, countBlack2);
    }

    @Test
    void getSoloDeck(){
        List<Revealable> tmp= lorenzo.getSoloTokenDeck();
        assertEquals(7, tmp.size());
        int temporary=0;
        BlackCross1 blackCross1= new BlackCross1();
        for (Revealable revealable:tmp){
            if(revealable.getClass().equals(blackCross1.getClass())){
                temporary++;
            }
        }
        assertEquals(2, temporary);
    }
}