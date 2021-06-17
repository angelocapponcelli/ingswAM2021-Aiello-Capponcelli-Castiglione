package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.GUI.FXGUI;
import it.polimi.ingsw.networking.messages.clientMessages.DiscardedLeaderCardsMessage;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class DiscardLeaderCardController {

    private List<Integer> discardCards = new ArrayList<>();
    public ImageView firstImage;
    public ImageView secondImage;
    public ImageView thirdImage;
    public ImageView fourthImage;

    @FXML
    public void initialize() {
        try {
            firstImage.setImage(new Image(getClass().getResourceAsStream("/image/cards/" + String.valueOf(FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().get(0).getId()) + ".png")));
        } catch (NullPointerException e) {
            System.err.println("Invalid url");
        }
        try {
            secondImage.setImage(new Image(getClass().getResourceAsStream("/image/cards/" + String.valueOf(FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().get(1).getId()) + ".png")));
        } catch (NullPointerException e) {
            System.err.println("Invalid url");
        }
        try {
            thirdImage.setImage(new Image(getClass().getResourceAsStream("/image/cards/" + String.valueOf(FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().get(2).getId()) + ".png")));
        } catch (NullPointerException e) {
            System.err.println("Invalid url");
        }
        try {
            fourthImage.setImage(new Image(getClass().getResourceAsStream("/image/cards/" + String.valueOf(FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().get(3).getId()) + ".png")));
        } catch (NullPointerException e) {
            System.err.println("Invalid url");
        }
       }

    @FXML
    public void discardCard(MouseEvent event) {
        ImageView cardClicked = (ImageView) event.getSource();
        cardClicked.setDisable(true);
        cardClicked.setY(cardClicked.getY() - 20 );
        switch (cardClicked.getId()) {
            case "firstImage":
                discardCards.add(FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().get(0).getId());
                break;
            case "secondImage":
                discardCards.add(FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().get(1).getId());
                break;
            case "thirdImage":
                discardCards.add(FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().get(2).getId());
                break;
            case "fourthImage":
                discardCards.add(FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().get(3).getId());
                break;
        }
        if (discardCards.size() == 2) {
            cardClicked.getParent().setDisable(true);
            Client client = FXGUI.getClient();
            FXGUI.getClient().sendMessage(new DiscardedLeaderCardsMessage(FXGUI.getClient().getNickName(), discardCards.get(0), discardCards.get(1)));
        }
    }

}
