package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.resources.Coin;
import it.polimi.ingsw.server.model.resources.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InHandLeaderCardTest {

    InHandLeaderCard inHandLeaderCard;
    RealPlayer player;
    @BeforeEach
    void init() {
        inHandLeaderCard = new InHandLeaderCard( new ArrayList<>());
        PersonalBoard personalBoard = new PersonalBoard( new PersonalDevelopmentBoard(), inHandLeaderCard, new ArrayList<>());
        player = new RealPlayer("Player1", 0, new VaticanReportStatus(new HashMap<>()), personalBoard, true, 0);
    }

    @Test
    void testGetSpecialAbility(){
        /** TODO:
         * - Instance inHandLeaderCard with known Card
         * - Play each card so activate special abilities
         * - check if method getEnabledAbilities return right avtivated abilities
         */
        player.getPersonalBoard().getInHandLeaderCard().getCard(0).getSpecialAbility();
        player.getPersonalBoard().getInHandLeaderCard().getCard(0).playCard(player);
        List<SpecialAbility> specialAbilityListExpected = new ArrayList<>();
        //specialAbilityListExpected.add(new Discount(Coin.getInstance())); // TODO add special ability expected
        assertEquals(specialAbilityListExpected, player.getPersonalBoard().getInHandLeaderCard().getEnabledAbilities());
    }

}