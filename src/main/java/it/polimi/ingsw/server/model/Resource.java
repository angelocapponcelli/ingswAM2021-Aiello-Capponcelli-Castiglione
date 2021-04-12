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
    public void onProduction(Player player) {
        // TODO: player.getStrongbox().add(this.type);
    }

    @Override
    public void onTaking(Player player) {
        // TODO: player.getDepotForMarket().add(this.type);
    }
}
