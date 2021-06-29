package it.polimi.ingsw.server.model.specialAbilities;

import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.productionPower.ProductionPower;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerInput;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerOutput;
import it.polimi.ingsw.server.model.resources.Resource;
import it.polimi.ingsw.server.model.resources.ResourceType;

/**
 * This is one of the four special abilities. It lets the player to have one more production power
 */

public class SpecialAdditionalProductionPower extends SpecialAbility {
    ProductionPower productionPower;

    /**
     * Class constructor
     * @param resource the resource to which is applied this production power
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
     * Class constructor
     * @param resource the resource to which is applied this production power
     * @param productionPower the production power of this ability
     */
    public SpecialAdditionalProductionPower(Resource resource, ProductionPower productionPower) {
        this.resource = resource;
        this.productionPower = productionPower;

    }

    public SpecialAdditionalProductionPower(ProductionPower productionPower) {
        this.productionPower = productionPower;
    }

    /**
     * Adds this power to the production power in the personal board
     * @param player that activates this special ability
     */
    @Override
    public void onActivation(RealPlayer player) {
        player.getPersonalBoard().addProductionPower(productionPower);
    }
}
