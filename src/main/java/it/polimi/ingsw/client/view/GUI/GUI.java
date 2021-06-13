package it.polimi.ingsw.client.view.GUI;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.controller.MY_TURN;
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

    }

    @Override
    public boolean askForInitialResources() {
        return false;
    }

    @Override
    public void moveFromTemporary() {

    }

    @Override
    public void askForAnyResourceReplacement() {

    }

    @Override
    public void refresh() {

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
    public MY_TURN askForMainAction() {
        return null;
    }

    @Override
    public void takeFromMarket() {

    }

    @Override
    public void buyDevCard() {

    }

    @Override
    public void activateProduction() {

    }

    @Override
    public void faithTrackDraw() {

    }
}
