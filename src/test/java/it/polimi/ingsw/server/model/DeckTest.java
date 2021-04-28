package it.polimi.ingsw.server.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    Deck deck;
    @BeforeEach
    void init(){
        DevelopmentCard developmentCard= new DevelopmentCard(1, new Cost(),new TypeLevel(Colors.YELLOW,1),new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()),14);
        DevelopmentCard developmentCard1= new DevelopmentCard(2, new Cost(),new TypeLevel(Colors.YELLOW,2),new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()),14);
        DevelopmentCard developmentCard2= new DevelopmentCard(3, new Cost(),new TypeLevel(Colors.YELLOW,3),new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()),2);
        deck= new Deck();
        deck.getDeck().add(developmentCard);
        deck.getDeck().add(developmentCard1);
        deck.getDeck().add(developmentCard2);

    }

    @Test
    void getVictoryPoint() {
        assertEquals(30, deck.getVictoryPoint());
        DevelopmentCard developmentCard3= new DevelopmentCard(4, new Cost(),new TypeLevel(Colors.YELLOW,1),new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()),10);
        deck.getDeck().add(developmentCard3);
        assertEquals(40, deck.getVictoryPoint());

    }
}