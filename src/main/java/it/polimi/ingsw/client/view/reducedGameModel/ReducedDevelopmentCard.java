package it.polimi.ingsw.client.view.reducedGameModel;

import it.polimi.ingsw.server.model.cards.DevelopmentCard;
import it.polimi.ingsw.server.model.cards.TypeLevel;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ReducedDevelopmentCard implements Serializable {
    private final int id;
    private final Map<ResourceType, Integer> cost = new HashMap<>();
    private final TypeLevel typeLevel;
    private final Map<ResourceType, Integer> productionPowerInput = new HashMap<>();
    private final Map<ResourceType, Integer> productionPowerOutput = new HashMap<>();
    private final int victoryPoints;

    public ReducedDevelopmentCard(DevelopmentCard developmentCard) {
        this.id = developmentCard.getId();
        this.victoryPoints = developmentCard.getVictoryPoints();

        developmentCard.getCost().getCost().forEach(((resource, multiplicity) -> cost.put(resource.getResourceType(), multiplicity) ));


        developmentCard.getProductionPower().getProductionInput().getProductionPowerInput()
                .forEach( (resource, multiplicity) -> productionPowerInput.put(resource.getResourceType(), multiplicity) );

        developmentCard.getProductionPower().getProductionOutput().getProductionPowerOutput()
                .forEach( (resource, multiplicity) -> productionPowerOutput.put(resource.getResourceType(), multiplicity) );

        typeLevel = new TypeLevel(developmentCard.getTypeLevel().getType(), developmentCard.getTypeLevel().getLevel());
    }

    public int getId() {
        return id;
    }

    public Map<ResourceType, Integer> getCost() {
        return cost;
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


    public TypeLevel getTypeLevel() {
        return typeLevel;
    }

    public String costToCLI(){
        StringBuffer temp = new StringBuffer();
        cost.forEach((key,value) -> temp.append(value).append("x").append(key.toCLI()).append(" "));
        return temp.toString();
    }

    public String productionPowerInputToCli(){
        StringBuffer temp = new StringBuffer();
        productionPowerInput.forEach((k,v) -> temp.append(v).append("x").append(k.toCLI()));
        return temp.toString();
    }

    public String productionPowerOutputToCli(){
        StringBuffer temp = new StringBuffer();
        productionPowerOutput.forEach((k,v) -> temp.append(v).append("x").append(k.toCLI()));
        return temp.toString();
    }



}
