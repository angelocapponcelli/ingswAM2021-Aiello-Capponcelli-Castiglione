package it.polimi.ingsw.server.model.soloToken;

import it.polimi.ingsw.server.model.interfaces.Revealable;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.player.Lorenzo;

/**
 * Discard 2 Development Cards of the
 * indicated type from the bottom of the
 * grid, from the lowest level to the highest
 */
public class Discarder implements Revealable {
    private final Colors type;
    private final int numberOfCardToBeDiscarded = 2;

    public Discarder(Colors type) {
        this.type = type;
    }


    @Override
    public void onReveal(Lorenzo lorenzo) {
        for (int i = 0; i < numberOfCardToBeDiscarded; i++) lorenzo.getGameController().getGameModel().getGlobalBoard().getDevelopmentCardGrid().popLowerLevelOfSpecifiedType(type);
    }
}
