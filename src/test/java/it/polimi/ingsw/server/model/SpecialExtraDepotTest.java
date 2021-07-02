package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.server.model.personalBoard.resourceContainers.SpecialContainer;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Coin;
import it.polimi.ingsw.server.model.resources.Shield;
import it.polimi.ingsw.server.model.specialAbilities.SpecialExtraDepot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Special extra depot test
 */
class SpecialExtraDepotTest {
    SpecialExtraDepot specialExtraDepot1;
    SpecialExtraDepot specialExtraDepot2;
    RealPlayer realPlayer;

    /**
     * Initializes real player and depots
     */
    @BeforeEach
    void init(){
        realPlayer= new RealPlayer("Christian Eriksen", new GameController(3,0));
        specialExtraDepot1= new SpecialExtraDepot(Coin.getInstance(),2);
        specialExtraDepot2= new SpecialExtraDepot(Shield.getInstance(),2);
    }

    /**
     * Tests on activation method
     */
    @Test
    void onActivation(){
        int i=0;
        for(SpecialContainer specialContainer: realPlayer.getPersonalBoard().getSpecialDepots().getSpecialContainers()){
            i++;
        }
        assertEquals(0,i);
        i=0;
        specialExtraDepot2.onActivation(realPlayer);
        for(SpecialContainer specialContainer: realPlayer.getPersonalBoard().getSpecialDepots().getSpecialContainers()){
            i++;
        }
        assertEquals(1, i);
        specialExtraDepot1.onActivation(realPlayer);
        i=0;
        for(SpecialContainer specialContainer: realPlayer.getPersonalBoard().getSpecialDepots().getSpecialContainers()){
            i++;
        }
        assertEquals(2,i);
    }

}