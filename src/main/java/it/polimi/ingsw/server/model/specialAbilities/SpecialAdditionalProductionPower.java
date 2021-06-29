package it.polimi.ingsw.server.model.specialAbilities;

import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.productionPower.ProductionPower;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerInput;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerOutput;
import it.polimi.ingsw.server.model.resources.Resource;
import it.polimi.ingsw.server.model.resources.ResourceType;

/**
 * Special Additional Production Power class. It extends Special Ability.
 */
public class SpecialAdditionalProductionPower extends SpecialAbility {
    ProductionPower productionPower;

    /**
     * Class constructor. Instantiates a new Special Additional Production Power.
     * @param resource which characterizes this ability
     */
    public SpecialAdditionalProductionPower(Resource resource) {
        this.resource = resource;
        ProductionPowerInput productionPowerInput = new ProductionPowerInput();
        productionPowerInput.add(resource, 1);
        ProductionPowerOutput productionPowerOutput = new ProductionPowerOutput();
        productionPowerOutput.add(ResourceType.ANY, 1);
        productionPowerOutput.add(ResourceType.FAITH, 1);

    }

    /**
     * Class constructor. Instantiates a new Special Additional Production Power.
     * @param productionPower of this Special Production Power Ability
     */
    public SpecialAdditionalProductionPower(ProductionPower productionPower) {
        this.productionPower = productionPower;
    }

    /**
     * Adds the production power to the personal board of the player
     * @param player that activates this special ability
     */
    @Override
    public void onActivation(RealPlayer player) {
        player.getPersonalBoard().addProductionPower(productionPower);
    }
}
