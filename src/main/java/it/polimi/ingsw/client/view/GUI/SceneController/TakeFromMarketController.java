package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.view.GUI.FXGUI;
import it.polimi.ingsw.client.view.reducedGameModel.ReducedMarketTray;
import it.polimi.ingsw.networking.messages.clientMessages.TakeFromMarketMessage;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.utils.CLIColors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.stream.IntStream;

public class TakeFromMarketController {

    public GridPane marketTrayGrid;
    public ImageView slideImage;

    @FXML
    public void initialize() {
        ReducedMarketTray marketTray = FXGUI.getClient().getView().getReducedGameModel().getMarketTray();
        int count = 0;
        for (int i = 0; i < marketTray.getMarketTray().length; i++) {
            for (int j = 0; j < marketTray.getMarketTray()[i].length; j++) {
                ImageView imageView = (ImageView) marketTrayGrid.getChildren().get(count);
                imageView.setImage(new Image(getClass().getResourceAsStream("/image/marketTray/" + marketTray.getMarketTray()[i][j].toString() + ".png")));
                count++;
            }
        }
        slideImage.setImage(new Image(getClass().getResourceAsStream("/image/marketTray/" + FXGUI.getClient().getView().getReducedGameModel().getMarketTray().getSlide().toString() + ".png")));
    }

    public void onColumnRowSelected(ActionEvent actionEvent) {
        Button buttonClicked = (Button) actionEvent.getSource();
        String rowOrColumn;
        int number;
        switch (buttonClicked.getId()) {
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
