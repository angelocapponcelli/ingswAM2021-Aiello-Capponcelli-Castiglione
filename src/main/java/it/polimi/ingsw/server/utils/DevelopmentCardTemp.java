package it.polimi.ingsw.server.utils;

import it.polimi.ingsw.server.model.resources.ResourceType;

import java.util.Map;

public class DevelopmentCardTemp {
    private final Integer id;
    private final Map<ResourceType,Integer> cost;
    private final TypeLevelTemp typeLevel;
    private final ProductionPowerTemp productionPower;
    private final Integer victoryPoints;

    public DevelopmentCardTemp(Integer id, Map<ResourceType, Integer> cost, TypeLevelTemp typeLevel, ProductionPowerTemp productionPower, Integer victoryPoints) {
        this.id = id;
        this.cost = cost;
        this.typeLevel = typeLevel;
        this.productionPower = productionPower;
        this.victoryPoints = victoryPoints;
    }

    public Integer getId() {
        return id;
    }

    public Map<ResourceType, Integer> getCost() {
        return cost;
    }

    public TypeLevelTemp getTypeLevel() {
        return typeLevel;
    }

    public ProductionPowerTemp getProductionPower() {
        return productionPower;
    }

    public Integer getVictoryPoints() {
        return victoryPoints;
    }
}