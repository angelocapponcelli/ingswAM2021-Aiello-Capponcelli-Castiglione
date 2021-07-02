package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.cards.Cost;
import it.polimi.ingsw.server.model.cards.DevelopmentCard;
import it.polimi.ingsw.server.model.cards.TypeLevel;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.misc.Deck;
import it.polimi.ingsw.server.model.productionPower.ProductionPower;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerInput;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Deck class
 */
class DeckTest {
    Deck deck;
    DevelopmentCard developmentCard;

    /**
     * Initializes development cards and a deck. It then adds the cards to the deck
     */
    @BeforeEach
    void init() {
        developmentCard = new DevelopmentCard(1, new Cost(), new TypeLevel(Colors.YELLOW, 1), new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()), 14);
        DevelopmentCard developmentCard1 = new DevelopmentCard(2, new Cost(), new TypeLevel(Colors.YELLOW, 2), new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()), 14);
        DevelopmentCard developmentCard2 = new DevelopmentCard(3, new Cost(), new TypeLevel(Colors.YELLOW, 3), new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()), 2);
        deck = new Deck();
        deck.getDeck().add(developmentCard);
        deck.getDeck().add(developmentCard1);
        deck.getDeck().add(developmentCard2);

    }

    /**
     * Verifies the sum of victory points of all the deck is correct.
     */
    @Test
    void getVictoryPoint() {
        assertEquals(30, deck.getVictoryPoint());
        DevelopmentCard developmentCard3 = new DevelopmentCard(4, new Cost(), new TypeLevel(Colors.YELLOW, 1), new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()), 10);
        deck.getDeck().add(developmentCard3);
        assertEquals(40, deck.getVictoryPoint());

    }

    /**
     * Verifies pop. When a card is taken away from the deck.
     */
    @Test
    void pop(){
        assertEquals(3, deck.getDeck().size());
        DevelopmentCard dCard= deck.pop();
        assertEquals(2, deck.getDeck().size());
        assertEquals(3, dCard.getId());
        deck.pop();
        deck.pop();
        deck.pop();
        deck.pop();
        assertEquals(0, deck.getDeck().size());

    }

    /**
     * Verifies that the card returned with the method peek is the top card of the deck
     */
    @Test
    void peek(){
        assertEquals(3, deck.getDeck().size());
        deck.peek();
        deck.peek();
        deck.peek();
        assertEquals(3, deck.getDeck().size());
        DevelopmentCard developmentCard= deck.peek();
        assertEquals(3, developmentCard.getId());
        deck.pop();
        developmentCard= deck.peek();
        assertEquals(2, deck.getDeck().size());
        assertEquals(2, developmentCard.getId());
    }

    /**
     * Verifies the shuffle method. the cards have to be in a casual order.
     */
    @Test
    void shuffle(){
        int size;
        DevelopmentCard card= new DevelopmentCard(4, new Cost(), new TypeLevel(Colors.YELLOW, 1), new ProductionPower(new ProductionPowerInput(), new ProductionPowerOutput()), 10);
        size= deck.getDeck().size();
        assertEquals(3, size);
        assertEquals(true, deck.getDeck().contains(developmentCard));
        assertEquals(false, deck.getDeck().contains(card));

        deck.shuffle();
        size= deck.getDeck().size();
        assertEquals(3, size);

        assertEquals(true, deck.getDeck().contains(developmentCard));
        assertEquals(false, deck.getDeck().contains(card));
    }

    /**
     * verifies the list of the cards that composes the deck is the same as the one returned from the getdeck
     * method
     */
    @Test
    void getDeck(){
        List<DevelopmentCard> d1= deck.getDeck();
        assertEquals(d1.size(), deck.getDeck().size());
        assertEquals(d1.get(0), deck.getDeck().get(0));
        assertEquals(d1.get(1), deck.getDeck().get(1));
        assertEquals(d1.get(2), deck.getDeck().get(2));
    }
}