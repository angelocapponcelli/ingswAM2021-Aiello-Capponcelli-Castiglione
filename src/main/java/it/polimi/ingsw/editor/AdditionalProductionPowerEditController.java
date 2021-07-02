package it.polimi.ingsw.editor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import it.polimi.ingsw.server.model.interfaces.Producible;
import it.polimi.ingsw.server.model.resources.*;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.effect.SepiaTone;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdditionalProductionPowerEditController {

    public TextField anyNumber;
    public Integer anyNumbers;

    /**
     * Personalizes the output of "ANY" marbles
     * @param event the click on okButton
     */
    public void insertedOutput(ActionEvent event){
        anyNumbers= Integer.parseInt(anyNumber.getText());
        Gson gson= new Gson();
        try {
            FileReader reader= new FileReader("src/main/resources/JSONs/settings.json");
            JsonObject jsonObject= gson.fromJson(reader,JsonObject.class);
            JsonObject object= jsonObject.getAsJsonObject("SpecialAbilities").getAsJsonObject("PRODUCTION_POWER").getAsJsonObject("output");

            object.addProperty("ANY", Integer.parseInt(anyNumber.getText()));

            FileWriter writer= new FileWriter("src/main/resources/JSONs/settings.json");
            writer.write(jsonObject.toString());
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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



}
