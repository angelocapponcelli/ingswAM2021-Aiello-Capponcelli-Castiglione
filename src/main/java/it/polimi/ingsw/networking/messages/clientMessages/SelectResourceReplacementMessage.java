package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.server.model.resources.Any;
import it.polimi.ingsw.server.model.resources.ResourceType;

public class SelectResourceReplacementMessage extends ClientMessage {
    private final ResourceType resourceType;

    public SelectResourceReplacementMessage(String nickname, ResourceType resourceType) {
        super(nickname);
        messageType = MessageType.SelectResourceTypeMessage;
        this.resourceType = resourceType;
        this.messageType = MessageType.SELECT_RESOURCE;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }
}
