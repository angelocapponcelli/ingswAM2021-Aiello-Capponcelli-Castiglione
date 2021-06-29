package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerOutput;
import it.polimi.ingsw.server.model.resources.ResourceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductionPowerOutputTest {

    ProductionPowerOutput productionPowerOutput;
    RealPlayer realPlayer;


    @Test
    void test1() {


        productionPowerOutput = new ProductionPowerOutput();
        productionPowerOutput.add(ResourceType.STONE, 2);
        productionPowerOutput.add(ResourceType.SHIELD, 1);
        realPlayer = new RealPlayer("mrPink");


        assertEquals(realPlayer.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.STONE), 0);
        assertEquals(realPlayer.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.COIN), 0);
        assertEquals(realPlayer.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.SHIELD), 0);
        assertEquals(realPlayer.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.SERVANT), 0);

        productionPowerOutput.onActivation(realPlayer);

        assertEquals(realPlayer.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.STONE), 2);
        assertEquals(realPlayer.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.COIN), 0);
        assertEquals(realPlayer.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.SHIELD), 1);
        assertEquals(realPlayer.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.SERVANT), 0);

    }

    @Test
    void test2() {

        productionPowerOutput = new ProductionPowerOutput();
        productionPowerOutput.add(ResourceType.SERVANT, 1);
        productionPowerOutput.add(ResourceType.SHIELD, 3);
        productionPowerOutput.add(ResourceType.COIN, 4);
        realPlayer = new RealPlayer("mrWhite");


        assertEquals(realPlayer.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.STONE), 0);
        assertEquals(realPlayer.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.COIN), 0);
        assertEquals(realPlayer.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.SHIELD), 0);
        assertEquals(realPlayer.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.SERVANT), 0);

        productionPowerOutput.onActivation(realPlayer);

        assertEquals(realPlayer.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.STONE), 0);
        assertEquals(realPlayer.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.COIN), 4);
        assertEquals(realPlayer.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.SHIELD), 3);
        assertEquals(realPlayer.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.SERVANT), 1);


    }


}