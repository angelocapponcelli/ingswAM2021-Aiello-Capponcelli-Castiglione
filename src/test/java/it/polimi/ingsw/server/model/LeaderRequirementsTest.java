package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.exceptions.DepotException;
import it.polimi.ingsw.server.model.resources.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeaderRequirementsTest {

    LeaderRequirements leaderRequirements;
    RealPlayer player;

    @BeforeEach
    void init(){
        player = new RealPlayer("player1");
        leaderRequirements = new LeaderRequirements();
    }

    @Test
    void resourceStrongboxRequirement() throws DepotException {
        player.getPersonalBoard().getStrongBoxDepot().add(ResourceType.COIN, 20);
        player.getPersonalBoard().getStrongBoxDepot().add(ResourceType.SHIELD, 10);
        player.getPersonalBoard().getStrongBoxDepot().add(ResourceType.SERVANT, 5);
        player.getPersonalBoard().getStrongBoxDepot().add(ResourceType.STONE, 2);

        leaderRequirements.add(Coin.getInstance(), 10);
        assertTrue(leaderRequirements.check(player));
        leaderRequirements.add(Shield.getInstance(), 10);
        assertTrue(leaderRequirements.check(player));
        leaderRequirements.add(Servant.getInstance(), 10);
        assertFalse(leaderRequirements.check(player));
        leaderRequirements.add(Stone.getInstance(), 1);
        assertFalse(leaderRequirements.check(player));
    }

    @Test
    void resourceWareHouseDepotRequirement() throws DepotException {
        player.getPersonalBoard().getWareHouseDepot().get(0).add(ResourceType.COIN, 3, 2);
        player.getPersonalBoard().getWareHouseDepot().get(0).add(ResourceType.SHIELD, 1, 1);
        player.getPersonalBoard().getWareHouseDepot().get(0).add(ResourceType.SERVANT, 1, 0);

        leaderRequirements.add(Coin.getInstance(), 2);
        assertTrue(leaderRequirements.check(player));
        leaderRequirements.add(Shield.getInstance(), 1);
        assertTrue(leaderRequirements.check(player));
        leaderRequirements.add(Servant.getInstance(), 2);
        assertFalse(leaderRequirements.check(player));
        leaderRequirements.add(Stone.getInstance(), 1);
        assertFalse(leaderRequirements.check(player));
    }

    @Test
    void resourceSpecialDepotRequirement() throws DepotException {
        player.getPersonalBoard().getSpecialDepot().get(0).addContainer(ResourceType.COIN);
        player.getPersonalBoard().getSpecialDepot().get(0).addContainer(ResourceType.SHIELD);
        player.getPersonalBoard().getSpecialDepot().get(0).addContainer(ResourceType.SERVANT);
        player.getPersonalBoard().getSpecialDepot().get(0).addContainer(ResourceType.STONE);
        player.getPersonalBoard().getSpecialDepot().get(0).add(ResourceType.COIN, 2);
        player.getPersonalBoard().getSpecialDepot().get(0).add(ResourceType.SHIELD, 2);
        player.getPersonalBoard().getSpecialDepot().get(0).add(ResourceType.SERVANT, 1);

        leaderRequirements.add(Coin.getInstance(), 2);
        assertTrue(leaderRequirements.check(player));
        leaderRequirements.add(Shield.getInstance(), 1);
        assertTrue(leaderRequirements.check(player));
        leaderRequirements.add(Servant.getInstance(), 2);
        assertFalse(leaderRequirements.check(player));
        leaderRequirements.add(Stone.getInstance(), 1);
        assertFalse(leaderRequirements.check(player));
    }

    @Test
    void resourceRequirement() throws DepotException {
        player.getPersonalBoard().getSpecialDepot().get(0).addContainer(ResourceType.COIN);
        player.getPersonalBoard().getSpecialDepot().get(0).addContainer(ResourceType.SHIELD);
        player.getPersonalBoard().getSpecialDepot().get(0).addContainer(ResourceType.SERVANT);
        player.getPersonalBoard().getSpecialDepot().get(0).addContainer(ResourceType.STONE);
        player.getPersonalBoard().getSpecialDepot().get(0).add(ResourceType.COIN, 2);
        player.getPersonalBoard().getSpecialDepot().get(0).add(ResourceType.SHIELD, 2);
        player.getPersonalBoard().getSpecialDepot().get(0).add(ResourceType.SERVANT, 1);
        player.getPersonalBoard().getWareHouseDepot().get(0).add(ResourceType.COIN, 3, 2);
        player.getPersonalBoard().getWareHouseDepot().get(0).add(ResourceType.SHIELD, 1, 1);
        player.getPersonalBoard().getWareHouseDepot().get(0).add(ResourceType.SERVANT, 1, 0);
        player.getPersonalBoard().getStrongBoxDepot().add(ResourceType.COIN, 20);
        player.getPersonalBoard().getStrongBoxDepot().add(ResourceType.SHIELD, 10);
        player.getPersonalBoard().getStrongBoxDepot().add(ResourceType.SERVANT, 5);
        player.getPersonalBoard().getStrongBoxDepot().add(ResourceType.STONE, 2);

        leaderRequirements.add(Coin.getInstance(), 25);
        assertTrue(leaderRequirements.check(player));
        leaderRequirements.add(Shield.getInstance(), 13);
        assertTrue(leaderRequirements.check(player));
        leaderRequirements.add(Servant.getInstance(), 6);
        assertTrue(leaderRequirements.check(player));
        leaderRequirements.add(Stone.getInstance(), 3);
        assertFalse(leaderRequirements.check(player));
    }

    @Test
    void typeLevelTest_1() {
        player.getPersonalBoard().getPersonalDevelopmentBoard().addCard(0, new DevelopmentCard(1, null, new TypeLevel(Colors.GREEN, 1), null, 1));
        player.getPersonalBoard().getPersonalDevelopmentBoard().addCard(1, new DevelopmentCard(1, null, new TypeLevel(Colors.GREEN, 1), null, 1));
        player.getPersonalBoard().getPersonalDevelopmentBoard().addCard(2, new DevelopmentCard(1, null, new TypeLevel(Colors.GREEN, 2), null, 1));
        player.getPersonalBoard().getPersonalDevelopmentBoard().addCard(0, new DevelopmentCard(1, null, new TypeLevel(Colors.YELLOW, 3), null, 1));
        player.getPersonalBoard().getPersonalDevelopmentBoard().addCard(1, new DevelopmentCard(1, null, new TypeLevel(Colors.PURPLE, 1), null, 1));
        player.getPersonalBoard().getPersonalDevelopmentBoard().addCard(2, new DevelopmentCard(1, null, new TypeLevel(Colors.PURPLE, 2), null, 1));
        player.getPersonalBoard().getPersonalDevelopmentBoard().addCard(0, new DevelopmentCard(1, null, new TypeLevel(Colors.PURPLE, 3), null, 1));

        leaderRequirements.add(new TypeLevel(Colors.GREEN,0), 2);
        assertTrue(leaderRequirements.check(player));
        leaderRequirements.add(new TypeLevel(Colors.YELLOW,0), 1);
        assertTrue(leaderRequirements.check(player));
        leaderRequirements.add(new TypeLevel(Colors.PURPLE,0), 1);
        assertTrue(leaderRequirements.check(player));
        leaderRequirements.add(new TypeLevel(Colors.BLUE,0), 1);
        assertFalse(leaderRequirements.check(player));
    }


    @Test
    void typeLevelTest_2() {
        player.getPersonalBoard().getPersonalDevelopmentBoard().addCard(0, new DevelopmentCard(1, null, new TypeLevel(Colors.GREEN, 1), null, 1));
        player.getPersonalBoard().getPersonalDevelopmentBoard().addCard(1, new DevelopmentCard(1, null, new TypeLevel(Colors.GREEN, 1), null, 1));
        player.getPersonalBoard().getPersonalDevelopmentBoard().addCard(2, new DevelopmentCard(1, null, new TypeLevel(Colors.GREEN, 2), null, 1));
        player.getPersonalBoard().getPersonalDevelopmentBoard().addCard(0, new DevelopmentCard(1, null, new TypeLevel(Colors.YELLOW, 3), null, 1));
        player.getPersonalBoard().getPersonalDevelopmentBoard().addCard(1, new DevelopmentCard(1, null, new TypeLevel(Colors.PURPLE, 1), null, 1));
        player.getPersonalBoard().getPersonalDevelopmentBoard().addCard(2, new DevelopmentCard(1, null, new TypeLevel(Colors.PURPLE, 2), null, 1));
        player.getPersonalBoard().getPersonalDevelopmentBoard().addCard(0, new DevelopmentCard(1, null, new TypeLevel(Colors.PURPLE, 3), null, 1));

        leaderRequirements.add(new TypeLevel(Colors.GREEN,1), 2);
        assertTrue(leaderRequirements.check(player));
        leaderRequirements.add(new TypeLevel(Colors.YELLOW,3), 1);
        assertTrue(leaderRequirements.check(player));
        leaderRequirements.add(new TypeLevel(Colors.PURPLE,1), 2);
        assertFalse(leaderRequirements.check(player));
        leaderRequirements.add(new TypeLevel(Colors.BLUE,1), 1);
        assertFalse(leaderRequirements.check(player));
    }

}