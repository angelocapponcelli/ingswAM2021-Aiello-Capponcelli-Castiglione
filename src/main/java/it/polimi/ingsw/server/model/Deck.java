package it.polimi.ingsw.server.model;

import java.util.List;

public class Deck {
    private List<DevelopmentCard> deck;

    public Deck(List<DevelopmentCard> deck){
        this.deck=deck;
    }

   /** has to be check*/
    public Integer getVictoryPoint(){
        int sum=0;
        for (DevelopmentCard developmentCard:deck){
            sum= sum + developmentCard.getVictoryPoints();
        }
        return sum;
    }

   /** need to do a getdevelopmentcard*/

    //push

    //pop
}
