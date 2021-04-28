package it.polimi.ingsw.server.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersonalDevelopmentBoardTest {

    PersonalDevelopmentBoard developmentBoard;

    @BeforeEach
    void init(){
        developmentBoard= new PersonalDevelopmentBoard();
    }


    @Test
    void getVictoryPoint() {
        DevelopmentCard developmentCard= new DevelopmentCard(1, new Cost(),new TypeLevel(Colors.YELLOW,1),new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()),14);
        DevelopmentCard developmentCard1= new DevelopmentCard(2, new Cost(),new TypeLevel(Colors.YELLOW,2),new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()),14);
        DevelopmentCard developmentCard2= new DevelopmentCard(3, new Cost(),new TypeLevel(Colors.YELLOW,3),new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()),2);
        developmentBoard.addCard(1, developmentCard);
        developmentBoard.addCard(1, developmentCard1);
        developmentBoard.addCard(1, developmentCard2);
        DevelopmentCard developmentCard4= new DevelopmentCard(4, new Cost(),new TypeLevel(Colors.GREEN,1),new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()),3);
        DevelopmentCard developmentCard5= new DevelopmentCard(5, new Cost(),new TypeLevel(Colors.GREEN,2),new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()),4);
        DevelopmentCard developmentCard6= new DevelopmentCard(6, new Cost(),new TypeLevel(Colors.GREEN,3),new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()),1);
        developmentBoard.addCard(2, developmentCard4);
        developmentBoard.addCard(2, developmentCard5);
        developmentBoard.addCard(2, developmentCard6);
        assertEquals(38, developmentBoard.getVictoryPoint());
        DevelopmentCard developmentCard7= new DevelopmentCard(7, new Cost(),new TypeLevel(Colors.GREEN,1),new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()),4);
        developmentBoard.addCard(0,developmentCard7);
        assertEquals(42, developmentBoard.getVictoryPoint());
    }

    @Test
    void addCard(){
        DevelopmentCard developmentCard= new DevelopmentCard(1, new Cost(),new TypeLevel(Colors.YELLOW,1),new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()),14);
        developmentBoard.addCard(0,developmentCard);
        DevelopmentCard developmentCard4= new DevelopmentCard(4, new Cost(),new TypeLevel(Colors.GREEN,1),new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()),3);
        DevelopmentCard developmentCard5= new DevelopmentCard(5, new Cost(),new TypeLevel(Colors.GREEN,2),new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()),4);
        DevelopmentCard developmentCard6= new DevelopmentCard(6, new Cost(),new TypeLevel(Colors.GREEN,3),new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()),1);

        developmentBoard.addCard(1,developmentCard4);
        developmentBoard.addCard(1,developmentCard5);
        developmentBoard.addCard(1,developmentCard6);

        for(DevelopmentCard developmentCard1: developmentBoard.getALlCards()){
            developmentBoard.increaseCardCount();
        }
        assertEquals(4,developmentBoard.getCardsCount());

    }
}