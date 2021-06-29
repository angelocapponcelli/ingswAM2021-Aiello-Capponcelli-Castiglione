package it.polimi.ingsw.server.model.personalBoard.resourceContainers;

import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.utils.exceptions.DepotException;

/**
 * Used by depots, can contain only one resource type and capacity is the maximum number of resources that can contain,
 * capacity = -1 indicate that there isn't capacity limitation
 */

public abstract class ResourceContainer {
    protected ResourceType resourceType;
    protected int capacity;
    protected int count;

    /**
     * Class constructor. Instantiates a new Resource container characterized by type and capacity
     * @param resourceType the resource that can be put in this container
     * @param capacity the maximum amount of that type of resource that can be put in this container
     */
    public ResourceContainer(ResourceType resourceType, int capacity) {
        this.resourceType = resourceType;
        this.capacity = capacity;
        count = 0;
    }

    /**
     * Class constructor. Instantiates a new Resource Container.
     * @param capacity the maximum amount of that type of resource that can be put in this container
     */
    public ResourceContainer(int capacity) {
        this.capacity = capacity;
        count = 0;
    }

    /**
     * Remove all the resources stored into the container.
     */
    public void clear() {
        count = 0;
    }

    /**
     * Gets type.
     *
     * @return the type of the resource that can be stored inside the container.
     */
    public ResourceType getType() {
        return resourceType;
    }

    /**
     * Gets capacity.
     *
     * @return the max number of resources that can be stored inside the container, -1 if limitless.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Gets count.
     *
     * @return the number of resource that are currently present inside the container.
     */
    public int getCount() {
        return count;
    }


    /**
     * Performs the addiction of the specified amount of the resource to this container.
     * @param numResource the amount of the resource that has to be added to this container
     * @throws DepotException if the bound is smaller than the count of resources already present in the container plus the resources that need to be put
     *
     */
    public void addResource(int numResource) throws DepotException {
        if (numResource < 0) throw new DepotException("Invalid negative parameter");
        if (capacity >= count + numResource || capacity == -1) {
            count = count + numResource;
        } else throw new DepotException("Out of resource container bound");
    }

    /**
     * Remove.
     *
     * @param numResource the number of resource to remove from the container
     * @throws DepotException the depot exception
     */
    public void remove(int numResource) throws DepotException {
        if (numResource < 0) throw new DepotException("Invalid negative parameter");
        if (count >= numResource) {
            count = count - numResource;
        } else throw new DepotException("Not enough resource in the container");
    }
}
