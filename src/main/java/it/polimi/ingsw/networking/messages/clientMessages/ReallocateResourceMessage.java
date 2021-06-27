package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.server.model.resources.ResourceType;

public class ReallocateResourceMessage extends ClientMessage {
    private final ResourceType resourceType;
    private final String sourceDepot;
    private final String destinationDepot;
    private final Integer sourceShelf;
    private final Integer destinationShelf;

    public ReallocateResourceMessage(String nickname, ResourceType resourceType, String sourceDepot, String destinationDepot, Integer sourceShelf, Integer destinationShelf) {
        super(nickname);
        this.resourceType = resourceType;
        this.messageType= MessageType.REALLOCATE_RESOURCE;
        this.sourceDepot = sourceDepot;
        this.destinationDepot = destinationDepot;
        this.sourceShelf = sourceShelf;
        this.destinationShelf = destinationShelf;
    }

    public ResourceType getResourceType(){
        return resourceType;
    }

    public String getSourceDepot() {
        return sourceDepot;
    }

    public String getDestinationDepot() {
        return destinationDepot;
    }

    public Integer getSourceShelf() {
        return sourceShelf;
    }

    public Integer getDestinationShelf() {
        return destinationShelf;
    }
}
