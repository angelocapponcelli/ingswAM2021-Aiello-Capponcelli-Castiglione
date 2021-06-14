package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.view.GUI.FXGUI;
import it.polimi.ingsw.networking.messages.clientMessages.TakeFromMarketMessage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class TakeFromMarketController {

    public void onColumnRowSelected (ActionEvent actionEvent){
        Button buttonClicked = (Button) actionEvent.getSource();
        String rowOrColumn;
        int number;
        switch (buttonClicked.getId()){
            case "firstColButton":
                rowOrColumn = "column";
                number = 0;
                break;
            case "secondColButton":
                rowOrColumn = "column";
                number = 1;
                break;
            case "thirdColButton":
                rowOrColumn = "column";
                number = 2;
                break;
            case "fourthColButton":
                rowOrColumn = "column";
                number = 3;
                break;
            case "firstRowButton":
                rowOrColumn = "row";
                number = 0;
                break;
            case "secondRowButton":
                rowOrColumn = "row";
                number = 1;
                break;
            case "thirdRowButton":
                rowOrColumn = "row";
                number = 2;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + buttonClicked.getId());
        }
        FXGUI.getClient().sendMessage(new TakeFromMarketMessage(FXGUI.getClient().getNickName(), rowOrColumn, number));
    }
}
