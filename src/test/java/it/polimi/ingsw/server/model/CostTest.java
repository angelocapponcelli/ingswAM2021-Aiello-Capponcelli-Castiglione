package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.cards.Cost;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.*;
import it.polimi.ingsw.utils.exceptions.DepotException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Cost test
 */
class CostTest {
    RealPlayer player;
    Cost cost;

    /**
     * Initializes a player and the depots to check the amount of resources. It also populates some depots
     * @throws DepotException if the resource can't be added
     */
    @BeforeEach
    void init() throws DepotException {
        player = new RealPlayer("Fredo");
        player.getPersonalBoard().getWareHouseDepot().addResource(ResourceType.COIN, 2, 2);
        player.getPersonalBoard().getWareHouseDepot().addResource(ResourceType.SHIELD, 1, 1);
        player.getPersonalBoard().getWareHouseDepot().addResource(ResourceType.SERVANT, 1, 0);
        player.getPersonalBoard().getSpecialDepots().addSpecialContainer(ResourceType.SHIELD, 2);
        player.getPersonalBoard().getSpecialDepots().addResources(ResourceType.SHIELD, 1);
        player.getPersonalBoard().getStrongBoxDepot().addResources(ResourceType.COIN, 10);
    }

    /**
     * Creates a new cost class and checks whether it could be possible to pay
     */
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

    /**
     * Creates a cost class and tries to pay. test for the warehouse depot
     * @throws DepotException if cost can't be paid
     */
    @Test
    void PayTestOneWareHouseElement() throws DepotException {
        cost = new Cost();
        cost.add(ResourceType.COIN, 1);
        assertEquals(0, player.getPersonalBoard().getSpecialDepots().getSpecificResourceCount(ResourceType.COIN));
        cost.pay(player);
        assertEquals(1, player.getPersonalBoard().getWareHouseDepot().getSpecificResourceCount(ResourceType.COIN));
        assertEquals(10, player.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(ResourceType.COIN));
    }

    /**
     * Creates a new cost class and tries to pay. Tests all the depots at the same time
     * @throws DepotException if cost can't be paid
     */
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

    /**
     * Test for get cost
     */
    @Test
    void getCost(){
        cost= new Cost();
        cost.add(ResourceType.COIN, 7);
        Map<Resource, Integer> map;
        map= cost.getCost();
        assertFalse(map.isEmpty());
        assertEquals(7, map.get(Coin.getInstance()));
        cost.add(ResourceType.SHIELD, 2);
        map=cost.getCost();
        assertEquals(2, map.get(Shield.getInstance()));
        cost.add(ResourceType.SERVANT, 12);
        map=cost.getCost();
        assertEquals(12, map.get(Servant.getInstance()));
        cost.add(ResourceType.STONE, 2);
        map=cost.getCost();
        assertEquals(2, map.get(Stone.getInstance()));

    }

}