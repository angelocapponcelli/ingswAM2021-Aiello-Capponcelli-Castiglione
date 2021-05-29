package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.cards.Cost;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.utils.exceptions.DepotException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CostTest {
    RealPlayer player;
    Cost cost;

    @BeforeEach
    void init() throws DepotException {
        player = new RealPlayer("Fredo");
        player.getPersonalBoard().getWareHouseDepot().addResource(ResourceType.COIN, 2, 2);
        player.getPersonalBoard().getWareHouseDepot().addResource(ResourceType.SHIELD, 1, 1);
        player.getPersonalBoard().getWareHouseDepot().addResource(ResourceType.SERVANT, 1, 0);
        player.getPersonalBoard().getSpecialDepots().addSpecialContainer(ResourceType.SHIELD);
        player.getPersonalBoard().getSpecialDepots().addResources(ResourceType.SHIELD, 1);
        player.getPersonalBoard().getStrongBoxDepot().addResources(ResourceType.COIN, 10);
    }

    @Test
    void CheckTest() {
        cost = new Cost();
        cost.add(ResourceType.COIN, 3);
        assertTrue(cost.check(player));
        cost = new Cost();
        cost.add(ResourceType.COIN, 0);
        assertTrue(cost.check(player));
        cost = new Cost();
        cost.add(ResourceType.COIN, 12);
        assertTrue(cost.check(player));
        cost = new Cost();
        cost.add(ResourceType.COIN, 13);
        assertFalse(cost.check(player));
    }

    @Test
    void PayTestOneWareHouseElement() throws DepotException {
        cost = new Cost();
        cost.add(ResourceType.COIN, 1);
        assertEquals(0, player.getPersonalBoard().getSpecialDepots().getSpecificResourceCount(ResourceType.COIN));
        cost.pay(player);
        assertEquals(1, player.getPersonalBoard().getWareHouseDepot().getSpecificResourceCount(ResourceType.COIN));
        assertEquals(10, player.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.COIN));
    }

    @Test
    void PayTestMultipleElement() throws DepotException {
        cost = new Cost();
        cost.add(ResourceType.COIN, 5);
        cost.add(ResourceType.SHIELD, 1);
        cost.add(ResourceType.SERVANT, 1);
        cost.pay(player);
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