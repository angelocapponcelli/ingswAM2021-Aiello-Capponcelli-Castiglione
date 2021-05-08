package it.polimi.ingsw.server.model.personalBoard.resourceContainers;

import it.polimi.ingsw.server.model.resources.ResourceType;

/**
 * Strongbox not have resources limitations, its capacity is set to -1
 */
public class StrongBoxContainer extends ResourceContainer {
    public StrongBoxContainer(ResourceType resourceType) {
        super(resourceType, -1);
    }
}
