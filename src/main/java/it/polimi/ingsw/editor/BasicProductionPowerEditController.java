package it.polimi.ingsw.editor;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.server.model.interfaces.Producible;
import it.polimi.ingsw.server.model.resources.*;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class manages to change the basic production input and output
 */
public class BasicProductionPowerEditController {

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



    private Map<Producible, Integer> output= new HashMap<>();

    private Map<Producible,Integer> input= new HashMap<>();


    /**
     * Calls the methods that write the changes. It is called when the button is clicked
     * @param event the click of the ok button
     */
    public void inserted(ActionEvent event){
        setIntegers();
        createOutputMap();
        createInputMap();
        writeOnFile(getInput(), getOutput());
        try {
            Editor.setRoot("editor");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Goes back to the main stage to start to edit again doesn't save the changes
     * @param event the click on the discard button
     */

    public void discard(ActionEvent event){
        try {
            Editor.setRoot("editor");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a map that represents the output production
     */
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

    /**
     * Creates a map that represents the input production
     */
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

    /**
     *
     * @return map of output productin
     */
    public Map<Producible,Integer> getOutput(){
        return this.output;
    }

    /**
     *
     * @return map of input production
     */
    public Map<Producible,Integer> getInput(){
        return this.input;
    }


    /**
     * Converts the text strings in integers
     */
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



    }

    /**
     * Writes and saves the changes on the json file
     * @param inputMap the map that represents the input production
     * @param outputMap the map that represents the output production
     */
    public void writeOnFile(Map<Producible,Integer> inputMap,Map<Producible,Integer> outputMap){
        Gson gson= new Gson();
        try {
            FileReader reader= new FileReader("src/main/resources/JSONs/editedSettings.json");
            JsonObject jsonObject= gson.fromJson(reader,JsonObject.class);
            JsonArray powerOutput= jsonObject.getAsJsonObject("BoardBasicProductionPower").getAsJsonArray("productionPowerOutput");
            JsonArray powerInput= jsonObject.getAsJsonObject("BoardBasicProductionPower").getAsJsonArray("productionPowerInput");


            powerOutput.remove(0);
            powerInput.remove(0);

            for (Producible producible: inputMap.keySet()){
                JsonObject tmp= new JsonObject();
                tmp.addProperty("resourceType", producible.getResourceType().toString());
                tmp.addProperty("multiplicity", inputMap.get(producible));
                powerInput.add(tmp);

            }


            for (Producible producible: outputMap.keySet()){
                JsonObject tmp1= new JsonObject();
                tmp1.addProperty("resourceType", producible.getResourceType().toString());
                tmp1.addProperty("multiplicity", outputMap.get(producible));
                powerOutput.add(tmp1);

            }



            FileWriter writer= new FileWriter("src/main/resources/JSONs/editedSettings.json");
            writer.write(jsonObject.toString());
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
