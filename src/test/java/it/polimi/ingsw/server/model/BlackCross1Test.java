package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.server.model.globalBoard.DevelopmentCardGrid;
import it.polimi.ingsw.server.model.globalBoard.GlobalBoard;
import it.polimi.ingsw.server.model.globalBoard.MarketTray;
import it.polimi.ingsw.server.model.globalBoard.faithTrack.FaithTrack;
import it.polimi.ingsw.server.model.interfaces.Revealable;
import it.polimi.ingsw.server.model.player.Lorenzo;
import it.polimi.ingsw.server.model.player.Player;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.productionPower.ProductionPower;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerInput;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerOutput;
import it.polimi.ingsw.server.model.resources.Coin;
import it.polimi.ingsw.server.model.resources.Resource;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.server.model.soloToken.BlackCross1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class BlackCross1Test {
    Lorenzo lorenzo;
    BlackCross1 blackCross1;


    @BeforeEach
    void init(){
        lorenzo= new Lorenzo("lorenzo", new GameController(1,1));
        lorenzo.setSoloTokenDeck();
        blackCross1=new BlackCross1();
    }

    @Test
    void onReveal(){
        assertEquals(0, lorenzo.getFaithPosition());
        blackCross1.onReveal(lorenzo);
        assertEquals(1, lorenzo.getFaithPosition());
        int i=0;
        for (Revealable re:
             lorenzo.getSoloTokenDeck()) {
            i++;
        }
        assertEquals(7,i);
        blackCross1.onReveal(lorenzo);
        blackCross1.onReveal(lorenzo);
        assertEquals(3, lorenzo.getFaithPosition());
    }

}