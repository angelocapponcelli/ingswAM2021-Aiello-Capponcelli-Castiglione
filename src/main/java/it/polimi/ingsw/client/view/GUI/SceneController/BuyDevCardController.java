package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.view.GUI.FXGUI;
import it.polimi.ingsw.client.view.reducedGameModel.ReducedDevelopmentCard;
import it.polimi.ingsw.networking.messages.clientMessages.BuyDevCardMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class BuyDevCardController {

    @FXML
    private GridPane gridCard;

    @FXML
    public void initialize() {
        ReducedDevelopmentCard[][] developmentCardsGrid = FXGUI.getClient().getView().getReducedGameModel().getDevelopmentCardsGrid();
        int count = 0;
        for (int i = 0; i < developmentCardsGrid.length; i++) {
            for (int j = 0; j < developmentCardsGrid[i].length; j++) {
                BorderPane borderPane = (BorderPane) gridCard.getChildren().get(count);
                ImageView imageView = (ImageView) borderPane.getCenter();
                try {
                    imageView.setImage(new Image(getClass().getResourceAsStream("/image/cards/" + developmentCardsGrid[i][j].getId() + ".png")));
                } catch (NullPointerException e) {
                    System.err.println("Invalid url or card stack empty: " + developmentCardsGrid[i][j].getId());
                }
                count++;
            }
        }
    }

    public void onCardSelected(MouseEvent event) {
        ImageView cardClicked = (ImageView) event.getSource();
        int id = FXGUI.getClient().getView().getReducedGameModel().getDevelopmentCardsGrid()[gridCard.getRowIndex(cardClicked.getParent())][gridCard.getColumnIndex(cardClicked.getParent())].getId();
        FXGUI.getClient().sendMessage(new BuyDevCardMessage(FXGUI.getClient().getNickName(), id));
    }
}
