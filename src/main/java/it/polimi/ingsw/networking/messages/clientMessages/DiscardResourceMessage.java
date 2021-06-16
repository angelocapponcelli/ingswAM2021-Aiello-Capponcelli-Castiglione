package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.server.model.resources.ResourceType;

public class DiscardResourceMessage extends ClientMessage{
    private final ResourceType resourceType;

    public DiscardResourceMessage(String nickname, ResourceType resourceType) {
        super(nickname);
        this.resourceType = resourceType;
        messageType = MessageType.DISCARD_RESOURCE;
    }

    public ResourceType getResourceType(){
        return resourceType;
    }
}
