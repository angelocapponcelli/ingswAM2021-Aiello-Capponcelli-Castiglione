package it.polimi.ingsw.server.model.misc;

import it.polimi.ingsw.server.model.cards.DevelopmentCard;

import java.util.ArrayList;
import java.util.List;

public class Deck {


    private final List<DevelopmentCard> deck;

    public Deck() {
        deck = new ArrayList<>();
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

    public DevelopmentCard getTopCard() {
        if (deck.size() > 0)
            return deck.get(0);
        else return null;
    }
}
