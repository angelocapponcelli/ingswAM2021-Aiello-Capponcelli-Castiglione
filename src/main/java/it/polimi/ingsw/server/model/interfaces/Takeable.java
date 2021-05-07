package it.polimi.ingsw.server.model.interfaces;

import it.polimi.ingsw.server.model.player.RealPlayer;

/**
 * The interface Takeable.
 * Implemented by items that can be taken from the market tray like resources, faith and the white marble.
 */
public interface Takeable {
    /**
     * Performs the taking of the resource.
     *
     * @param realPlayer The player who take the resource.
     */
    void onTaking(RealPlayer realPlayer);
}
