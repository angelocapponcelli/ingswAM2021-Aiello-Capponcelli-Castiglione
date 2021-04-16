package it.polimi.ingsw.server.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.server.model.*;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Initialize all the Development Card by reading a json file.
 *
 */
public class DevelopmentCardFactory {

    public void loadCards() throws FileNotFoundException {
        List<DevelopmentCardTemp> tempDeck;
        List<DevelopmentCard> deck = new ArrayList<>();
        Type listOfMyClassObject = new TypeToken<ArrayList<DevelopmentCardTemp>>() {}.getType();

        Gson gson = new Gson();
        tempDeck = gson.fromJson(new FileReader("src/main/resources/DevelopmentCard.json"), listOfMyClassObject);

        for (DevelopmentCardTemp developmentCardTemp: tempDeck){
            Cost cost = new Cost();
            ProductionPowerInput productionPowerInput = new ProductionPowerInput();
            ProductionPowerOutput productionPowerOutput = new ProductionPowerOutput();

            for(Map.Entry<ResourceType, Integer> entry : developmentCardTemp.getCost().entrySet()){
                cost.add(entry.getKey(),entry.getValue());
            }

            for(Map.Entry<ResourceType, Integer> entry : developmentCardTemp.getProductionPower().getProductionPowerInput().entrySet()){
                productionPowerInput.add(entry.getKey(),entry.getValue());
            }
            for(Map.Entry<ResourceType, Integer> entry : developmentCardTemp.getProductionPower().getProductionPowerOutput().entrySet()){
                productionPowerOutput.add(entry.getKey(),entry.getValue());
            }
            ProductionPower productionPower= new ProductionPower(productionPowerInput,productionPowerOutput);


            TypeLevel typeLevel= new TypeLevel(developmentCardTemp.getTypeLevel().getType(),developmentCardTemp.getTypeLevel().getLevel());
            deck.add(new DevelopmentCard(developmentCardTemp.getId(), cost, typeLevel,productionPower,developmentCardTemp.getVictoryPoints()));


        }


        //deck.forEach(card-> card.getCost().getCost().forEach((k, v)-> System.out.println(card.getId() + " " + k.toString() + " " + v)));
    }
}