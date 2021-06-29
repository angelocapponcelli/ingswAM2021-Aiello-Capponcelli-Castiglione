package it.polimi.ingsw.server.model.globalBoard;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedDevelopmentCard;
import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedDevelopmentCardGridMessage;
import it.polimi.ingsw.server.model.cards.DevelopmentCard;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.misc.Deck;
import it.polimi.ingsw.utils.observer.Observable;
import it.polimi.ingsw.utils.parsers.SettingsParser;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class DevelopmentCardGrid extends Observable {
    private final Deck[][] deckGrid = new Deck[3][4];

    public DevelopmentCardGrid() throws FileNotFoundException {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                deckGrid[i][j] = new Deck();
            }
        }

        SettingsParser.getInstance().getDevelopmentCards()
                .forEach(developmentCard -> {
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

    public DevelopmentCard peek(int row, int column){
        return deckGrid[row][column].peek();
    }
    public DevelopmentCard pop(int row, int column){
        DevelopmentCard developmentCard = deckGrid[row][column].pop();
        notifyObserver(new UpdatedDevelopmentCardGridMessage(toReduced()));
        return developmentCard;
    }

    public Deck getDeck(Integer row, Integer column) {
        return this.deckGrid[row][column];
    }

    public Deck[][] getDeckGrid() {
        return deckGrid;
    }



    public List<Deck> getColumn(Colors type){
        return Arrays.stream(deckGrid)
                .flatMap(deckRow -> Arrays.stream(deckRow)
                        .filter(deck -> deck.peek().getTypeLevel().getType().equals(type)))
                .collect(Collectors.toList());
    }


    public DevelopmentCard peekLowerLevelOfSpecifiedType(Colors type){
        return Arrays.stream(deckGrid)
                .flatMap(deckRow -> Arrays.stream(deckRow).filter(deck -> deck.peek().getTypeLevel().getType().equals(type)))
                .min(Comparator.comparing(deck-> deck.peek().getTypeLevel().getLevel()))
                .get().peek();
    }

    public void popLowerLevelOfSpecifiedType(Colors type){
         Arrays.stream(deckGrid)
                .flatMap(deckRow -> Arrays.stream(deckRow).filter(deck -> deck.peek().getTypeLevel().getType().equals(type)))
                .min(Comparator.comparing(deck-> deck.peek().getTypeLevel().getLevel()))
                .get().pop();

        notifyObserver(new UpdatedDevelopmentCardGridMessage(toReduced()));
    }

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
