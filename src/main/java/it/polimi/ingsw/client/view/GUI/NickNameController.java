package it.polimi.ingsw.client.view.GUI;
import it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages.NicknameMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NickNameController{

    @FXML
    private TextField nickName;

    @FXML
    public void askForCreateOrJoinGame(ActionEvent actionEvent) throws IOException {
        FXGui.getClient().sendMessage(new NicknameMessage(nickName.getText()));
        FXGui.setRoot("createOrJoin");

    }
}
