package it.polimi.ingsw.server.model;

public class WareHouseContainer extends  ResourceContainer{

    public WareHouseContainer(ResourceType resource, int capacity) {
        super(resource, capacity);
    }

    public void setResourceType (ResourceType resource) throws DepotException {
        if (getCount() > 0) throw new DepotException("Depot not empty, impossible to change resource type");
        this.resource = resource;
    }

    public void setCapacity (int capacity) throws DepotException {
        if (getCount() > capacity) throw new DepotException("Depot not empty, impossible to change resource type");
        else this.capacity = capacity;
    }
}
