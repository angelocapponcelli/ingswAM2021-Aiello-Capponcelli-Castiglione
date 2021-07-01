package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.view.GUI.FXGUI;
import it.polimi.ingsw.client.view.reducedGameModel.ReducedContainer;
import it.polimi.ingsw.client.view.reducedGameModel.SpecialAbilityType;
import it.polimi.ingsw.server.model.resources.ResourceType;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.beans.EventHandler;
import java.io.IOException;
import java.util.Objects;

public class ActivateProductionController {

    @FXML
    private VBox leaderCardPowerBox;
    @FXML
    private FlowPane specialPane;
    @FXML
    private CheckBox baseProduction;
    @FXML
    private CheckBox firstDevelopmentCardCheckBox;
    @FXML
    private CheckBox secondDevelopmentCardCheckBox;
    @FXML
    private CheckBox thirdDevelopmentCardCheckBox;
    @FXML
    private ImageView firstDevelopmentCardImage;
    @FXML
    private ImageView secondDevelopmentCardImage;
    @FXML
    private ImageView thirdDevelopmentCardImage;
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

    private Stage stage;
    private Scene scene;
    private Parent root;

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
            firstDevelopmentCardCheckBox.setDisable(true);
            //System.err.println("Invalid url or card stack empty");
        }
        try {
            secondDevelopmentCardImage.setImage(new Image(getClass().getResourceAsStream("/image/cards/" + String.valueOf(FXGUI.getClient().getView().getReducedGameModel().getPersonalDevelopmentBoard().get(1).getId()) + ".png")));
        } catch (NullPointerException e) {
            secondDevelopmentCardCheckBox.setDisable(true);
            //System.err.println("Invalid url or card stack empty");
        }
        try {
            thirdDevelopmentCardImage.setImage(new Image(getClass().getResourceAsStream("/image/cards/" + String.valueOf(FXGUI.getClient().getView().getReducedGameModel().getPersonalDevelopmentBoard().get(2).getId()) + ".png")));
        } catch (NullPointerException e) {
            thirdDevelopmentCardCheckBox.setDisable(true);
            //System.err.println("Invalid url or card stack empty");
        }
        for (int i = 0; i < FXGUI.getClient().getView().getReducedGameModel().getStrongBoxDepot().size(); i++) {
            ReducedContainer reducedContainer = FXGUI.getClient().getView().getReducedGameModel().getStrongBoxDepot().get(i);
            switch (reducedContainer.getResourceType()) {
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
                default:
                    System.err.println("Invalid resource");
            }
        }

        FXGUI.getClient().getView().getReducedGameModel().getSpecialDepot().forEach(reducedContainer -> {
            AnchorPane anchorPane = new AnchorPane();
            ImageView imageViewBackGround = new ImageView(new Image(getClass().getResourceAsStream("/image/special/special" + reducedContainer.getResourceType().toString() + ".png")));
            imageViewBackGround.setFitHeight(50);
            imageViewBackGround.setFitWidth(105);
            anchorPane.getChildren().add(imageViewBackGround);
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setPrefHeight(50);
            hBox.setPrefWidth(105);
            hBox.setSpacing(18);
            hBox.setPadding(new Insets(0, 0, 0, 8));
            for (int i = 0; i < reducedContainer.getCount(); i++) {
                ImageView imageResourceSpecial = new ImageView(new Image(getClass().getResourceAsStream("/image/resources/" + reducedContainer.getResourceType().toString() + ".png")));
                imageResourceSpecial.setFitWidth(35);
                imageResourceSpecial.setFitHeight(35);
                hBox.getChildren().add(imageResourceSpecial);
            }
            anchorPane.getChildren().add(hBox);
            specialPane.getChildren().add(anchorPane);
        });

        FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().stream()
                .filter(reducedLeaderCard -> (reducedLeaderCard.getPlayed() == true && reducedLeaderCard.getSpecialAbility() == SpecialAbilityType.PRODUCTION_POWER))
                .forEach(reducedLeaderCard -> {
                    HBox rowLeaderPower = new HBox();
                    rowLeaderPower.setPrefWidth(530);
                    rowLeaderPower.setPrefHeight(50);
                    rowLeaderPower.setSpacing(20);
                    ImageView powerImage = new ImageView(new Image(getClass().getResourceAsStream("/image/leaderCardPower/" + reducedLeaderCard.getSpecialResourceType().toString() + "Power.png")));
                    powerImage.setFitWidth(170);
                    powerImage.setFitHeight(60);
                    powerImage.setCursor(Cursor.HAND);
                    rowLeaderPower.getChildren().add(powerImage);
                    powerImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> onLeaderPowerClicked(rowLeaderPower, reducedLeaderCard.getSpecialResourceType()));
                    leaderCardPowerBox.getChildren().add(rowLeaderPower);
                });

    }

    private void onLeaderPowerClicked(HBox rowLeaderPower, ResourceType specialResourceType) {
        rowLeaderPower.getChildren().get(0).setDisable(true);
        ImageView resourcePowerImage = new ImageView(new Image(getClass().getResourceAsStream("/image/resources/COIN.png")));
        resourcePowerImage.setFitWidth(60);
        resourcePowerImage.setFitHeight(60);
        resourcePowerImage.setCursor(Cursor.HAND);
        rowLeaderPower.getChildren().add(resourcePowerImage);
        resourcePowerImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> onResourceLeaderPowerClicked(rowLeaderPower, specialResourceType, ResourceType.valueOf("COIN")));

        resourcePowerImage = new ImageView(new Image(getClass().getResourceAsStream("/image/resources/SERVANT.png")));
        resourcePowerImage.setFitWidth(60);
        resourcePowerImage.setFitHeight(60);
        resourcePowerImage.setCursor(Cursor.HAND);
        rowLeaderPower.getChildren().add(resourcePowerImage);
        resourcePowerImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> onResourceLeaderPowerClicked(rowLeaderPower, specialResourceType, ResourceType.valueOf("SERVANT")));

        resourcePowerImage = new ImageView(new Image(getClass().getResourceAsStream("/image/resources/STONE.png")));
        resourcePowerImage.setFitWidth(60);
        resourcePowerImage.setFitHeight(60);
        resourcePowerImage.setCursor(Cursor.HAND);
        rowLeaderPower.getChildren().add(resourcePowerImage);
        resourcePowerImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> onResourceLeaderPowerClicked(rowLeaderPower, specialResourceType, ResourceType.valueOf("STONE")));

        resourcePowerImage = new ImageView(new Image(getClass().getResourceAsStream("/image/resources/SHIELD.png")));
        resourcePowerImage.setFitWidth(60);
        resourcePowerImage.setFitHeight(60);
        resourcePowerImage.setCursor(Cursor.HAND);
        rowLeaderPower.getChildren().add(resourcePowerImage);
        resourcePowerImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> onResourceLeaderPowerClicked(rowLeaderPower, specialResourceType, ResourceType.valueOf("SHIELD")));

    }

    private void onResourceLeaderPowerClicked(HBox rowLeaderPower, ResourceType specialResourceType, ResourceType resourceChoosen) {
        //sendMessage( input specialResourceType, output faith+resourceChoosen ) //TODO
        rowLeaderPower.getChildren().remove(1, rowLeaderPower.getChildren().size());
        ImageView resourcePowerImage = new ImageView(new Image(getClass().getResourceAsStream("/image/resources/" + resourceChoosen.toString() + ".png")));
        resourcePowerImage.setFitWidth(60);
        resourcePowerImage.setFitHeight(60);
        rowLeaderPower.getChildren().add(resourcePowerImage);
        rowLeaderPower.setDisable(true);
    }

    public void onConfirmProduction(ActionEvent event) {
        if (firstDevelopmentCardCheckBox.isSelected()) ;//todo activate first card production
        if (secondDevelopmentCardCheckBox.isSelected()) ;//todo activate second card production
        if (thirdDevelopmentCardCheckBox.isSelected()) ;//todo activate third card production
        if (baseProduction.isSelected()) {
            Platform.runLater(() -> {
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/activateBaseProduction.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage = FXGUI.getStage();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            });
        }
    }
}
