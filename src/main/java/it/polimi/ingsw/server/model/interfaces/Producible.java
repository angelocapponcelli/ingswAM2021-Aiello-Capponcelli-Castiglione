package it.polimi.ingsw.server.model.interfaces;

import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.utils.exceptions.DepotException;
import it.polimi.ingsw.server.model.player.RealPlayer;

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
     */
    void onProduction(RealPlayer realPlayer, Integer multiplicity);

    ResourceType getResourceType();
}
