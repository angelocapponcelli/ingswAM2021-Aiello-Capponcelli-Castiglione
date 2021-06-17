package it.polimi.ingsw.client.view.GUI;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.View;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GUI extends View {


    private Stage stage;
    private Scene scene;
    private Parent root;


    public GUI(Client client) {
        super(client);
    }

    @Override
    public void splashScreen() {

    }

    @Override
    public void askForNickName() {
        /*Platform.runLater(()-> {
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/nickName.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = FXGUI.getStage();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });*/
    }

    @Override
    public void askForCreateOrJoinGame() {
        Platform.runLater(()-> {
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/createOrJoin.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = FXGUI.getStage();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });
    }

    @Override
    public void askForLeaderCardsToDiscard() {
        Platform.runLater(()-> {
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/discardLeaderCard.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = FXGUI.getStage();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });
    }

    @Override
    public boolean askForInitialResources() {
        int numOfResources;
        switch (reducedGameModel.getPlayerTurnPosition()) {
            case 2:
            case 3:
                numOfResources = 1;
                break;
            case 4:
                numOfResources = 2;
                break;
            default:
                numOfResources = 0;
                break;
        }
        if (numOfResources > 0) {
            Platform.runLater(()-> {
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/askForInitialResources.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage = FXGUI.getStage();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            });
            return true;
        }

        return false;
    }


    @Override
    public void moveFromTemporary() {
        Platform.runLater(()-> {
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/moveFromTemporary.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = FXGUI.getStage();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });
    }

    @Override
    public void askForAnyResourceReplacement() {

    }

    @Override
    public void refresh() {
        Platform.runLater(()-> {
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/gameBoard.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = FXGUI.getStage();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });
    }

    @Override
    public void drawMarketTray() {

    }

    @Override
    public void drawInHandLeaderCards() {

    }

    @Override
    public void drawTemporaryDepot() {

    }

    @Override
    public void drawWareHouse() {

    }

    @Override
    public void drawDevCardGrid() {

    }

    @Override
    public void drawPersonalDevelopmentBoard() {

    }

    @Override
    public void drawStrongBox() {

    }

    @Override
    public void drawFaithTrack() {

    }

    @Override
    public void askForMainAction() {
        Platform.runLater(()-> {
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/askForMainAction.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = FXGUI.getStage();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });
        client.getClientController().pause();
    }

    @Override
    public void takeFromMarket() {
        Platform.runLater(()-> {
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/takeFromMarket.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = FXGUI.getStage();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });
    }

    @Override
    public void buyDevCard() {
        Platform.runLater(()-> {
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/buyDevCard.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = FXGUI.getStage();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });
    }

    @Override
    public void activateProduction() {
        Platform.runLater(()-> {
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/activateProduction.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = FXGUI.getStage();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });
    }

    @Override
    public void faithTrackDraw() {

    }
}
