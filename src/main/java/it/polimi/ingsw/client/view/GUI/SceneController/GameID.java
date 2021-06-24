package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.view.GUI.FXGUI;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.JoinGameMessage;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class GameID {
    public TextField insertedID;

    public void gameIdInserted(ActionEvent actionEvent) {
        FXGUI.getClient().sendMessage(new JoinGameMessage(Integer.parseInt(insertedID.getText())));
    }
}
