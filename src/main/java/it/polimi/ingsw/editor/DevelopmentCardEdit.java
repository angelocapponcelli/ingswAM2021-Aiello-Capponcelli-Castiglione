package it.polimi.ingsw.editor;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.polimi.ingsw.server.model.interfaces.Payable;
import it.polimi.ingsw.server.model.interfaces.Producible;
import it.polimi.ingsw.server.model.interfaces.Requirement;
import it.polimi.ingsw.server.model.resources.*;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DevelopmentCardEdit {
    public TextField coin;
    public TextField shield;
    public TextField servant;
    public TextField stone;
    public TextField any;
    public TextField faith;
    public TextField coinInput;
    public TextField shieldInput;
    public TextField servantInput;
    public TextField stoneInput;
    public TextField anyInput;
    public TextField coinCost;
    public TextField shieldCost;
    public TextField servantCost;
    public TextField stoneCost;
    public TextField points;
    public TextField id;


    private int coins;
    private int shields;
    private int servants;
    private int stones;
    private int anyNumber;
    private int faiths;
    private int coinsInput;
    private int shieldsInput;
    private int servantsInput;
    private int stonesInput;
    private int anyNumberInput;
    private int numberOfCoins;
    private int numberOfStones;
    private int numberOfShields;
    private int numberOfServants;





    private Map<Producible, Integer> output= new HashMap<>();
    private Map<Producible,Integer> input= new HashMap<>();
    private Map<ResourceType, Integer> cost= new HashMap<>();

    public void editedCard(ActionEvent event){
        setIntegers();
        createOutputMap();
        createInputMap();
        createCostMap();

        writeOnFile(getInput(),getOutput(),Integer.parseInt(points.getText()),Integer.parseInt(id.getText()));



        try {
            Editor.setRoot("editor");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void discard(ActionEvent event){
        try {
            Editor.setRoot("editor");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setIntegers(){
        coins= Integer.parseInt(coin.getText());
        shields= Integer.parseInt(shield.getText());
        servants= Integer.parseInt(servant.getText());
        stones= Integer.parseInt(stone.getText());
        faiths= Integer.parseInt(faith.getText());
        anyNumber= Integer.parseInt(any.getText());
        coinsInput= Integer.parseInt(coinInput.getText());
        shieldsInput= Integer.parseInt(shieldInput.getText());
        servantsInput= Integer.parseInt(servantInput.getText());
        stonesInput= Integer.parseInt(stoneInput.getText());
        anyNumberInput= Integer.parseInt(anyInput.getText());
        numberOfCoins= Integer.parseInt(coinCost.getText());
        numberOfServants= Integer.parseInt(servantCost.getText());
        numberOfStones= Integer.parseInt(stoneCost.getText());
        numberOfShields=Integer.parseInt(shieldCost.getText());
    }
    public void createOutputMap(){
        if (coins>0){
            output.put(Coin.getInstance(),coins);
        }
        if (shields>0){
            output.put(Shield.getInstance(),shields);
        }
        if (stones>0){
            output.put(Stone.getInstance(),stones);
        }
        if (anyNumber >0){
            output.put(Any.getInstance(),anyNumber);
        }
        if (faiths>0){
            output.put(Faith.getInstance(),faiths);
        }
        if(servants>0){
            output.put(Servant.getInstance(), servants);
        }

    }
    public void createCostMap(){
        if (numberOfCoins>0){
            cost.put(ResourceType.COIN,numberOfCoins);
        }
        if (numberOfShields>0){
            cost.put(ResourceType.SHIELD,numberOfShields);
        }
        if (numberOfStones>0){
            cost.put(ResourceType.STONE, numberOfStones);
        }

        if(numberOfServants>0){
            cost.put(ResourceType.SERVANT, numberOfStones);
        }

    }

    public void createInputMap(){

        if (coinsInput>0){
            input.put(Coin.getInstance(),coinsInput);
        }
        if (shieldsInput>0){
            input.put(Shield.getInstance(),shieldsInput);
        }
        if (stonesInput>0){
            input.put(Stone.getInstance(),stonesInput);
        }
        if (anyNumberInput >0){
            input.put(Any.getInstance(),anyNumberInput);
        }
        if(servantsInput>0){
            input.put(Servant.getInstance(), servantsInput);
        }
    }

    public void writeOnFile(Map<Producible,Integer> inputMap, Map<Producible,Integer> outputMap, Integer victoryPoints,Integer idCard){
        Gson gson= new Gson();
        try {
            FileReader reader= new FileReader("src/main/resources/JSONs/settings.json");
            JsonObject jsonObject= gson.fromJson(reader,JsonObject.class);
            JsonArray listOfCards= jsonObject.getAsJsonArray("DevelopmentCards");

            JsonObject cardSelected= (JsonObject) listOfCards.get(idCard-1);
            cardSelected.remove("victoryPoints");
            cardSelected.remove("cost");


            JsonObject power= cardSelected.getAsJsonObject("productionPower");
            power.remove("productionPowerInput");
            power.remove("productionPowerOutput");

            JsonObject costFinal= new JsonObject();
            for(ResourceType type: cost.keySet()){
                costFinal.addProperty(type.toString(), cost.get(type));
            }
            cardSelected.add("cost", costFinal);




            JsonObject inputPower= new JsonObject();
            for (Producible producible: inputMap.keySet()){
                inputPower.addProperty("resourceType", producible.getResourceType().toString());
                inputPower.addProperty("multiplicity", inputMap.get(producible));
            }
            power.add("productionPowerInput", inputPower);

            JsonObject outputPower= new JsonObject();
            for (Producible producible: outputMap.keySet()){
                outputPower.addProperty("resourceType", producible.getResourceType().toString());
                outputPower.addProperty("multiplicity", outputMap.get(producible));
            }
            power.add("productionPowerOutput", outputPower);


            cardSelected.addProperty("victoryPoints", victoryPoints);


            FileWriter writer= new FileWriter("src/main/resources/JSONs/settings.json");
            writer.write(jsonObject.toString());
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Producible,Integer> getOutput(){
        return this.output;
    }

    public Map<Producible,Integer> getInput(){
        return this.input;
    }

    public Map<ResourceType, Integer> getCost() {
        return cost;
    }
}
