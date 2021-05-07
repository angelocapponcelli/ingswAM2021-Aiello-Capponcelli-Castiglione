package it.polimi.ingsw.server.model.interfaces;

import it.polimi.ingsw.server.model.player.Lorenzo;

/**
 * The Revealable interface.
 * Implemented by SoloAction token in the single player game.
 */
public interface Revealable {
    /**
     * Performs the actions after revealing the solo token.
     *
     * @param lorenzo The "AI" player.
     */
    void onReveal(Lorenzo lorenzo);
}
