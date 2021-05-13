package it.polimi.ingsw.networking.ClientMessage;

import it.polimi.ingsw.server.model.resources.ResourceType;

public class SelectResourceTypeClientMessage extends ClientMessage {
    private ResourceType resourceType;

    public SelectResourceTypeClientMessage(String nickname, ResourceType resourceType1) {
        super(nickname);
        this.resourceType = resourceType1;
    }
}
