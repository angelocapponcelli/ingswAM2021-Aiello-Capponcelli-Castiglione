package it.polimi.ingsw.server.model.personalBoard;

import it.polimi.ingsw.server.model.cards.DevelopmentCard;
import it.polimi.ingsw.server.model.misc.Deck;

import java.util.ArrayList;
import java.util.List;

/**
 * The place where a player puts Development Cards;
 */
public class PersonalDevelopmentBoard {
    private final List<Deck> board;
    private Integer cardsCount;

    public PersonalDevelopmentBoard() {
        this.board = new ArrayList<>();
        board.add(new Deck()); //Deck 0
        board.add(new Deck()); //Deck 1
        board.add(new Deck()); //Deck 2
        this.cardsCount = 0;
    }

    /**
     * @param deck            The deck where the development card will be pushed on.
     * @param developmentCard The development card to be added.
     */
    public void addCard(Integer deck, DevelopmentCard developmentCard) {
        board.get(deck).getDeck().add(developmentCard);
    }

    // maybe notify when they are seven
    public Integer getCardsCount() {
        return cardsCount;
    }

    public void increaseCardCount() {
        this.cardsCount = this.cardsCount + 1;
    }

    public Integer getVictoryPoint() {
        int sum = 0;
        for (Deck tmpDeck : board) {
            sum = sum + tmpDeck.getVictoryPoint();
        }

        return sum;
    }

    public List<DevelopmentCard> getALlCards() {
        List<DevelopmentCard> allCards = new ArrayList<>();
        for (Deck deck : board) {
            allCards.addAll(deck.getDeck());
        }
        return allCards;
    }

    public DevelopmentCard getTopCard(Deck deck) {
        int tmp;
        for (tmp = 0; tmp < 3; tmp++) {
            if (deck.equals(this.board.get(tmp))) {
                return this.board.get(tmp).getDeck().get(0);
            }
        }
        return null;
    }

}
