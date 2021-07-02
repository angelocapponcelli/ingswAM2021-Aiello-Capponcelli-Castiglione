package it.polimi.ingsw.editor;


import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import it.polimi.ingsw.server.model.specialAbilities.SpecialWhiteMarble;
import it.polimi.ingsw.utils.parsers.SettingsParser;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.*;

/**
 * Changes the amount of resources you can get out of the white marble ability
 */
public class WhiteMarbleEditController {

    public TextField insertedOutput;


    /**
     * Changes and saves on the json file the new amount for the white marble
     * @param event the click on the ok button
     */
    public void outputInserted(ActionEvent event){





        Gson gson= new Gson();
        try {
            FileReader reader= new FileReader("src/main/resources/JSONs/editedSettings.json");
            JsonObject jsonObject= gson.fromJson(reader,JsonObject.class);
            JsonObject object= jsonObject.getAsJsonObject("SpecialAbilities");
            object.addProperty("WHITE_MARBLE", Integer.parseInt(insertedOutput.getText()));

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
