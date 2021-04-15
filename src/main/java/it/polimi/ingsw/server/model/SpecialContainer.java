package it.polimi.ingsw.server.model;
/**
 * Container of special depot it's a container that can contain a maximum of 2 resources
 */
public class SpecialContainer extends ResourceContainer{

    public SpecialContainer(ResourceType resource) {
        super(resource, 2);
    }

}
