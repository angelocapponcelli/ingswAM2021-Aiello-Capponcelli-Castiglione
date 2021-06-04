package it.polimi.ingsw.server.model.cards;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedDevelopmentCard;
import it.polimi.ingsw.server.model.interfaces.Takeable;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.productionPower.ProductionPower;

public class DevelopmentCard implements Takeable {
    private final Integer id;
    private final Cost cost;
    private final TypeLevel typeLevel;
    private final ProductionPower productionPower;
    private final int victoryPoints;


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
        realPlayer.getPersonalBoard().getPersonalDevelopmentBoard().addCard(0, this);
    }

    public ReducedDevelopmentCard toReduced(){
        return new ReducedDevelopmentCard(this);
    }
}
