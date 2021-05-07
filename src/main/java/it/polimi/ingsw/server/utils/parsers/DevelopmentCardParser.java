package it.polimi.ingsw.server.utils.parsers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.server.model.cards.Cost;
import it.polimi.ingsw.server.model.cards.DevelopmentCard;
import it.polimi.ingsw.server.model.cards.TypeLevel;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.productionPower.ProductionPower;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerInput;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerOutput;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Initialize all the Development Card by reading a json file.
 */
public class DevelopmentCardParser {

    public static List<DevelopmentCard> getCards() throws FileNotFoundException {
        List<DevelopmentCardTemp> tempDeck;
        List<DevelopmentCard> deck = new ArrayList<>();
        Type listOfMyClassObject = new TypeToken<ArrayList<DevelopmentCardTemp>>() {
        }.getType();

        Gson gson = new Gson();
        tempDeck = gson.fromJson(new FileReader("src/main/resources/DevelopmentCard.json"), listOfMyClassObject);

        for (DevelopmentCardTemp developmentCardTemp : tempDeck) {
            Cost cost = new Cost();
            ProductionPowerInput productionPowerInput = new ProductionPowerInput();
            ProductionPowerOutput productionPowerOutput = new ProductionPowerOutput();

            for (Map.Entry<ResourceType, Integer> entry : developmentCardTemp.getCost().entrySet()) {
                cost.add(entry.getKey(), entry.getValue());
            }

            for (Map.Entry<ResourceType, Integer> entry : developmentCardTemp.getProductionPower().getProductionPowerInput().entrySet()) {
                productionPowerInput.add(ResourceType.getResourceClass(entry.getKey()), entry.getValue());
            }
            for (Map.Entry<ResourceType, Integer> entry : developmentCardTemp.getProductionPower().getProductionPowerOutput().entrySet()) {
                productionPowerOutput.add(entry.getKey(), entry.getValue());
            }
            ProductionPower productionPower = new ProductionPower(productionPowerInput, productionPowerOutput);

            TypeLevel typeLevel = new TypeLevel(developmentCardTemp.getTypeLevel().getType(), developmentCardTemp.getTypeLevel().getLevel());
            deck.add(new DevelopmentCard(developmentCardTemp.getId(), cost, typeLevel, productionPower, developmentCardTemp.getVictoryPoints()));


        }
        //deck.forEach(card-> card.getCost().getCost().forEach((k, v)-> System.out.println(card.getId() + " " + k.toString() + " " + v)));
        return deck;

    }
}


class ProductionPowerTemp {
    private final Map<ResourceType, Integer> productionPowerInput;
    private final Map<ResourceType, Integer> productionPowerOutput;

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

class TypeLevelTemp {
    private final Colors type;
    private final Integer level;

    public TypeLevelTemp(Colors type, Integer level) {
        this.type = type;
        this.level = level;
    }

    public Colors getType() {
        return type;
    }

    public Integer getLevel() {
        return level;
    }
}

class DevelopmentCardTemp {
    private final Integer id;
    private final Map<ResourceType, Integer> cost;
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