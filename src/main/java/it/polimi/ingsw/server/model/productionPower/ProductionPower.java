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

    /**
     * Class constructor. Instantiates a new Production Power.
     * @param productionPowerInput the resources that the player has to pay
     * @param productionPowerOutput the resources the player receives if he decides to activate the production
     */
    public ProductionPower(ProductionPowerInput productionPowerInput, ProductionPowerOutput productionPowerOutput) {
        this.productionPowerInput = productionPowerInput;
        this.productionPowerOutput = productionPowerOutput;
    }

    /**
     * Gets the map that represents the resources the player has to pay to activate production
     * @return map of resources and quantity that the player has to pay
     */
    public ProductionPowerInput getProductionInput() {
        return productionPowerInput;
    }

    /**
     * Gets the map that represents the resources the player receives when he decides to activate production
     * @return map of resources and quantity that th player receives
     */

    public ProductionPowerOutput getProductionOutput() {
        return productionPowerOutput;
    }

    /**
     * Performs and gets the reduced version of the production power
     * @return reduced version of the production power
     */
    public ReducedProductionPower toReduced(){

        Map<ResourceType, Integer> input = new HashMap<>();
        Map<ResourceType, Integer> output = new HashMap<>();

        productionPowerInput.getProductionPowerInput().forEach((k,v) -> input.put(k.getResourceType(), v));
        productionPowerOutput.getProductionPowerOutput().forEach((k,v) -> output.put(k.getResourceType(), v));

        return new ReducedProductionPower(input,output);


    }
}
