package it.polimi.ingsw.server.model.misc;

import it.polimi.ingsw.server.model.cards.DevelopmentCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Deck that contains development cards.
 */
public class Deck {

    private final List<DevelopmentCard> deck;

    /**
     * Class constructor. Instantiates a new deck.
     */
    public Deck() {
        deck = new ArrayList<>();
    }

    /**
     * Gets list of cards
     * @return list of development cards contained in the deck
     */
    public List<DevelopmentCard> getDeck() {
        return deck;
    }

    /**
     * Gets the sum of the points of each card in the deck
     * @return sum of victory points of the cards of the deck
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
        if (deck.size() > 0) {
            return deck.get(deck.size() - 1);
        }
        return null;
    }

    /**
     * Shuffles this deck.
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }


}
