package it.polimi.ingsw.server.model.personalBoard.resourceContainers;

import it.polimi.ingsw.server.model.resources.ResourceType;

/**
 * Container of special depot it's a container that can contain a maximum of 2 resources
 */
public class SpecialContainer extends ResourceContainer {

    public SpecialContainer(ResourceType resource, int capacity) {
        super(resource, capacity);
    }

}
