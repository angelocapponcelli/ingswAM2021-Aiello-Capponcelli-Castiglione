package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.GUI.FXGUI;
import it.polimi.ingsw.networking.messages.clientMessages.DiscardedLeaderCardsMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DiscardLeaderCard {

    private List<Integer> discardCards = new ArrayList<>();
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;

    @FXML
    public void initialize() {
        btn1.setText(String.valueOf(FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().get(0).getId()));
        btn2.setText(String.valueOf(FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().get(1).getId()));
        btn3.setText(String.valueOf(FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().get(2).getId()));
        btn4.setText(String.valueOf(FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().get(3).getId()));
    }

    @FXML
    public void discardCard(ActionEvent event){
        Button btn = (Button) event.getSource();
        btn.setDisable(true);
        switch (btn.getId()) {
            case "btn1":
                discardCards.add(FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().get(0).getId());
                break;
            case "btn2":
                discardCards.add(FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().get(1).getId());
                break;
            case "btn3":
                discardCards.add(FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().get(2).getId());
                break;
            case "btn4":
                discardCards.add(FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().get(3).getId());
                break;
        }
        if (discardCards.size() == 2) {
            btn.getParent().setDisable(true);
            Client client = FXGUI.getClient();
            FXGUI.getClient().sendMessage(new DiscardedLeaderCardsMessage(FXGUI.getClient().getNickName(), discardCards.get(0), discardCards.get(1)));
        }
    }

}
