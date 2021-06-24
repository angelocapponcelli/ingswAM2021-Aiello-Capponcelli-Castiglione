package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Coin;
import it.polimi.ingsw.server.model.resources.Shield;
import it.polimi.ingsw.server.model.specialAbilities.SpecialAdditionalProductionPower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecialAdditionalProductionPowerTest {
    SpecialAdditionalProductionPower special1;
    SpecialAdditionalProductionPower special2;
    RealPlayer realPlayer;

    @BeforeEach
    void init(){
        special1= new SpecialAdditionalProductionPower(Coin.getInstance());
        special2= new SpecialAdditionalProductionPower(Shield.getInstance());
        realPlayer= new RealPlayer("dwight", new GameController(4,23));

    }

    @Test
    void onActivation(){
        assertEquals(0, realPlayer.getPersonalBoard().getProductionPowers().size());
        special1.onActivation(realPlayer);
        assertEquals(1, realPlayer.getPersonalBoard().getProductionPowers().size());
        special2.onActivation(realPlayer);
    }

}
