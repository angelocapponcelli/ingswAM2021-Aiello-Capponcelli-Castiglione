package it.polimi.ingsw.server.model.interfaces;

import it.polimi.ingsw.utils.exceptions.DepotException;
import it.polimi.ingsw.server.model.player.RealPlayer;

/**
 * The interface Payable.
 * Implemented by items that must be payed by the player like development card's cost and production power's input
 */
public interface Payable {
    /**
     * Performs the paying.
     *
     * @param realPlayer The player who pays
     */
    void pay(RealPlayer realPlayer) throws DepotException;
}
