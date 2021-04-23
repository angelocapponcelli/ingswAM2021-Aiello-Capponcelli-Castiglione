package it.polimi.ingsw.server.model.interfaces;

import it.polimi.ingsw.server.model.DepotException;
import it.polimi.ingsw.server.model.RealPlayer;

/**
 * The interface Producible.
 * Implemented by items that can be produced like resources and faith.
 */
public interface Producible {
    /**
     * Performs the actions that follow the production.
     *
     * @param realPlayer   The Player who performs the production.
     * @param multiplicity The multiplicity of the produced resource.
     * @throws DepotException
     */
    void onProduction(RealPlayer realPlayer, Integer multiplicity) throws DepotException;
}
