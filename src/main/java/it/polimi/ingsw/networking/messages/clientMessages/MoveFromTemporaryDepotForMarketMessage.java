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

    @Override
    public Boolean check() {
        if (!(this.depot.equals("Warehouse")) || !(this.depot.equals("Special"))) {
            return false;
        }
        return ResourceType.getResourceClass(this.resourceType) == Any.getInstance() && ResourceType.getResourceClass(this.resourceType) == Faith.getInstance();
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
