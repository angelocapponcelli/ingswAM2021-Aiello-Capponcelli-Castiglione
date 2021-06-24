package it.polimi.ingsw.server.model;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedDevelopmentCard;
import it.polimi.ingsw.server.model.cards.Cost;
import it.polimi.ingsw.server.model.cards.DevelopmentCard;
import it.polimi.ingsw.server.model.cards.TypeLevel;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.personalBoard.PersonalDevelopmentBoard;
import it.polimi.ingsw.server.model.productionPower.ProductionPower;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerInput;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerOutput;
import it.polimi.ingsw.utils.exceptions.PersonalBoardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonalDevelopmentBoardTest {

    PersonalDevelopmentBoard developmentBoard;


    @BeforeEach
    void init() {
        developmentBoard = new PersonalDevelopmentBoard();
        DevelopmentCard developmentCard = new DevelopmentCard(1, new Cost(), new TypeLevel(Colors.YELLOW, 1), new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()), 14);
        DevelopmentCard developmentCard1 = new DevelopmentCard(2, new Cost(), new TypeLevel(Colors.YELLOW, 2), new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()), 14);
        DevelopmentCard developmentCard2 = new DevelopmentCard(3, new Cost(), new TypeLevel(Colors.YELLOW, 3), new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()), 2);
        developmentBoard.addCard(1, developmentCard);
        developmentBoard.addCard(1, developmentCard1);
        developmentBoard.addCard(1, developmentCard2);
        DevelopmentCard developmentCard4 = new DevelopmentCard(4, new Cost(), new TypeLevel(Colors.GREEN, 1), new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()), 3);
        DevelopmentCard developmentCard5 = new DevelopmentCard(5, new Cost(), new TypeLevel(Colors.GREEN, 2), new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()), 4);
        DevelopmentCard developmentCard6 = new DevelopmentCard(6, new Cost(), new TypeLevel(Colors.GREEN, 3), new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()), 1);
        developmentBoard.addCard(2, developmentCard4);
        developmentBoard.addCard(2, developmentCard5);
        developmentBoard.addCard(2, developmentCard6);
    }


    @Test
    void getVictoryPoint() {

        assertEquals(38, developmentBoard.getVictoryPoint());
        DevelopmentCard developmentCard7 = new DevelopmentCard(7, new Cost(), new TypeLevel(Colors.GREEN, 1), new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()), 4);
        developmentBoard.addCard(0, developmentCard7);
        assertEquals(42, developmentBoard.getVictoryPoint());
    }

    @Test
    void addCard() {
        DevelopmentCard developmentCard = new DevelopmentCard(1, new Cost(), new TypeLevel(Colors.YELLOW, 1), new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()), 14);
        developmentBoard.addCard(0, developmentCard);
        DevelopmentCard developmentCard4 = new DevelopmentCard(4, new Cost(), new TypeLevel(Colors.GREEN, 1), new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()), 3);
        DevelopmentCard developmentCard5 = new DevelopmentCard(5, new Cost(), new TypeLevel(Colors.GREEN, 2), new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()), 4);
        DevelopmentCard developmentCard6 = new DevelopmentCard(6, new Cost(), new TypeLevel(Colors.GREEN, 3), new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()), 1);

        developmentBoard.addCard(0, developmentCard4);
        developmentBoard.addCard(0, developmentCard5);


        for (DevelopmentCard developmentCard1 : developmentBoard.getALlCards()) {
            developmentBoard.increaseCardCount();
        }
        assertEquals(9, developmentBoard.getCardsCount());

    }

    @Test
    void toReduced(){
        List<ReducedDevelopmentCard> cards;
        cards= developmentBoard.toReduced();
        int notNull=0;
        /*they has to be 3*/
        assertEquals(3, cards.size());
        for (ReducedDevelopmentCard card: cards){
            if(card!= null){
                notNull++;
            }
        }
        assertEquals(2,notNull );
        /*just the top card*/

        assertEquals(null, cards.get(0));
        assertEquals(3, cards.get(1).getId());
        assertEquals(6, cards.get(2).getId());
        assertEquals(1, cards.get(2).getVictoryPoints());
    }

    @Test
    void getAllCards(){
        List<DevelopmentCard> cards= developmentBoard.getALlCards();
        int count=0;

        for(int i=0; i>=0 && i<cards.size(); i++){
            if(cards.get(i)!= null){
                count++;
            }
        }
        assertEquals(6, count);
        assertEquals(6, cards.size());

        developmentBoard.addCard(0,new DevelopmentCard(4, new Cost(), new TypeLevel(Colors.GREEN, 1), new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()), 3));
        cards= developmentBoard.getALlCards();

        assertEquals(7,cards.size());

    }




    @Test
    void getCardsCount(){
        developmentBoard.increaseCardCount();
        assertEquals(1, developmentBoard.getCardsCount());
        developmentBoard.increaseCardCount();
        developmentBoard.increaseCardCount();
        assertEquals(3, developmentBoard.getCardsCount());
        /*
        assertEquals(6, developmentBoard.getCardsCount());
        try {
            developmentBoard.addCard(new DevelopmentCard(1, new Cost(), new TypeLevel(Colors.YELLOW, 1), new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()), 14));
        } catch (PersonalBoardException e) {
            e.printStackTrace();
        }
        assertEquals(7, developmentBoard.getCardsCount());
        */


    }


}