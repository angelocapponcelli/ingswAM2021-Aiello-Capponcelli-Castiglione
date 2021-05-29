package it.polimi.ingsw.server.model.misc;

import it.polimi.ingsw.server.model.cards.DevelopmentCard;

import java.util.ArrayList;
import java.util.Collections;
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

    /**
     * Returns the card on top of the deck and removes it from the deck
     *
     * @return the card on top of the deck.
     */
    public DevelopmentCard pop() {

        if (deck.size() > 0) {
            DevelopmentCard temp = deck.get(deck.size() - 1);
            deck.remove(deck.size() - 1);
            return temp;
        }
        return null;
    }

    /**
     * Adds a development card to the deck.
     *
     * @param developmentCard the card to be added.
     */
    public void push(DevelopmentCard developmentCard) {
        deck.add(developmentCard);
    }

    /**
     * Returns the card on top of the deck without removes it
     *
     * @return the the card on top of the deck
     */
    public DevelopmentCard peek() {
        return deck.get(deck.size() - 1);
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }


}
