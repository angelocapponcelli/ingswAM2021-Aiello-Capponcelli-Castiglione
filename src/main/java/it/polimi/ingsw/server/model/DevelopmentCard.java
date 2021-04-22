package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.interfaces.Takeable;

public class DevelopmentCard implements Takeable {
    private Integer id;
    private Cost cost;
    private TypeLevel typeLevel;
    private ProductionPower productionPower;
    private int victoryPoints;


    public DevelopmentCard(Integer id, Cost cost, TypeLevel typeLevel, ProductionPower productionPower, int victoryPoints) {
        this.id = id;
        this.cost = cost;
        this.typeLevel = typeLevel;
        this.productionPower = productionPower;
        this.victoryPoints = victoryPoints;
    }

    public Integer getId() {
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
     * @param realPlayer The player who performs the card's taking.
     */
    @Override
    public void onTaking(RealPlayer realPlayer) {
        int tmpDeck=0;
        /** asks the player which deck and set that value*/
        realPlayer.getPersonalBoard().getPersonalDevelopmentBoard().addCard(tmpDeck,this);
    }
}
