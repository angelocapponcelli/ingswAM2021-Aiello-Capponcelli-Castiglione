package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.controller.MY_TURN;
import it.polimi.ingsw.client.view.GUI.FXGUI;
import it.polimi.ingsw.client.view.reducedGameModel.ReducedContainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Objects;

public class AskForMainAction {

    @FXML
    private HBox firstShelfBox;
    @FXML
    private HBox secondShelfBox;
    @FXML
    private HBox thirdShelfBox;
    @FXML
    private Label coinLabel;
    @FXML
    private Label stoneLabel;
    @FXML
    private Label shieldLabel;
    @FXML
    private Label servantLabel;
    @FXML
    private ImageView firstDevelopmentCardImage;
    @FXML
    private ImageView secondDevelopmentCardImage;
    @FXML
    private ImageView thirdDevelopmentCardImage;

    @FXML
    public void initialize() {
        for (int i = 0; i < FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(0).getCount(); i++) {
            ImageView imageView = new ImageView();
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/resources/" + FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(0).getResourceType().toString() + ".png"))));
            imageView.setAccessibleText(FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(0).getResourceType().toString());
            firstShelfBox.getChildren().add(imageView);
        }

        for (int i = 0; i < FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(1).getCount(); i++) {
            ImageView imageView = new ImageView();
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/resources/" + FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(1).getResourceType().toString() + ".png"))));
            imageView.setAccessibleText(FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(1).getResourceType().toString());
            secondShelfBox.getChildren().add(imageView);
        }

        for (int i = 0; i < FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(2).getCount(); i++) {
            ImageView imageView = new ImageView();
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/resources/" + FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(2).getResourceType().toString() + ".png"))));
            imageView.setAccessibleText(FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(2).getResourceType().toString());
            thirdShelfBox.getChildren().add(imageView);
        }
        try {
            firstDevelopmentCardImage.setImage(new Image(getClass().getResourceAsStream("/image/cards/" + String.valueOf(FXGUI.getClient().getView().getReducedGameModel().getPersonalDevelopmentBoard().get(0).getId()) + ".png")));
        } catch (NullPointerException e) {
            System.err.println("Invalid url or card stack empty");
        }
        try {
            secondDevelopmentCardImage.setImage(new Image(getClass().getResourceAsStream("/image/cards/" + String.valueOf(FXGUI.getClient().getView().getReducedGameModel().getPersonalDevelopmentBoard().get(1).getId()) + ".png")));
        } catch (NullPointerException e) {
            System.err.println("Invalid url or card stack empty");
        }
        try {
            thirdDevelopmentCardImage.setImage(new Image(getClass().getResourceAsStream("/image/cards/" + String.valueOf(FXGUI.getClient().getView().getReducedGameModel().getPersonalDevelopmentBoard().get(2).getId()) + ".png")));
        } catch (NullPointerException e) {
            System.err.println("Invalid url or card stack empty");
        }
        for (int i = 0; i < FXGUI.getClient().getView().getReducedGameModel().getStrongBoxDepot().size(); i++) {
            ReducedContainer reducedContainer = FXGUI.getClient().getView().getReducedGameModel().getStrongBoxDepot().get(i);
            switch (reducedContainer.getResourceType()){
                case COIN:
                    coinLabel.setText("x " + reducedContainer.getCount());
                    break;
                case STONE:
                    stoneLabel.setText("x " + reducedContainer.getCount());
                    break;
                case SHIELD:
                    shieldLabel.setText("x " + reducedContainer.getCount());
                    break;
                case SERVANT:
                    servantLabel.setText("x " + reducedContainer.getCount());
                    break;
                default: System.err.println("Invalid resource");
            }
        }
    }

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
