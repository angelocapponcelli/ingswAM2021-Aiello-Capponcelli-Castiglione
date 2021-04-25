package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.exceptions.DepotException;
import it.polimi.ingsw.server.model.resources.Resource;
import it.polimi.ingsw.server.model.resources.ResourceType;

/**
 * Generic Depot without any specialization
 */

public abstract class Depot {

    public void add(ResourceType resource, int numResource) throws DepotException {
    }

    public void remove(Resource resource, int numResource) throws DepotException {
    }

    public int getResourceCount() {
        return 0;
    }

    public int getResourceCount(Resource resource) {
        return 0;
    }
}
