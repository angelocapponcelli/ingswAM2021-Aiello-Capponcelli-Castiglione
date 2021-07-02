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

/**
 * Class that manages the edit of the special ability of the production.
 * The user can change the number of faith marbles and the number of any marbles of the production output
 *
 */
public class AdditionalProductionPowerEditController {

    public TextField anyNumber;
    public TextField faithNumber;



    /**
     * Personalizes the output of "ANY" marbles and of the FAITH marbles. It writes the changes int the settings json file
     * @param event the click on okButton
     */
    public void insertedOutput(ActionEvent event){

        Gson gson= new Gson();
        try {
            FileReader reader= new FileReader("src/main/resources/JSONs/editedSettings.json");
            JsonObject jsonObject= gson.fromJson(reader,JsonObject.class);
            JsonObject object= jsonObject.getAsJsonObject("SpecialAbilities").getAsJsonObject("PRODUCTION_POWER").getAsJsonObject("output");

            object.addProperty("ANY", Integer.parseInt(anyNumber.getText()));
            object.addProperty("FAITH",Integer.parseInt(faithNumber.getText()) );

            FileWriter writer= new FileWriter("src/main/resources/JSONs/editedSettings.json");
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



}
