package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.server.model.cards.DevelopmentCard;
import it.polimi.ingsw.server.model.globalBoard.DevelopmentCardGrid;
import it.polimi.ingsw.server.model.interfaces.Revealable;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.misc.Deck;
import it.polimi.ingsw.server.model.player.Lorenzo;
import it.polimi.ingsw.server.model.soloToken.Discarder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscarderTest {
    Lorenzo lorenzo;
    Revealable discard;
    Revealable discard2;
    Revealable discard3;
    Revealable discard4;

    @BeforeEach
    void init(){
        lorenzo= new Lorenzo("lollo",new GameController(1,1));
        discard= new Discarder(Colors.GREEN);
        discard2= new Discarder(Colors.YELLOW);
        discard3=new Discarder(Colors.PURPLE);
        discard4= new Discarder(Colors.BLUE);
    }

    @Test
    void onReveal(){
        DevelopmentCardGrid grid= lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid();
        int num=0;
        for (int i=0; i<3; i++){
            for (int j=0; j<4; j++){
                for(DevelopmentCard card:grid.getDeck(i,j).getDeck()){
                    num++;
                }
            }
        }
        assertEquals(48,num);
        num=0;
        int total=0;
        discard.onReveal(lorenzo);
        for (int i=0; i<3; i++){
            for (int j=0; j<4; j++){
                for(DevelopmentCard card:lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getDeck(i,j).getDeck()){
                    if(card.getTypeLevel().getType().equals(Colors.GREEN)){
                        num++;
                    }
                    total++;
                }
            }
        }
        assertEquals(46,total);
        assertEquals(10, num);
        num=0;
        total=0;
        /*this is done because i want to know if onReveal does discard the cards from the deck with the lowest level*/
        discard.onReveal(lorenzo);
        discard.onReveal(lorenzo);
        assertEquals(0, lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getDeck(2,0).getDeck().size());
        assertEquals(2, lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getDeck(1,0).getDeck().size());
        discard.onReveal(lorenzo);
        discard.onReveal(lorenzo);
        for (int i=0; i<3; i++){
            for (int j=0; j<4; j++){
                for(DevelopmentCard card:lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getDeck(i,j).getDeck()){
                    if(card.getTypeLevel().getType().equals(Colors.GREEN)){
                        num++;
                    }
                    total++;
                }
            }
        }
        assertEquals(38, total);
        assertEquals(2,num);

        /*checks yellow type*/
        num=0;
        total=0;
        discard2.onReveal(lorenzo);
        discard2.onReveal(lorenzo);
        for (int i=0; i<3; i++){
            for (int j=0; j<4; j++){
                for(DevelopmentCard card:lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getDeck(i,j).getDeck()){
                    if(card.getTypeLevel().getType().equals(Colors.YELLOW)){
                        num++;
                    }
                    total++;
                }
            }
        }
        assertEquals(8, num);
        assertEquals(34, total);
        assertEquals(0, lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getDeck(2,2).getDeck().size());
        assertEquals(4, lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getDeck(1,2).getDeck().size());
        assertEquals(4, lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getDeck(0,2).getDeck().size());

        /*check purple type*/
        num=0;
        total=0;
        discard3.onReveal(lorenzo);
        discard3.onReveal(lorenzo);
        for (int i=0; i<3; i++){
            for (int j=0; j<4; j++){
                for(DevelopmentCard card:lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getDeck(i,j).getDeck()){
                    if(card.getTypeLevel().getType().equals(Colors.PURPLE)){
                        num++;
                    }
                    total++;
                }
            }
        }
        assertEquals(8, num);
        assertEquals(30, total);
        assertEquals(0, lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getDeck(2,3).getDeck().size());
        assertEquals(4, lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getDeck(1,3).getDeck().size());
        assertEquals(4, lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getDeck(0,3).getDeck().size());

        /*checks color blue, and a card is remove so that we can check if it is able to take a card from two different decks*/


        num=0;
        total=0;
        lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getDeck(2,1).getDeck().remove(0);

        discard4.onReveal(lorenzo);
        discard4.onReveal(lorenzo);
        for (int i=0; i<3; i++){
            for (int j=0; j<4; j++){
                for(DevelopmentCard card:lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getDeck(i,j).getDeck()){
                    if(card.getTypeLevel().getType().equals(Colors.BLUE)){
                        num++;
                    }
                    total++;
                }
            }
        }
        assertEquals(7, num);
        assertEquals(25, total);
        assertEquals(0, lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getDeck(2,1).getDeck().size());
        assertEquals(3, lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getDeck(1,1).getDeck().size());
        assertEquals(4, lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().getDeck(0,1).getDeck().size());

        discard4.onReveal(lorenzo);
        discard4.onReveal(lorenzo);
        discard4.onReveal(lorenzo);
        discard4.onReveal(lorenzo);


    }

}