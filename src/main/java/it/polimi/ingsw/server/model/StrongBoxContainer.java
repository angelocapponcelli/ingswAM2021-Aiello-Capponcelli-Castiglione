package it.polimi.ingsw.server.model;

/**
 * Strongbox not have resources limitations, its capacity is set to -1
 */
public class StrongBoxContainer extends  ResourceContainer{
    public StrongBoxContainer(ResourceType resource) {
        super(resource, -1);
    }
}
