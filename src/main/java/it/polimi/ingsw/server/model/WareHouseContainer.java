package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.resources.ResourceType;

/**
 * WareHouseContainer allowed to set resource type, or capacity
 */

public class WareHouseContainer extends  ResourceContainer{

    public WareHouseContainer(int capacity) {
        super(capacity);
    }

    public void setResourceType (ResourceType resource) throws DepotException {
        if (count > 0) throw new DepotException("Depot not empty, impossible to change resource type");
        this.resource = resource;
    }

    public void setCapacity (int capacity) throws DepotException {
        if (capacity < 0) this.capacity = -1;
        else {
            if (count > capacity)
                throw new DepotException("There are too many resources, impossible to change capacity");
            else this.capacity = capacity;
        }
    }
}
