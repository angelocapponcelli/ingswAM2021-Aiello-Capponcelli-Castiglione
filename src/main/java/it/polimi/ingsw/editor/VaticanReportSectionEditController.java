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

public class VaticanReportSectionEditController {
    public TextField points;
    public TextField number;



    public void insertedVictoryPoints(ActionEvent event){
        Gson gson= new Gson();
        try {
            FileReader reader= new FileReader("src/main/resources/JSONs/settings.json");
            JsonObject jsonObject= gson.fromJson(reader,JsonObject.class);
            JsonArray array= jsonObject.getAsJsonObject("FaithTrack").getAsJsonArray("vaticanReportSections");
            JsonObject object= (JsonObject) array.get(Integer.parseInt(number.getText()));
            object.addProperty("victoryPoints", Integer.parseInt(points.getText()));


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
