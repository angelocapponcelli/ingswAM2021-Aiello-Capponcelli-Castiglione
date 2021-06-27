package it.polimi.ingsw.server.model.specialAbilities;

import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.productionPower.ProductionPower;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerInput;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerOutput;
import it.polimi.ingsw.server.model.resources.Resource;
import it.polimi.ingsw.server.model.resources.ResourceType;


public class SpecialAdditionalProductionPower extends SpecialAbility {
    ProductionPower productionPower;

    public SpecialAdditionalProductionPower(Resource resource) {
        this.resource = resource;
        ProductionPowerInput productionPowerInput = new ProductionPowerInput();
        productionPowerInput.add(resource, 1);
        ProductionPowerOutput productionPowerOutput = new ProductionPowerOutput();
        productionPowerOutput.add(ResourceType.ANY, 1);
        productionPowerOutput.add(ResourceType.FAITH, 1);

    }

    public SpecialAdditionalProductionPower(Resource resource, ProductionPower productionPower) {
        this.resource = resource;
        this.productionPower = productionPower;

    }

    public SpecialAdditionalProductionPower(ProductionPower productionPower) {
        this.productionPower = productionPower;
    }

    @Override
    public void onActivation(RealPlayer player) {
        player.getPersonalBoard().addProductionPower(productionPower);
    }
}
