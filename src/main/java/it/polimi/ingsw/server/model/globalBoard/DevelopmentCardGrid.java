package it.polimi.ingsw.server.model.globalBoard;

import it.polimi.ingsw.server.model.cards.DevelopmentCard;
import it.polimi.ingsw.server.model.misc.Deck;
import it.polimi.ingsw.utils.observer.Observable;
import it.polimi.ingsw.utils.parsers.DevelopmentCardParser;

import java.io.FileNotFoundException;


public class DevelopmentCardGrid extends Observable {
    private final Deck[][] deckGrid = new Deck[3][4];

    public DevelopmentCardGrid() throws FileNotFoundException {

        for(int i = 0; i < 3; i++){
            for (int j= 0; j< 4; j++){
                deckGrid[i][j] = new Deck();
            }
        }

        DevelopmentCardParser.getCards().forEach(developmentCard -> {
            switch (developmentCard.getTypeLevel().getType()){
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


    public Deck getDeck(Integer row, Integer column) {
        return this.deckGrid[row][column];
    }

    public Deck[][] getDeckGrid() {
        return deckGrid;
    }

    public DevelopmentCard[][] toReduced(){
        DevelopmentCard[][] reducedDeckGrid = new DevelopmentCard[3][4];
        for (int i = 0; i < 3; i++ ){
            for(int j = 0; j < 4; j++){
                reducedDeckGrid[i][j] = deckGrid[i][j].peek();
            }
        }
        return reducedDeckGrid;
    }

}
