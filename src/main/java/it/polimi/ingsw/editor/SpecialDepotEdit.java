package it.polimi.ingsw.editor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class consents the client to change the special depot capacity
 */
public class SpecialDepotEdit {
    public TextField capacity;

    /**
     * Changes the capacity of the special depot and returns to the main stage to continue to modify
     * @param event the click on the ok button
     */
    public void capacityInserted(ActionEvent event){

        Gson gson= new Gson();
        try {
            FileReader reader= new FileReader("src/main/resources/JSONs/editedSettings.json");
            JsonObject jsonObject= gson.fromJson(reader,JsonObject.class);
            JsonObject object= jsonObject.getAsJsonObject("SpecialAbilities");

            object.addProperty("DEPOT", Integer.parseInt(capacity.getText()));

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

}
