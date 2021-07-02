package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.server.model.interfaces.Revealable;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.player.Lorenzo;
import it.polimi.ingsw.server.model.soloToken.Discarder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the discarder solo token
 */
class DiscarderTest {
    Lorenzo lorenzo = new Lorenzo(new GameController(1,1));
    Revealable greenDiscarder = new Discarder(Colors.GREEN);

    /**
     * Tests onReveal method
     */
    @Test
    void onReveal(){

        int numberOfGreenCard = 12;
        assertEquals(numberOfGreenCard, lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getColumn(Colors.GREEN).stream().mapToInt(deck -> deck.getDeck().size()).sum());



        greenDiscarder.onReveal(lorenzo);
        assertEquals(numberOfGreenCard - 2, lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getColumn(Colors.GREEN).stream().mapToInt(deck -> deck.getDeck().size()).sum());





    }

}