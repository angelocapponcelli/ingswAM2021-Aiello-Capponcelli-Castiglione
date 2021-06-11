package it.polimi.ingsw.client.view.reducedGameModel;

import it.polimi.ingsw.server.model.resources.ResourceType;

import java.io.Serializable;
import java.util.Map;

public class ReducedProductionPower implements Serializable {

    private Map<ResourceType, Integer> productionPowerInput;
    private Map<ResourceType, Integer> productionPowerOutput;

    public ReducedProductionPower() {

    }

    public Map<ResourceType, Integer> getProductionPowerInput() {
        return productionPowerInput;
    }

    public ReducedProductionPower(Map<ResourceType, Integer> productionPowerInput, Map<ResourceType, Integer> productionPowerOutput) {
        this.productionPowerInput = productionPowerInput;
        this.productionPowerOutput = productionPowerOutput;
    }

    public void setProductionPowerInput(Map<ResourceType, Integer> productionPowerInput) {
        this.productionPowerInput = productionPowerInput;
    }

    public Map<ResourceType, Integer> getProductionPowerOutput() {
        return productionPowerOutput;
    }

    public void setProductionPowerOutput(Map<ResourceType, Integer> productionPowerOutput) {
        this.productionPowerOutput = productionPowerOutput;
    }
}
