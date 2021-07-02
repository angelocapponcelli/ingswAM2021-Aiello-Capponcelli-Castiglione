package it.polimi.ingsw.editor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EditorController {

    @FXML
    public void editDevCards(ActionEvent actionEvent) {
        try {
            Editor.setRoot("editDevCard");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editFaithTrack(ActionEvent actionEvent) {
        try {
            Editor.setRoot("CellOrVatican");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editLeaderCards(ActionEvent actionEvent) {
        try {
            Editor.setRoot("editLeaderCard");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editBoardProductionPower(ActionEvent actionEvent) {
        try {
            Editor.setRoot("editBasicProductionPower");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editSpecialAbility(ActionEvent event){
        try {
            Editor.setRoot("editSpecialAbility");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
