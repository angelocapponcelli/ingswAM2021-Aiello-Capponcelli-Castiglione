package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.resources.ResourceType;


public class AdditionalProductionPower extends SpecialAbility {
    ResourceType resourceType;
    ProductionPower productionPower;

    public AdditionalProductionPower(ResourceType resourceType) {
        this.resourceType = resourceType;
        ProductionPowerInput productionPowerInput = new ProductionPowerInput();
        productionPowerInput.add(resourceType, 1);
        ProductionPowerOutput productionPowerOutput = new ProductionPowerOutput();
        productionPowerOutput.add(ResourceType.ANY, 1);
        productionPowerOutput.add(ResourceType.FAITH, 1);

    }

    public AdditionalProductionPower(ProductionPower productionPower) {
        this.productionPower = productionPower;
    }

    @Override
    public void onActivation(RealPlayer player) {
        player.getPersonalBoard().addProductionPower(productionPower);
    }
}
