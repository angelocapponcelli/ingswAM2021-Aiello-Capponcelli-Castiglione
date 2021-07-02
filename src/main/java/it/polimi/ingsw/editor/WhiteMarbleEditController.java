package it.polimi.ingsw.editor;


import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import it.polimi.ingsw.server.model.specialAbilities.SpecialWhiteMarble;
import it.polimi.ingsw.utils.parsers.SettingsParser;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.*;

public class WhiteMarbleEditController {

    public TextField insertedOutput;

    private Integer number;

    public void outputInserted(ActionEvent event){





        Gson gson= new Gson();
        try {
            FileReader reader= new FileReader("src/main/resources/JSONs/settings.json");
            JsonObject jsonObject= gson.fromJson(reader,JsonObject.class);
            JsonObject object= jsonObject.getAsJsonObject("SpecialAbilities");
            object.addProperty("WHITE_MARBLE", Integer.parseInt(insertedOutput.getText()));

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

    public Integer getNumber(){
        return this.number;
    }
}
