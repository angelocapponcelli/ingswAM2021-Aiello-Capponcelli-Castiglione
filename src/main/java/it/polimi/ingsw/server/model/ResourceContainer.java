package it.polimi.ingsw.server.model;

public class ResourceContainer {
    protected ResourceType resource;
    protected int capacity;
    private int count;

    public ResourceContainer(ResourceType resource, int capacity) {
        this.resource = resource;
        this.capacity = capacity;
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

    public void add () throws DepotException {
        if (capacity >= count + 1 || capacity == -1) count++;
        else throw new DepotException("Out of resource container bound");
    }

    public void remove (int numResource) throws DepotException {
        if (numResource < 0) throw new DepotException("Invalid negative parameter");
        if (count >= numResource) {
            count = count - numResource;
        } else throw new DepotException("Not enough resource in the container");
    }

    public void remove () throws DepotException {
        if (count >= 1) count--;
        else throw new DepotException("Not enough resource in the container");
    }
}
