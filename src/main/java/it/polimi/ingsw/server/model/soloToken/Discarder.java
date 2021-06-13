package it.polimi.ingsw.server.model.soloToken;

import it.polimi.ingsw.server.model.cards.DevelopmentCard;
import it.polimi.ingsw.server.model.globalBoard.GlobalBoard;
import it.polimi.ingsw.server.model.interfaces.Revealable;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.misc.Deck;
import it.polimi.ingsw.server.model.player.Lorenzo;

import java.util.ArrayList;

public class Discarder implements Revealable {
    private final Colors type;

    public Discarder(Colors type) {
        this.type = type;
    }


    @Override
    public void onReveal(Lorenzo lorenzo) {
        GlobalBoard globalBoard1 = lorenzo.getGameController().getGameModel().getGlobalBoard();
        ArrayList<Deck> listOfPossibleDecks = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                DevelopmentCard developmentCard = globalBoard1.getDevelopmentCardGrid().getDeck(i, j).getDeck().get(0);
                if (developmentCard.getTypeLevel().getType().equals(this.type)) {
                    listOfPossibleDecks.add(globalBoard1.getDevelopmentCardGrid().getDeck(i, j));
                }
            }
        }
        int tmp3 = 0;
        /*asks the player which deck*/
        listOfPossibleDecks.get(tmp3).getDeck().remove(0);
        listOfPossibleDecks.get(tmp3).getDeck().remove(0);
    }
}
