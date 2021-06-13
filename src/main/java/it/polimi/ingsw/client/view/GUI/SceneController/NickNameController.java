package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.view.GUI.FXGUI;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.NicknameMessage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NickNameController {

    public TextField nickName;
    public Button loginButton;

    public void askForCreateOrJoinGame(ActionEvent actionEvent) {
        FXGUI.getClient().sendMessage(new NicknameMessage(nickName.getText()));
    }
}
