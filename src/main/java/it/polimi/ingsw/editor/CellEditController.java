package it.polimi.ingsw.editor;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CellEditController {
    public TextField points;
    public TextField cellNumber;


    /**
     * Changes the points of the specified cell
     * @param event the click on the ok button
     */
    public void insertedPoints(ActionEvent event){
        Gson gson= new Gson();
        try {
            FileReader reader= new FileReader("src/main/resources/JSONs/settings.json");
            JsonObject jsonObject= gson.fromJson(reader,JsonObject.class);
            JsonArray array= jsonObject.getAsJsonObject("FaithTrack").getAsJsonArray("track");

            JsonObject object= (JsonObject)array.get(Integer.parseInt(cellNumber.getText()));
            object.addProperty("victoryPoints", Integer.parseInt(points.getText()));

            FileWriter writer= new FileWriter("src/main/resources/JSON/settings.json");
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
