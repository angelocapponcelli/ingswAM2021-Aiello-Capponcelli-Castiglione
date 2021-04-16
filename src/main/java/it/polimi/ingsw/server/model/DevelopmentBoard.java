package it.polimi.ingsw.server.model;

import java.util.List;

public class DevelopmentBoard {
    private List<Deck> board;
    private Integer cardsCount;

    public DevelopmentBoard(List<Deck> board, Integer cardsCount){
        this.board=board;
        this.cardsCount=cardsCount;
    }

    public void addCard(Deck deck, DevelopmentCard developmentCard ){

    }

    // maybe notify when they are seven
    public Integer getCardsCount(){
        return cardsCount;
    }

    public void increaseCardCount(){
        this.cardsCount= this.cardsCount+1;
    }

    public Integer getVictoryPoint(){
        int sum=0;
        for (Deck tmpDeck: board){
            sum= sum+ tmpDeck.getVictoryPoint();
        }

        return sum;
    }

   /** public DevelopmentCard getCard(Deck){
        return deck.getTopCard;
    }*/
}
