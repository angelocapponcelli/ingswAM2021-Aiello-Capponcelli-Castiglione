package it.polimi.ingsw.client.view.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class CreateOrJoin {

    @FXML
    public void askForPlayersNumber(ActionEvent actionEvent) throws IOException {
        FXGui.setRoot("playersNumber");
    }

    @FXML
    public void askForGameID(ActionEvent actionEvent) throws IOException {
        FXGui.setRoot("gameID");
    }
}
