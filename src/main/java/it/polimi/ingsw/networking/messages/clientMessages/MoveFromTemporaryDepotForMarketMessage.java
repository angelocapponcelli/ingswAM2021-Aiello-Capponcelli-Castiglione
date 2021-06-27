package it.polimi.ingsw.networking.messages.clientMessages;


import it.polimi.ingsw.server.model.resources.Any;
import it.polimi.ingsw.server.model.resources.Faith;
import it.polimi.ingsw.server.model.resources.ResourceType;


public class MoveFromTemporaryDepotForMarketMessage extends ClientMessage {

    private final ResourceType resourceType;
    private final Integer numberOfContainer;
    private final String depot;

    public MoveFromTemporaryDepotForMarketMessage(String nickname, ResourceType resourceType, Integer numberOfContainer, String depot) {
        super(nickname);
        this.numberOfContainer = numberOfContainer;
        this.resourceType = resourceType;
        this.depot = depot;
    }


    public Integer getNumberOfContainer() {
        return numberOfContainer;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public String getDepot() {
        return this.depot;
    }
}
