package it.polimi.ingsw.server.model.resources;

import it.polimi.ingsw.server.model.DepotException;
import it.polimi.ingsw.server.model.RealPlayer;

/**
 * The Any resource. Represents a resource that can be chosen by a player.
 */
public class Any extends Resource {
    private static final Any INSTANCE = new Any();
    private static final ResourceType resourceType = ResourceType.ANY;

    private Any() {
    }

    public static Any getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "Any";
    }

    @Override
    public void onProduction(RealPlayer realPlayer, Integer multiplicity) throws DepotException {
        // ask for a resource
        //resource.onProduction

    }

    @Override
    public void onTaking(RealPlayer realPlayer) {
        //check if any whitemarble's special ability is activated
        //if so then ask for a resource
        //resource.onTaking
        //if no whitemarble's special ability is activated the ignore
    }
}
