package it.polimi.ingsw.server.utils;

import it.polimi.ingsw.server.model.resources.ResourceType;

import java.util.Map;

public class ProductionPowerTemp {
    private Map<ResourceType,Integer> productionPowerInput;
    private Map<ResourceType,Integer> productionPowerOutput;

    public ProductionPowerTemp(Map<ResourceType, Integer> productionPowerInput, Map<ResourceType, Integer> productionPowerOutput) {
        this.productionPowerInput = productionPowerInput;
        this.productionPowerOutput = productionPowerOutput;
    }

    public Map<ResourceType, Integer> getProductionPowerInput() {
        return productionPowerInput;
    }

    public Map<ResourceType, Integer> getProductionPowerOutput() {
        return productionPowerOutput;
    }
}
