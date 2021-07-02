package it.polimi.ingsw.editor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Manages to bring the user in different branches of the editor based on the interaction
 */
public class EditorController {
    /**
     * Changes the scene to the one which is used to edit the development cards
     * @param actionEvent the click on the development card button
     */
    @FXML
    public void editDevCards(ActionEvent actionEvent) {
        try {
            Editor.setRoot("editDevCard");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Changes the scene to the one which is used to edit the faith track
     * @param actionEvent the click on the faith track button
     */
    public void editFaithTrack(ActionEvent actionEvent) {
        try {
            Editor.setRoot("CellOrVatican");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Changes the scene to the one which is used to edit the leader cards
     * @param actionEvent the click on the leader cards button
     */
    public void editLeaderCards(ActionEvent actionEvent) {
        try {
            Editor.setRoot("editLeaderCard");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Changes the scene to the one which is used to edit the basic production
     * @param actionEvent the click on the Basic Production Power button
     */
    public void editBoardProductionPower(ActionEvent actionEvent) {
        try {
            Editor.setRoot("editBasicProductionPower");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Changes the scene to the one which is used to edit the special abilities
     * @param event the click on the special abilities button
     */
    public void editSpecialAbility(ActionEvent event){
        try {
            Editor.setRoot("editSpecialAbility");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
