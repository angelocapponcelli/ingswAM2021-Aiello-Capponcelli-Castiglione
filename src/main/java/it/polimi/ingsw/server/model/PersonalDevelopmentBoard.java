package it.polimi.ingsw.server.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The place where a player puts Development Cards;
 */
public class PersonalDevelopmentBoard {
    private final List<Deck> board;
    private Integer cardsCount;

    public PersonalDevelopmentBoard() {
        this.board = new ArrayList<>(3);
        this.cardsCount = 0;
    }

    public void addCard(Integer deck, DevelopmentCard developmentCard) {
        board.get(deck).getDeck().add(developmentCard);
    }

    // maybe notify when they are seven
    private Integer getCardsCount() {
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

    /** public DevelopmentCard getCard(Deck){
     return deck.getTopCard;
     }*/
}
