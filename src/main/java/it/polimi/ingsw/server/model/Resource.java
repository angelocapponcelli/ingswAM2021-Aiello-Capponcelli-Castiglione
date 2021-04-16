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
    public void onProduction(RealPlayer realPlayer, Integer molteplicity) throws DepotException {
        PersonalBoard personalBoard;
        StrongBoxDepot strongBoxDepot;
        personalBoard= realPlayer.getPersonalBoard();
        strongBoxDepot= personalBoard.getStrongBoxDepot();
        strongBoxDepot.add(this.type, molteplicity);
        // done realPlayer.getPersonalBoard.getStrongBoxDepot().add(this.type);
    }

    @Override
    public void onTaking(RealPlayer realPlayer) {
        // TODO: realPlayer.getPersonalBoard.getDepotForMarket().add(this.type);
    }
}
