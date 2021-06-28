package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.view.GUI.FXGUI;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.NewGameMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PlayersNumber {

    @FXML
    private BorderPane waitPane;
    @FXML
    private VBox choosePane;

    public void singlePlayer(ActionEvent actionEvent) {
        FXGUI.getClient().sendMessage(new NewGameMessage(1));
        choosePane.setVisible(false);
        waitPane.setVisible(true);
    }

    public void twoPlayers(ActionEvent actionEvent) {
        FXGUI.getClient().sendMessage(new NewGameMessage(2));
        choosePane.setVisible(false);
        waitPane.setVisible(true);
    }

    public void threePlayers(ActionEvent actionEvent) {
        FXGUI.getClient().sendMessage(new NewGameMessage(3));
        choosePane.setVisible(false);
        waitPane.setVisible(true);
    }

    public void fourPlayersButton(ActionEvent actionEvent) {
        FXGUI.getClient().sendMessage(new NewGameMessage(4));
        choosePane.setVisible(false);
        waitPane.setVisible(true);
    }

}
