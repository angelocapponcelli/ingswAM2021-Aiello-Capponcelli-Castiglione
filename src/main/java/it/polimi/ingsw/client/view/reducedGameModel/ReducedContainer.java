package it.polimi.ingsw.client.view.reducedGameModel;

import it.polimi.ingsw.server.model.resources.ResourceType;

import java.io.Serializable;

public class ReducedContainer implements Serializable {
    ResourceType resourceType;
    Integer count;

    public ReducedContainer(ResourceType resourceType, Integer count) {
        this.resourceType = resourceType;
        this.count = count;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
