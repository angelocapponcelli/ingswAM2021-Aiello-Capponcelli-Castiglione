package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.view.GUI.FXGUI;
import it.polimi.ingsw.networking.messages.clientMessages.ActivateLeaderCardMessage;
import it.polimi.ingsw.networking.messages.clientMessages.ReallocateResourceMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ActivateLeaderController {
    @FXML
    private HBox cardsPane;

    @FXML
    public void initialize() {
        FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().forEach(leaderCard -> {
            if (!leaderCard.getPlayed()) {
                try {
                    ImageView card = new ImageView(new Image(getClass().getResourceAsStream("/image/cards/" + leaderCard.getId() + ".png")));
                    card.setFitWidth(146);
                    card.setFitHeight(222);
                    card.setCursor(Cursor.HAND);
                    card.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> onCardClicked(leaderCard.getId()));
                    cardsPane.getChildren().add(card);
                } catch (NullPointerException e) {
                    System.err.println("Invalid url");
                }
            }
        });
    }

    private void onCardClicked(int id) {
        new FXGUI().getClient().sendMessage(new ActivateLeaderCardMessage(FXGUI.getClient().getNickName(), id));
        FXGUI.getClient().getClientController().update();
    }

    public void onSkip(ActionEvent event) {
        FXGUI.getClient().getClientController().update();
    }
}
