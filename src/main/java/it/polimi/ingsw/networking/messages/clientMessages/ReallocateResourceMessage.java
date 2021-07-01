package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.server.model.resources.ResourceType;

/**
 * Reallocate resource message
 */
public class ReallocateResourceMessage extends ClientMessage {
    private final ResourceType resourceType;
    private final String sourceDepot;
    private final String destinationDepot;
    private final Integer sourceShelf;
    private final Integer destinationShelf;

    /**
     * Class constructor
     * @param nickname the nickname of the real player
     * @param resourceType the resource to replace
     * @param sourceDepot the depot where it has been taken from
     * @param destinationDepot the depot where to put the resource
     * @param sourceShelf the shelf where to take the resource
     * @param destinationShelf the shelf where to put the resource
     */
    public ReallocateResourceMessage(String nickname, ResourceType resourceType, String sourceDepot, String destinationDepot, Integer sourceShelf, Integer destinationShelf) {
        super(nickname);
        this.resourceType = resourceType;
        this.messageType= MessageType.REALLOCATE_RESOURCE;
        this.sourceDepot = sourceDepot;
        this.destinationDepot = destinationDepot;
        this.sourceShelf = sourceShelf;
        this.destinationShelf = destinationShelf;
    }

    /**
     *
     * @return the resource to replace
     */
    public ResourceType getResourceType(){
        return resourceType;
    }

    /**
     *
     * @return the depot
     */
    public String getSourceDepot() {
        return sourceDepot;
    }

    /**
     *
     * @return the depot where to put the resource
     */
    public String getDestinationDepot() {
        return destinationDepot;
    }

    /**
     *
     * @return the shelf where to take the resource
     */
    public Integer getSourceShelf() {
        return sourceShelf;
    }

    /**
     *
     * @return the shelf where to put the resource
     */
    public Integer getDestinationShelf() {
        return destinationShelf;
    }
}
