package it.polimi.ingsw.client.view.reducedGameModel;

import it.polimi.ingsw.server.model.cards.DevelopmentCard;
import it.polimi.ingsw.server.model.interfaces.Producible;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.resources.Resource;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.util.HashMap;
import java.util.Map;

public class ReducedDevelopmentCard {
    private final int id;
    private final Map<ResourceType, Integer> cost;
    private final Colors type;
    private final int level;
    private final Map<ResourceType, Integer> productionPowerInput;
    private final Map<ResourceType, Integer> productionPowerOutput;
    private final int victoryPoints;

    public ReducedDevelopmentCard(DevelopmentCard developmentCard) {
        this.id = developmentCard.getId();
        this.victoryPoints = developmentCard.getVictoryPoints();
        Map<ResourceType, Integer> tmp = new HashMap<>();
        for (Map.Entry<Resource, Integer> entry : developmentCard.getProductionPower().getProductionInput().getProductionPowerInput().entrySet()) {
            tmp.put(entry.getKey().getResourceType(), entry.getValue());
        }
        productionPowerInput = tmp;
        tmp.clear();
        for (Map.Entry<Producible, Integer> entry : developmentCard.getProductionPower().getProductionOutput().getProductionPowerOutput().entrySet()) {
            tmp.put(entry.getKey().getResourceType(), entry.getValue());
        }
        productionPowerOutput = tmp;
        tmp.clear();
        for (Map.Entry<Resource, Integer> entry : developmentCard.getCost().getCost().entrySet()) {
            tmp.put(entry.getKey().getResourceType(), entry.getValue());
        }
        cost = tmp;
        type = developmentCard.getTypeLevel().getType();
        level = developmentCard.getTypeLevel().getLevel();
    }

    public int getId() {
        return id;
    }

    public Map<ResourceType, Integer> getCost() {
        return cost;
    }

    public int getLevel() {
        return level;
    }

    public Map<ResourceType, Integer> getProductionPowerInput() {
        return productionPowerInput;
    }

    public Map<ResourceType, Integer> getProductionPowerOutput() {
        return productionPowerOutput;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public Colors getType() {
        return type;
    }
}
