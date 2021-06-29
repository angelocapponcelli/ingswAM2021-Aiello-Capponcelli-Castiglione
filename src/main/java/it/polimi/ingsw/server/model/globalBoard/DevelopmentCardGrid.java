package it.polimi.ingsw.server.model.globalBoard;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedDevelopmentCard;
import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedDevelopmentCardGridMessage;
import it.polimi.ingsw.server.model.cards.DevelopmentCard;
import it.polimi.ingsw.server.model.misc.Deck;
import it.polimi.ingsw.utils.observer.Observable;
import it.polimi.ingsw.utils.parsers.DevelopmentCardParser;

import java.io.FileNotFoundException;

/**
 * Development Card Grid. It is a grid composed by twelve decks. Each deck is associated to a specific level and type
 * of development card.
 */

public class DevelopmentCardGrid extends Observable {
    private final Deck[][] deckGrid = new Deck[3][4];

    /**
     * Class constructor. Instantiates a new Card grid. Instantiates twelve new Decks and populates them based on type and level of the cards.
     * It also shuffles the decks.
     * @throws FileNotFoundException if the JSON file is not found
     */
    public DevelopmentCardGrid() throws FileNotFoundException {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                deckGrid[i][j] = new Deck();
            }
        }

        DevelopmentCardParser.getCards().forEach(developmentCard -> {
            switch (developmentCard.getTypeLevel().getType()) {
                case GREEN:
                    deckGrid[3 - developmentCard.getTypeLevel().getLevel()][0].push(developmentCard);
                    break;
                case BLUE:
                    deckGrid[3 - developmentCard.getTypeLevel().getLevel()][1].push(developmentCard);
                    break;
                case YELLOW:
                    deckGrid[3 - developmentCard.getTypeLevel().getLevel()][2].push(developmentCard);
                    break;
                case PURPLE:
                    deckGrid[3 - developmentCard.getTypeLevel().getLevel()][3].push(developmentCard);
                    break;

            }
        });

        for (Deck[] decks : deckGrid) {
            for (int j = 0; j < deckGrid[0].length; j++) {
                decks[j].shuffle();
            }
        }


    }

    /**
     * Gets the top card of the deck
     * @param row where to find the deck
     * @param column where to find the deck
     * @return card which is on top of the selected deck
     */

    public DevelopmentCard peek(int row, int column){
        return deckGrid[row][column].peek();
    }

    /**
     * Picks the top card of the selected deck. The it removes the card from the deck and notifies the observer
     * @param row where to find the deck
     * @param column where to find the deck
     * @return card which is on top of the selected Deck
     */
    public DevelopmentCard pop(int row, int column){
        DevelopmentCard developmentCard = deckGrid[row][column].pop();
        notifyObserver(new UpdatedDevelopmentCardGridMessage(toReduced()));
        return developmentCard;
    }

    /**
     * Gets the selected deck
     * @param row where to find the deck
     * @param column where to find the deck
     * @return deck selected
     */

    public Deck getDeck(Integer row, Integer column) {
        return this.deckGrid[row][column];
    }

    /**
     * Gets the multidimensional array of the grid
     * @return grid where the cards are put
     */

    public Deck[][] getDeckGrid() {
        return deckGrid;
    }

    /**
     * Gets a reduced version of the grid
     * @return reduced development card multidimensional array.
     */

    public ReducedDevelopmentCard[][] toReduced() {
        ReducedDevelopmentCard[][] reducedDeckGrid = new ReducedDevelopmentCard[3][4];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {

                reducedDeckGrid[i][j] = new ReducedDevelopmentCard(deckGrid[i][j].peek());
            }
        }
        return reducedDeckGrid;
    }

}
