package it.polimi.ingsw.server.model.personalBoard;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedDevelopmentCard;
import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedPersonalDevelopmentBoardMessage;
import it.polimi.ingsw.server.model.cards.DevelopmentCard;
import it.polimi.ingsw.server.model.misc.Deck;
import it.polimi.ingsw.utils.exceptions.PersonalBoardException;
import it.polimi.ingsw.utils.observer.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * The place where a player puts Development Cards;
 */
public class PersonalDevelopmentBoard extends Observable {
    private final List<Deck> board = new ArrayList<>();
    private Integer cardsCount;


    /**
     * Class constructor. Instantiates a new Personal Development Board.
     * It creates three decks and sets the count of the cards to zero.
     */
    public PersonalDevelopmentBoard() {
        board.add(new Deck()); //Deck 0
        board.add(new Deck()); //Deck 1
        board.add(new Deck()); //Deck 2
        this.cardsCount = 0;
    }

    /**
     * @param id the id of the requested card
     * @return the requested development card
     */
    public DevelopmentCard getTopCard(int id){
        return board.stream().map(deck -> deck.peek()).filter(card -> card.getId() == id).findFirst().get();
    }

    /**
     * Adds a card to one of the three decks of the board
     * @param deck            The deck where the development card will be pushed on.
     * @param developmentCard The development card to be added.
     */
    public void addCard(Integer deck, DevelopmentCard developmentCard) {
        board.get(deck).getDeck().add(developmentCard);
    }

    /**
     * Adds automatically the card to the board
     * @param developmentCard the card to be added
     * @throws PersonalBoardException if the card  can't be put because of the level.
     */
    public void addCard(DevelopmentCard developmentCard) throws PersonalBoardException {
        switch (developmentCard.getTypeLevel().getLevel()){
            case 1:
                board.stream()
                        .filter(deck -> deck.peek()==null)
                        .findFirst()
                        .orElseThrow(PersonalBoardException::new).push(developmentCard);
                break;
            case 2:
                board.stream()
                        .filter(deck -> deck.peek().getTypeLevel().getLevel()==1)
                        .findFirst()
                        .orElseThrow(PersonalBoardException::new).push(developmentCard);
                break;
            case 3:
                board.stream()
                        .filter(deck -> deck.peek().getTypeLevel().getLevel()==2)
                        .findFirst()
                        .orElseThrow(PersonalBoardException::new).push(developmentCard);
                break;
        }

        notifyObserver(new UpdatedPersonalDevelopmentBoardMessage(toReduced()));
    }

    /**
     * Gets the count of the card in the board
     * @return cards count
     */
    public Integer getCardsCount() {
        return cardsCount;
    }

    /**
     *Performs the increment of the cards count
     */
    public void increaseCardCount() {
        this.cardsCount = this.cardsCount + 1;
    }

    /**
     * Gets the sum of the points of every card in the board
     * @return sum of victory points
     */
    public Integer getVictoryPoint() {
        int sum = 0;
        for (Deck tmpDeck : board) {
            sum = sum + tmpDeck.getVictoryPoint();
        }

        return sum;
    }

    /**
     * Gets the list of every card in the board
     * @return list of all bought cards
     */
    public List<DevelopmentCard> getAllCards() {
        List<DevelopmentCard> allCards = new ArrayList<>();
        for (Deck deck : board) {
            allCards.addAll(deck.getDeck());
        }
        return allCards;
    }

    /**
     * Gets top card of the deck
     * @param deck the deck selected
     * @return top card
     */
    public DevelopmentCard getTopCard(Deck deck) {
        int tmp;
        int size= deck.getDeck().size();
        for (tmp = 0; tmp < 3; tmp++) {
            if (deck.equals(this.board.get(tmp))&& size>0) {
                return this.board.get(tmp).getDeck().get(size-1);
            }
        }
        return null;
    }

    /**
     * @return a list of lightweight development card on top of the personal development board
     */
    public List<ReducedDevelopmentCard> toReduced() {
        List<ReducedDevelopmentCard> reduced = new ArrayList<>();
        for(Deck deck: board){
            if(deck.peek()!=null){
                reduced.add(deck.peek().toReduced());
            }
            else reduced.add(null);
        }
        return reduced;
    }

}
