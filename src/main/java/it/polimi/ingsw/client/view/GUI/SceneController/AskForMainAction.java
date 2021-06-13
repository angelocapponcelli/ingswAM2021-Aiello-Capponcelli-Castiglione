package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.controller.MY_TURN;
import it.polimi.ingsw.client.view.GUI.FXGUI;
import javafx.event.ActionEvent;

public class AskForMainAction {

    public void onTakeFromMarket(ActionEvent actionEvent) {
        FXGUI.getClient().getClientController().setMyTurnState(MY_TURN.TAKE_FROM_MARKET);
        FXGUI.getClient().getClientController().update();
    }

    public void onActivateProduction(ActionEvent actionEvent) {
        FXGUI.getClient().getClientController().setMyTurnState(MY_TURN.ACTIVATE_PRODUCTION);
        FXGUI.getClient().getClientController().update();
    }

    public void onBuyDevCard(ActionEvent actionEvent) {
        FXGUI.getClient().getClientController().setMyTurnState(MY_TURN.BUY_DEV_CARD);
        FXGUI.getClient().getClientController().update();
    }
}
