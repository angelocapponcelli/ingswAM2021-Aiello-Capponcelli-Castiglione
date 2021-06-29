package it.polimi.ingsw.server.model.resources;

import it.polimi.ingsw.server.model.interfaces.Producible;
import it.polimi.ingsw.server.model.interfaces.Takeable;

/**
 * Resource class implements takeable and producible interfaces. A resource can be taken from the market
 * or it can b produced during "Activate production" phase of the game.
 */
public abstract class Resource implements Takeable, Producible {
    public abstract ResourceType getResourceType();
}
