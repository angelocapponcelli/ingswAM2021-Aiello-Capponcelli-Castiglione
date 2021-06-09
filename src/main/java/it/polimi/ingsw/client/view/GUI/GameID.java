package it.polimi.ingsw.client.view.GUI;

import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.JoinGameMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class GameID {

    @FXML
    public TextField insertedID;

    public void gameIdInserted(ActionEvent actionEvent) {
        FXGui.getClient().sendMessage(new JoinGameMessage(Integer.parseInt(insertedID.getText())));
    }
}
