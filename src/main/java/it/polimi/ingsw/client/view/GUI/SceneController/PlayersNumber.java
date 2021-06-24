package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.view.GUI.FXGUI;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.NewGameMessage;
import javafx.event.ActionEvent;

public class PlayersNumber {

    public void singlePlayer(ActionEvent actionEvent) {
        FXGUI.getClient().sendMessage(new NewGameMessage(1));
    }

    public void twoPlayers(ActionEvent actionEvent) {
        FXGUI.getClient().sendMessage(new NewGameMessage(2));
    }

    public void threePlayers(ActionEvent actionEvent) {
        FXGUI.getClient().sendMessage(new NewGameMessage(3));
    }

    public void fourPlayersButton(ActionEvent actionEvent) {
        FXGUI.getClient().sendMessage(new NewGameMessage(4));
    }

}
