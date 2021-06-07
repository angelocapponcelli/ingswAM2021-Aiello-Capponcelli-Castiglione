package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.controller.MY_TURN;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class GUI extends View {

    @FXML
    public Label nicknameErrorMessage;
    @FXML
    public TextField nicknameTextField;
    @FXML
    public AnchorPane nicknamePane;
    @FXML
    private Button nicknameButton;
    @FXML
    public AnchorPane createJoinGamePane;
    @FXML
    private Button createButton;
    @FXML
    private Button joinButton;
    @FXML
    public AnchorPane selectPlayersNumberPane;
    @FXML
    private Button onePlayerButton;
    @FXML
    private Button twoPlayerButton;
    @FXML
    private Button threePlayerButton;
    @FXML
    private Button fourPlayerButton;
    @FXML
    public AnchorPane joinGamePane;
    @FXML
    private Button joinGameButton;
    @FXML
    public TextField joinGameTextField;

    @FXML
    public void initialize() {
        nicknameButton.setOnAction(event -> {
            nicknameButtonAction();
        });
        createButton.setOnAction(event -> {
            createJoinGamePane.setVisible(false);
            selectPlayersNumberPane.setVisible(true);
        });
        joinButton.setOnAction(event -> {
            createJoinGamePane.setVisible(false);
            joinGamePane.setVisible(true);
            FXGui.ChangePane("FXML/WaitLobby.fxml");
        });
        onePlayerButton.setOnAction(event -> {
           // client.sendMessage(new NewGameMessage(1));
        });
        twoPlayerButton.setOnAction(event -> {
           // client.sendMessage(new NewGameMessage(2));
        });
        threePlayerButton.setOnAction(event -> {
           // client.sendMessage(new NewGameMessage(3));
        });
        fourPlayerButton.setOnAction(event -> {
           // client.sendMessage(new NewGameMessage(4));
        });
        joinGameButton.setOnAction(event -> {

        });
    }

    public GUI(Client client) {
        super(client);
    }

    @Override
    public void askForNickName() {
        FXGui.ChangePane("FXML/Login.fxml");
        nicknamePane.setVisible(true);
        createJoinGamePane.setVisible(false);
        selectPlayersNumberPane.setVisible(false);
        joinGamePane.setVisible(false);
    }

    @Override
    public void refresh() {
    }

    @Override
    public void marketTrayDraw() {

    }

    @Override
    public void inHandLeaderCardsDraw() {

    }


    @Override
    public void askForLeaderCardsToDiscard() {

    }

    @Override
    public void askForCreateOrJoinGame() {

    }

    @Override
    public void temporaryDepotDraw() {

    }

    @Override
    public void wareHouseDraw() {

    }

    @Override
    public void devCardGridDraw() {

    }

    @Override
    public void personalDevelopmentBoardDraw() {

    }

    @Override
    public MY_TURN askForMainAction() {
        return null;
    }

    @Override
    public void takeFromMarket() {

    }

    @Override
    public void buyDevCard() {

    }

    @Override
    public void faithTrackDraw() {

    }

    @Override
    public boolean askForInitialResources() {
        return true;
    }

    @Override
    public void moveFromTemporary() {

    }

    @Override
    public void askForAnyResourceReplacement() {

    }

    @Override
    public void splashScreen() {

    }

    private void nicknameButtonAction() {
        String nickname = nicknameTextField.getText();
        System.out.println(nickname + "  " + nickname.length());
        if (nickname.length() > 15) {
            nicknameErrorMessage.setText("Maximum 15 characters");
            nicknameErrorMessage.setVisible(true);
        } else if (nickname.length() < 1) {
            nicknameErrorMessage.setText("Please, insert your nickname");
            nicknameErrorMessage.setVisible(true);
        } else {
            client.setNickName(nickname);
            //client.sendMessage(new NicknameMessage(nickname));
            nicknamePane.setVisible(false);
            createJoinGamePane.setVisible(true);

        }
    }
}
