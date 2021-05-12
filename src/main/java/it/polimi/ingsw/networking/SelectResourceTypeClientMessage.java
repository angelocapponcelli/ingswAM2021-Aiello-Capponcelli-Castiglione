package it.polimi.ingsw.networking;

import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.ResourceType;

public class SelectResourceTypeClientMessage extends ClientMessage {
    private ResourceType resourceType;

    public SelectResourceTypeClientMessage(RealPlayer realPlayer1, ResourceType resourceType1) {
        super(realPlayer1);
        this.resourceType= resourceType1;
    }
}
