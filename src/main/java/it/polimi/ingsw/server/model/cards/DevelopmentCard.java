package it.polimi.ingsw.server.model.cards;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedDevelopmentCard;
import it.polimi.ingsw.server.model.interfaces.Takeable;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.productionPower.ProductionPower;
import it.polimi.ingsw.utils.exceptions.PersonalBoardException;

/**
 * Development card
 */
public class DevelopmentCard implements Takeable {
    private final Integer id;
    private final Cost cost;
    private final TypeLevel typeLevel;
    private final ProductionPower productionPower;
    private final int victoryPoints;

    /**
     * Class Constructor.
     * @param id the value that is unique for each card
     * @param cost the quantity of resources that the player has to pay to buy the card
     * @param typeLevel the color and level of the card
     * @param productionPower the power that can be used for the production
     * @param victoryPoints the points that the player receive when he buys the card
     */
    public DevelopmentCard(Integer id, Cost cost, TypeLevel typeLevel, ProductionPower productionPower, int victoryPoints) {
        this.id = id;
        this.cost = cost;
        this.typeLevel = typeLevel;
        this.productionPower = productionPower;
        this.victoryPoints = victoryPoints;
    }

    /**
     * @return id of this card
     */

    public Integer getId() {
        return id;
    }

    /**
     * @return cost of this card
     */

    public Cost getCost() {
        return cost;
    }

    /**
     * @return type and level of this card
     */

    public TypeLevel getTypeLevel() {
        return typeLevel;
    }

    /**
     * @return productionPower of this card
     */

    public ProductionPower getProductionPower() {
        return productionPower;
    }

    /**
     * @return victoryPoints of this card
     */

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
        try {
            realPlayer.getPersonalBoard().getPersonalDevelopmentBoard().addCard(this);
        } catch (PersonalBoardException e) {
            e.printStackTrace();
        }
    }

    /**
     *Gets a reduced version of this card with no methods but just the attributes.
     * @return reduced DevelopmentCard modeled on this card
     */

    public ReducedDevelopmentCard toReduced(){
        return new ReducedDevelopmentCard(this);
    }
}
