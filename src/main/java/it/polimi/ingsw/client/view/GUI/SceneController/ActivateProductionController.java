package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.view.GUI.FXGUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ActivateProductionController {

    public CheckBox baseProduction;
    public CheckBox firstCardProduction;
    public CheckBox secondCardProduction;
    public CheckBox thirdCardProduction;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void onConfirmProduction(ActionEvent event) {
        if (firstCardProduction.isSelected()) ;//todo activate first card production
        if (secondCardProduction.isSelected()) ;//todo activate second card production
        if (thirdCardProduction.isSelected()) ;//todo activate third card production
        if (baseProduction.isSelected()) {
            Platform.runLater(()-> {
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/activateBaseProduction.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage = FXGUI.getStage();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            });
        }
    }
}
