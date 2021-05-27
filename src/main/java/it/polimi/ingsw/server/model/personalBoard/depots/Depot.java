package it.polimi.ingsw.server.model.personalBoard.depots;

import it.polimi.ingsw.utils.exceptions.DepotException;
import it.polimi.ingsw.server.model.resources.ResourceType;

/**
 * Generic Depot without any specialization
 */
public abstract class Depot {

    /**
     * Add resources to the depot
     *
     * @param resourceType the resource type of the resources to be added
     * @param numResource  the number of resources to be added
     * @throws DepotException the depot exception
     */
    public void addResources(ResourceType resourceType, int numResource) throws DepotException {
    }

    /**
     * Remove resources from the depot
     *
     * @param resourceType the resource to be removed
     * @throws DepotException the depot exception
     */
    public abstract void removeResources(ResourceType resourceType, int numResource) throws DepotException;

    /**
     * Gets the count of all the resources stored in the depot.
     *
     * @return the number of all the resources stored inside the depot.
     */
    public abstract int getAllResourceCount();

    /**
     * Gets the count of a specific resource.
     *
     * @param resourceType the resource
     * @return the resource count
     */
    public abstract int getSpecificResourceCount(ResourceType resourceType);
}
