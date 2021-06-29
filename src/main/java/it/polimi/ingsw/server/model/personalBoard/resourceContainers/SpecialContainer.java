package it.polimi.ingsw.server.model.personalBoard.resourceContainers;

import it.polimi.ingsw.server.model.resources.ResourceType;

/**
 * Container of special depot it's a container that can contain a maximum of 2 resources
 */
public class SpecialContainer extends ResourceContainer {

    /**
     * Class constructor. Instantiates a new Special container
     * @param resource the resource that characterizes the container
     */
    public SpecialContainer(ResourceType resource) {
        super(resource, 2);
    }

}
