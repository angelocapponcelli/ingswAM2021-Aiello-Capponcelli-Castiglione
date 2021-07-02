package it.polimi.ingsw.editor;

import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * To choose if modify the cells of the vatican sections
 */
public class FaithEdit {
    /**
     *  Changes the scene to the one which is used to edit the cells
     * @param event the click on the cell button
     */
    public void cellClick(ActionEvent event){
        try {
            Editor.setRoot("editCell");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Changes the scene to the one which is used to edit the vatican sections
     * @param event the click on the vatican section button
     */
    public void vaticanClick(ActionEvent event){
        try {
            Editor.setRoot("editVaticanReportSection");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
