package it.polimi.ingsw.server.model;

/**
 * Used by depots, can contain only one resource type and capacity is maximum of resources that can contain,
 * capacity = -1 indicate that there isn't capacity limitation
 */

public abstract class ResourceContainer {
    protected ResourceType resource;
    protected int capacity;
    protected int count;

    public ResourceContainer(ResourceType resource, int capacity) {
        this.resource = resource;
        if ( capacity < -1 ) capacity = -1;
        else this.capacity = capacity;
        count = 0;
    }

    public ResourceContainer(int capacity) {
        if ( capacity < -1 ) capacity = -1;
        else this.capacity = capacity;
        count = 0;
    }

    public ResourceType getType() {
        return resource;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCount() {
        return count;
    }

    public void add (int numResource) throws DepotException {
        if (numResource < 0) throw new DepotException("Invalid negative parameter");
        if (capacity >= count + numResource || capacity == -1) {
            count = count + numResource;
        } else throw new DepotException("Out of resource container bound");
    }

    public void remove (int numResource) throws DepotException {
        if (numResource < 0) throw new DepotException("Invalid negative parameter");
        if (count >= numResource) {
            count = count - numResource;
        } else throw new DepotException("Not enough resource in the container");
    }
}
