package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.server.model.resources.Any;
import it.polimi.ingsw.server.model.resources.ResourceType;

public class SelectResourceTypeClientMessage extends ClientMessage {
    private final ResourceType resourceType;

    public SelectResourceTypeClientMessage(String nickname, ResourceType resourceType1) {
        super(nickname);
        this.resourceType = resourceType1;
        this.messageType = MessageType.SELECT_RESOURCE;
    }

    @Override
    public Boolean check() {
        return ResourceType.getResourceClass(this.resourceType) == Any.getInstance();
    }

    public ResourceType getResourceType() {
        return resourceType;
    }
}
