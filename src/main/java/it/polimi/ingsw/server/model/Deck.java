package it.polimi.ingsw.server.model;

import java.util.List;

public class Deck {


    private final List<DevelopmentCard> deck;

    public Deck(List<DevelopmentCard> deck) {
        this.deck = deck;
    }

    public List<DevelopmentCard> getDeck() {
        return deck;
    }

    /**
     * has to be check
     */
    public int getVictoryPoint() {
        int sum = 0;
        for (DevelopmentCard developmentCard : deck) {
            sum = sum + developmentCard.getVictoryPoints();
        }
        return sum;
    }
}
