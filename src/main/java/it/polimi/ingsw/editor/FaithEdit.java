package it.polimi.ingsw.editor;

import javafx.event.ActionEvent;

import java.io.IOException;

public class FaithEdit {
    public void cellClick(ActionEvent event){
        try {
            Editor.setRoot("editCell");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void vaticanClick(ActionEvent event){
        try {
            Editor.setRoot("editVaticanReportSection");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
