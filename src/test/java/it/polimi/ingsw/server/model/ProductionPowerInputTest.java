package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.exceptions.DepotException;
import it.polimi.ingsw.server.model.resources.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductionPowerInputTest {
    RealPlayer player;
    ProductionPowerInput productionPowerInput;

    @BeforeEach
    void init() throws DepotException {
        player = new RealPlayer("Micheal");
        player.getPersonalBoard().getWareHouseDepot().addResource(ResourceType.COIN, 2, 2);
        player.getPersonalBoard().getWareHouseDepot().addResource(ResourceType.SHIELD, 1, 1);
        player.getPersonalBoard().getWareHouseDepot().addResource(ResourceType.SERVANT, 1, 0);
        player.getPersonalBoard().getSpecialDepots().addSpecialContainer(ResourceType.SHIELD);
        player.getPersonalBoard().getSpecialDepots().addResources(ResourceType.SHIELD, 1);
        player.getPersonalBoard().getStrongBoxDepot().addResources(ResourceType.COIN, 10);
    }

    @Test
    void CheckTest() {
        productionPowerInput = new ProductionPowerInput();
        productionPowerInput.add(ResourceType.getResourceClass(ResourceType.COIN), 3);
        assertTrue(productionPowerInput.check(player));
        productionPowerInput = new ProductionPowerInput();
        productionPowerInput.add(ResourceType.getResourceClass(ResourceType.COIN), 0);
        assertTrue(productionPowerInput.check(player));
        productionPowerInput = new ProductionPowerInput();
        productionPowerInput.add(ResourceType.getResourceClass(ResourceType.COIN), 12);
        assertTrue(productionPowerInput.check(player));
        productionPowerInput = new ProductionPowerInput();
        productionPowerInput.add(ResourceType.getResourceClass(ResourceType.COIN), 13);
        assertFalse(productionPowerInput.check(player));
    }

    @Test
    void PayTestOneWareHouseElement() throws DepotException {
        productionPowerInput = new ProductionPowerInput();
        productionPowerInput.add(ResourceType.getResourceClass(ResourceType.COIN), 1);
        assertEquals(0, player.getPersonalBoard().getSpecialDepots().getSpecificResourceCount(ResourceType.COIN));
        productionPowerInput.pay(player);
        assertEquals(1, player.getPersonalBoard().getWareHouseDepot().getSpecificResourceCount(ResourceType.COIN));
        assertEquals(10, player.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.COIN));
    }

    @Test
    void PayTestMultipleElement() throws DepotException {
        productionPowerInput = new ProductionPowerInput();
        productionPowerInput.add(ResourceType.getResourceClass(ResourceType.COIN), 5);
        productionPowerInput.add(ResourceType.getResourceClass(ResourceType.SHIELD), 1);
        productionPowerInput.add(ResourceType.getResourceClass(ResourceType.SERVANT), 1);
        productionPowerInput.pay(player);
        //Check right Coin number in each depots
        assertEquals(0, player.getPersonalBoard().getWareHouseDepot().getSpecificResourceCount(ResourceType.COIN));
        assertEquals(0, player.getPersonalBoard().getSpecialDepots().getSpecificResourceCount(ResourceType.COIN));
        assertEquals(7, player.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.COIN));
        //Check right Shield number in each depots
        assertEquals(0, player.getPersonalBoard().getWareHouseDepot().getSpecificResourceCount(ResourceType.SHIELD));
        assertEquals(1, player.getPersonalBoard().getSpecialDepots().getSpecificResourceCount(ResourceType.SHIELD));
        assertEquals(0, player.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.SHIELD));
        //Check right Servant number in each depots
        assertEquals(0, player.getPersonalBoard().getWareHouseDepot().getSpecificResourceCount(ResourceType.SERVANT));
        assertEquals(0, player.getPersonalBoard().getSpecialDepots().getSpecificResourceCount(ResourceType.SERVANT));
        assertEquals(0, player.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.SERVANT));
        //Check right Stone number in each depots
        assertEquals(0, player.getPersonalBoard().getWareHouseDepot().getSpecificResourceCount(ResourceType.STONE));
        assertEquals(0, player.getPersonalBoard().getSpecialDepots().getSpecificResourceCount(ResourceType.STONE));
        assertEquals(0, player.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.STONE));
    }
}