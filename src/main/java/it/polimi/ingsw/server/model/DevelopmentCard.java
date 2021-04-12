package it.polimi.ingsw.server.model;

public class DevelopmentCard implements Takeable{
    private String id;
    private Cost cost;
    private TypeLevel typeLevel;
    private ProductionPower productionPower;
    private int victoryPoints;

    public DevelopmentCard(String id, Cost cost, TypeLevel typeLevel, ProductionPower productionPower, int victoryPoints) {
        this.id = id;
        this.cost = cost;
        this.typeLevel = typeLevel;
        this.productionPower = productionPower;
        this.victoryPoints = victoryPoints;
    }

    public String getId() {
        return id;
    }

    public Cost getCost() {
        return cost;
    }

    public TypeLevel getTypeLevel() {
        return typeLevel;
    }

    public ProductionPower getProductionPower() {
        return productionPower;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    /**
     * Add the development card on the player's personal board
     *
     * @param player The player who performs the card's taking.
     */
    @Override
    public void onTaking(Player player) {

    }
}
