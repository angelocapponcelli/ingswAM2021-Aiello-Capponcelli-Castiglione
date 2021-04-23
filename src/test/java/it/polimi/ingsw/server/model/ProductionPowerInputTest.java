package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.resources.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductionPowerInputTest {
    RealPlayer player;
    ProductionPowerInput productionPowerInput;

    @BeforeEach
    void init() throws DepotException {
        List<Depot> depotForMarket = new ArrayList<>();
        depotForMarket.add(new WareHouseDepot());
        depotForMarket.add(new SpecialDepot());
        PersonalBoard personalBoard = new PersonalBoard(depotForMarket, new StrongBoxDepot(), new PersonalDevelopmentBoard(), new InHandLeaderCard(new ArrayList<>()), new ArrayList<>());
        player = new RealPlayer("Player1", personalBoard, true);
        player.getPersonalBoard().getWarehouseDepot().get(0).add(ResourceType.COIN, 2, 2);
        player.getPersonalBoard().getWarehouseDepot().get(0).add(ResourceType.SHIELD, 1, 1);
        player.getPersonalBoard().getWarehouseDepot().get(0).add(ResourceType.SERVANT, 1, 0);
        player.getPersonalBoard().getSpecialDepot().get(0).addContainer(ResourceType.SHIELD);
        player.getPersonalBoard().getSpecialDepot().get(0).add(ResourceType.SHIELD, 1);
        player.getPersonalBoard().getStrongBoxDepot().add(ResourceType.COIN, 10);
    }

    @Test
    void CheckTest() {
        productionPowerInput = new ProductionPowerInput();
        productionPowerInput.add(ResourceType.COIN, 3);
        assertTrue(productionPowerInput.check(player));
        productionPowerInput = new ProductionPowerInput();
        productionPowerInput.add(ResourceType.COIN, 0);
        assertTrue(productionPowerInput.check(player));
        productionPowerInput = new ProductionPowerInput();
        productionPowerInput.add(ResourceType.COIN, 12);
        assertTrue(productionPowerInput.check(player));
        productionPowerInput = new ProductionPowerInput();
        productionPowerInput.add(ResourceType.COIN, 13);
        assertFalse(productionPowerInput.check(player));
    }

    @Test
    void PayTestOneWareHouseElement() throws DepotException {
        productionPowerInput = new ProductionPowerInput();
        productionPowerInput.add(ResourceType.COIN, 1);
        assertEquals(0, player.getPersonalBoard().getSpecialDepot().get(0).getResourceCount(Coin.getInstance()));
        productionPowerInput.pay(player);
        assertEquals(1, player.getPersonalBoard().getWarehouseDepot().get(0).getResourceCount(Coin.getInstance()));
        assertEquals(10, player.getPersonalBoard().getStrongBoxDepot().getResourceCount(Coin.getInstance()));
    }

    @Test
    void PayTestMultipleElement() throws DepotException {
        productionPowerInput = new ProductionPowerInput();
        productionPowerInput.add(ResourceType.COIN, 5);
        productionPowerInput.add(ResourceType.SHIELD, 1);
        productionPowerInput.add(ResourceType.SERVANT, 1);
        productionPowerInput.pay(player);
        //Check right Coin number in each depots
        assertEquals(0, player.getPersonalBoard().getSpecialDepot().get(0).getResourceCount(Coin.getInstance()));
        assertEquals(0, player.getPersonalBoard().getWarehouseDepot().get(0).getResourceCount(Coin.getInstance()));
        assertEquals(7, player.getPersonalBoard().getStrongBoxDepot().getResourceCount(Coin.getInstance()));
        //Check right Coin number in each depots
        assertEquals(0, player.getPersonalBoard().getSpecialDepot().get(0).getResourceCount(Shield.getInstance()));
        assertEquals(1, player.getPersonalBoard().getWarehouseDepot().get(0).getResourceCount(Shield.getInstance()));
        assertEquals(0, player.getPersonalBoard().getStrongBoxDepot().getResourceCount(Shield.getInstance()));
        //Check right Coin number in each depots
        assertEquals(0, player.getPersonalBoard().getSpecialDepot().get(0).getResourceCount(Servant.getInstance()));
        assertEquals(0, player.getPersonalBoard().getWarehouseDepot().get(0).getResourceCount(Servant.getInstance()));
        assertEquals(0, player.getPersonalBoard().getStrongBoxDepot().getResourceCount(Servant.getInstance()));
        //Check right Coin number in each depots
        assertEquals(0, player.getPersonalBoard().getSpecialDepot().get(0).getResourceCount(Stone.getInstance()));
        assertEquals(0, player.getPersonalBoard().getWarehouseDepot().get(0).getResourceCount(Stone.getInstance()));
        assertEquals(0, player.getPersonalBoard().getStrongBoxDepot().getResourceCount(Stone.getInstance()));
    }
}