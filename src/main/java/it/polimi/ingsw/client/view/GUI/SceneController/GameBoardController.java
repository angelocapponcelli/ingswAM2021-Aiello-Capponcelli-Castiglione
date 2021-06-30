package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.view.GUI.FXGUI;
import it.polimi.ingsw.client.view.reducedGameModel.ReducedContainer;
import it.polimi.ingsw.client.view.reducedGameModel.ReducedDevelopmentCard;
import it.polimi.ingsw.client.view.reducedGameModel.ReducedMarketTray;
import it.polimi.ingsw.client.view.reducedGameModel.ReducedPlayer;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.List;
import java.util.Objects;

public class GameBoardController {

    @FXML
    private AnchorPane faithTrackPane;
    @FXML
    private GridPane gridCard;
    @FXML
    private GridPane marketTrayGrid;
    @FXML
    private ImageView slideImage;
    @FXML
    private FlowPane specialPane;
    @FXML
    private FlowPane inHandLeaderCardPane;
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
            // System.err.println("Invalid url or card stack empty");
        }
        try {
            secondDevelopmentCardImage.setImage(new Image(getClass().getResourceAsStream("/image/cards/" + String.valueOf(FXGUI.getClient().getView().getReducedGameModel().getPersonalDevelopmentBoard().get(1).getId()) + ".png")));
        } catch (NullPointerException e) {
            // System.err.println("Invalid url or card stack empty");
        }
        try {
            thirdDevelopmentCardImage.setImage(new Image(getClass().getResourceAsStream("/image/cards/" + String.valueOf(FXGUI.getClient().getView().getReducedGameModel().getPersonalDevelopmentBoard().get(2).getId()) + ".png")));
        } catch (NullPointerException e) {
            // System.err.println("Invalid url or card stack empty");
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

        for (int i = 0; i < FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().size(); i++) {
            if (FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().get(i).getPlayed())
            {
                ImageView leaderCard = new ImageView(new Image(getClass().getResourceAsStream("/image/cards/"+ FXGUI.getClient().getView().getReducedGameModel().getReducedInHandLeaderCards().getInHandLeaderCards().get(i).getId() +".png")));
                leaderCard.setFitWidth(132);
                leaderCard.setFitHeight(200);
                inHandLeaderCardPane.getChildren().add(leaderCard);
            }
        }

        FXGUI.getClient().getView().getReducedGameModel().getSpecialDepot().forEach( reducedContainer -> {
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

        ReducedDevelopmentCard[][] developmentCardsGrid = FXGUI.getClient().getView().getReducedGameModel().getDevelopmentCardsGrid();
        count = 0;
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

        List<ReducedPlayer> players = FXGUI.getClient().getView().getReducedGameModel().getPlayers();
        for (int i = 0; i < players.size(); i++) {
            FlowPane cellPain = (FlowPane) faithTrackPane.getChildren().get( players.get(i).getFaithPosition() );
            ImageView playerImage = new ImageView(new Image(getClass().getResourceAsStream("/image/players/player" + i + ".png")));
            playerImage.setFitHeight(35);
            playerImage.setFitWidth(27);
            cellPain.getChildren().add(playerImage);
        }


    }

}
