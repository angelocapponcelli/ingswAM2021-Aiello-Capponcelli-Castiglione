package it.polimi.ingsw.server.model;

public class Resource implements Takeable, Producible, Requirement{
    private ResourceType type;

    public Resource(ResourceType type) {
        this.type = type;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    @Override
    public void onProduction(RealPlayer realPlayer) {
        // TODO realPlayer.getPersonalBoard.getStrongBoxDepot().add(this.type);
    }

    @Override
    public void onTaking(RealPlayer realPlayer) {
        // TODO: realPlayer.getPersonalBoard.getDepotForMarket().add(this.type);
    }
}
