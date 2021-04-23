package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.resources.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class CostTest {
    RealPlayer player;
    Cost cost;

    @BeforeEach
    void init() throws DepotException {
        List<Depot> depotForMarket = new ArrayList<>();
        PersonalBoard personalBoard = new PersonalBoard( new PersonalDevelopmentBoard(), new InHandLeaderCard(new ArrayList<>()), new ArrayList<>());
        player = new RealPlayer("Player1", 0, new VaticanReportStatus(new HashMap<>()), personalBoard, true, 0);
        player.getPersonalBoard().getWarehouseDepot().get(0).add(ResourceType.COIN, 2, 2);
        player.getPersonalBoard().getWarehouseDepot().get(0).add(ResourceType.SHIELD, 1, 1);
        player.getPersonalBoard().getWarehouseDepot().get(0).add(ResourceType.SERVANT, 1, 0);
        player.getPersonalBoard().getSpecialDepot().get(0).addContainer(ResourceType.SHIELD);
        player.getPersonalBoard().getSpecialDepot().get(0).add(ResourceType.SHIELD, 1);
        player.getPersonalBoard().getStrongBoxDepot().add(ResourceType.COIN, 10);
    }

    @Test
    void CheckTest(){
        cost = new Cost();
        cost.add(ResourceType.COIN, 3);
        assertEquals(true, cost.check(player) );
        cost = new Cost();
        cost.add(ResourceType.COIN, 0);
        assertEquals(true, cost.check(player) );
        cost = new Cost();
        cost.add(ResourceType.COIN, 12);
        assertEquals(true, cost.check(player) );
        cost = new Cost();
        cost.add(ResourceType.COIN, 13);
        assertEquals(false, cost.check(player) );
    }

    @Test
    void PayTestOneWareHouseElement() throws DepotException {
        cost = new Cost();
        cost.add(ResourceType.COIN, 1);
        assertEquals(0, player.getPersonalBoard().getSpecialDepot().get(0).getResourceCount(Coin.getInstance()));
        cost.pay(player);
        assertEquals(1, player.getPersonalBoard().getWarehouseDepot().get(0).getResourceCount(Coin.getInstance()));
        assertEquals(10, player.getPersonalBoard().getStrongBoxDepot().getResourceCount(Coin.getInstance()));
    }

    @Test
    void PayTestMultipleElement() throws DepotException {
        cost = new Cost();
        cost.add(ResourceType.COIN, 5);
        cost.add(ResourceType.SHIELD, 1);
        cost.add(ResourceType.SERVANT, 1);
        cost.pay(player);
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