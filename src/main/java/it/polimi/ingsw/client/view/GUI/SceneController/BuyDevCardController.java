package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.view.GUI.FXGUI;
import it.polimi.ingsw.networking.messages.clientMessages.BuyDevCardMessage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BuyDevCardController {

    public GridPane gridCard;

    public void onCardSelected(ActionEvent event) {
        Button buttonClicked = (Button) event.getSource();
        int id = FXGUI.getClient().getView().getReducedGameModel().getDevelopmentCardsGrid()[gridCard.getRowIndex(buttonClicked)][gridCard.getColumnIndex(buttonClicked)].getId();
        FXGUI.getClient().sendMessage(new BuyDevCardMessage(FXGUI.getClient().getNickName(), id));
    }
}
