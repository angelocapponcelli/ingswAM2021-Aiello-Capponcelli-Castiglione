package it.polimi.ingsw.client.view.GUI;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.controller.ClientController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.beans.EventHandler;
import java.io.IOException;

public class FXGUI extends Application {

    private static Client client;
    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {

        Platform.setImplicitExit(true);
        stage.setOnCloseRequest((ae) -> {
            Platform.exit();
            System.exit(0);
        });

        FXGUI.stage = stage;
        scene = new Scene(loadFXML("nickName"));
        stage.setScene(scene);
        stage.setTitle("Masters of Renaissance");
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Scene getScene() {
        return scene;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FXGUI.class.getResource("/FXML/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(Client client) {
        FXGUI.client = client;
        launch();
    }

    public static Client getClient(){
        return client;
    }

    public static ClientController getClientController() {
        return client.getClientController();
    }

    public static Stage getStage() {
        return stage;
    }


}