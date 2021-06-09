package it.polimi.ingsw.client.view.GUI;

import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.NewGameMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;

public class PlayersNumber {

    @FXML
    public void singlePlayer(ActionEvent actionEvent) {
        System.out.println("1");
        FXGui.getClient().sendMessage(new NewGameMessage(1));
    }
    @FXML
    public void twoPlayers(ActionEvent actionEvent) {
        System.out.println("2");
        FXGui.getClient().sendMessage(new NewGameMessage(2));
    }
    @FXML
    public void threePlayers(ActionEvent actionEvent) {
        System.out.println("3");
        FXGui.getClient().sendMessage(new NewGameMessage(3));
    }
    @FXML
    public void fourPlayersButton(ActionEvent actionEvent) {
        System.out.println("4");
        FXGui.getClient().sendMessage(new NewGameMessage(4));
    }
}
