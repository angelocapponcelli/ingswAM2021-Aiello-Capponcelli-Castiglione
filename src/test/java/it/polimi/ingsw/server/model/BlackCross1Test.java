package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.server.model.player.Lorenzo;
import it.polimi.ingsw.server.model.soloToken.BlackCross1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class BlackCross1Test {
    Lorenzo lorenzo;
    BlackCross1 blackCross1;


    @BeforeEach
    void init(){
        lorenzo= new Lorenzo(new GameController(1,1));
        blackCross1 = new BlackCross1();
    }

    @Test
    void onReveal(){
        assertEquals(0, lorenzo.getFaithPosition());
        blackCross1.onReveal(lorenzo);
        assertEquals(1, lorenzo.getFaithPosition());
        blackCross1.onReveal(lorenzo);
        blackCross1.onReveal(lorenzo);
        assertEquals(3, lorenzo.getFaithPosition());
    }

}