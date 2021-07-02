package it.polimi.ingsw.server.model.player;

import it.polimi.ingsw.server.controller.GameController;
import org.junit.jupiter.api.Test;

/**
 * Tests Lorenzo
 */
class LorenzoTest {

    @Test
    void test1(){
        Lorenzo lorenzo = new Lorenzo(new GameController(1,1));
        lorenzo.reveal();

    }

}