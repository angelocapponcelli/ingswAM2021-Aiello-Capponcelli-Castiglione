package it.polimi.ingsw.server.model.productionPower;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedProductionPower;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.util.HashMap;
import java.util.Map;

/**
 * Used by the development cards, by the board's basic production power and by the special ability that give an additional production power
 */
public class ProductionPower {
    private final ProductionPowerInput productionPowerInput;
    private final ProductionPowerOutput productionPowerOutput;

    public ProductionPower(ProductionPowerInput productionPowerInput, ProductionPowerOutput productionPowerOutput) {
        this.productionPowerInput = productionPowerInput;
        this.productionPowerOutput = productionPowerOutput;
    }

    public ProductionPowerInput getProductionInput() {
        return productionPowerInput;
    }

    public ProductionPowerOutput getProductionOutput() {
        return productionPowerOutput;
    }

    public ReducedProductionPower toReduced(){

        Map<ResourceType, Integer> input = new HashMap<>();
        Map<ResourceType, Integer> output = new HashMap<>();

        productionPowerInput.getProductionPowerInput().forEach((k,v) -> input.put(k.getResourceType(), v));
        productionPowerOutput.getProductionPowerOutput().forEach((k,v) -> output.put(k.getResourceType(), v));

        return new ReducedProductionPower(input,output);


    }
}
