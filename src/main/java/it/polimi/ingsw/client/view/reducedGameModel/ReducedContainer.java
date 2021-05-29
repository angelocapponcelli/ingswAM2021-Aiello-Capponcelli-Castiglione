package it.polimi.ingsw.client.view.reducedGameModel;

import it.polimi.ingsw.server.model.resources.ResourceType;

public class ReducedContainer {
    ResourceType resourceType;
    int count;

    public ReducedContainer(ResourceType resourceType, int count) {
        this.resourceType = resourceType;
        this.count = count;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
