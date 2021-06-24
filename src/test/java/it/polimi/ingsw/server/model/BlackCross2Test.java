package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.server.model.globalBoard.DevelopmentCardGrid;
import it.polimi.ingsw.server.model.globalBoard.GlobalBoard;
import it.polimi.ingsw.server.model.globalBoard.MarketTray;
import it.polimi.ingsw.server.model.globalBoard.faithTrack.FaithTrack;
import it.polimi.ingsw.server.model.player.Lorenzo;
import it.polimi.ingsw.server.model.productionPower.ProductionPower;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerInput;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerOutput;
import it.polimi.ingsw.server.model.resources.Coin;
import it.polimi.ingsw.server.model.resources.Resource;
import it.polimi.ingsw.server.model.soloToken.BlackCross1;
import it.polimi.ingsw.server.model.soloToken.BlackCross2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class BlackCross2Test {
    Lorenzo lorenzo;
    BlackCross2 blackCross2;


    @BeforeEach
    void init() {

        lorenzo = new Lorenzo("lorenzo", new GameController(1, 1));
        lorenzo.setSoloTokenDeck();
        blackCross2 = new BlackCross2();
    }

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