package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.view.GUI.FXGUI;
import javafx.event.ActionEvent;

public class SceneController {

    public void play(ActionEvent actionEvent) {
        FXGUI.getClientController().update();
    }
}
