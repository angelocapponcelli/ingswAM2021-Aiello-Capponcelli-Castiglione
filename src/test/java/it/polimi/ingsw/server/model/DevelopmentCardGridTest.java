package it.polimi.ingsw.server.model;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedDevelopmentCard;
import it.polimi.ingsw.server.model.cards.DevelopmentCard;
import it.polimi.ingsw.server.model.globalBoard.DevelopmentCardGrid;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.misc.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DevelopmentCardGridTest {
    DevelopmentCardGrid developmentCardGrid;

    @BeforeEach
    void init(){
        try {
            developmentCardGrid= new DevelopmentCardGrid();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void pop() {
        DevelopmentCard developmentCard;
        developmentCard = developmentCardGrid.pop(0, 0);
        developmentCard = developmentCardGrid.pop(0, 0);
        assertEquals(2, developmentCardGrid.getDeck(0, 0).getDeck().size());
        developmentCard = developmentCardGrid.pop(1, 2);
        developmentCard = developmentCardGrid.pop(1, 2);
        developmentCard = developmentCardGrid.pop(1, 2);
        assertEquals(1, developmentCardGrid.getDeck(1, 2).getDeck().size());
        int i = 0;
        for (Deck[] deck : developmentCardGrid.getDeckGrid()){
            for (Deck decks: deck){
                for (DevelopmentCard developmentCard1: decks.getDeck()){
                    i++;
                }
            }
        }
        assertEquals(43,i);

    }

    @Test
    void getDeck(){
        Deck d1= developmentCardGrid.getDeck(0,0);
        assertEquals(4, d1.getDeck().size());
        assertEquals(Colors.GREEN,d1.getDeck().get(0).getTypeLevel().getType());
        Deck d2= developmentCardGrid.getDeck(0,1);
        assertEquals(4, d2.getDeck().size());
        assertEquals(Colors.BLUE,d2.getDeck().get(0).getTypeLevel().getType());
        Deck d3= developmentCardGrid.getDeck(0,2);
        assertEquals(4, d3.getDeck().size());
        assertEquals(Colors.YELLOW,d3.getDeck().get(0).getTypeLevel().getType());
        Deck d4= developmentCardGrid.getDeck(0,3);
        assertEquals(4, d4.getDeck().size());
        assertEquals(Colors.PURPLE,d4.getDeck().get(0).getTypeLevel().getType());
    }

    @Test
    void getDeckGrid(){
        Deck[][] grid= developmentCardGrid.getDeckGrid();
        int sum=0;
        for(int i=0; i>=0 && i<3; i++){
            for(int j=0; j>=0 && j<4; j++){
                for(DevelopmentCard card: grid[i][j].getDeck()){
                    sum++;
                }
            }
        }
        assertEquals(48, sum);

    }

    @Test
    void toReduced(){
        ReducedDevelopmentCard[][] deck= developmentCardGrid.toReduced();
        int size=0;
        for(int i=0; i>=0 && i<3; i++){
            for(int j=0; j>=0 && j<4; j++){
                assertEquals(developmentCardGrid.peek(i,j).getId(),deck[i][j].getId());
            }
        }
    }

    @Test
    void peek(){
        Random rn= new Random();
        int casual= rn.nextInt(3);
        DevelopmentCard dc= developmentCardGrid.getDeck(0,casual).getDeck().get(3);
        assertEquals(dc, developmentCardGrid.peek(0, casual));
    }

}