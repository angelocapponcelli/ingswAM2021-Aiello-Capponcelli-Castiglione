package it.polimi.ingsw.client.view.GUI;

import it.polimi.ingsw.client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXGui extends Application {
    private static Scene scene;
    private static Client client;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("nickName"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FXGui.class.getResource("/FXML/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void setClient(Client client) {
        FXGui.client = client;
    }

    public static Client getClient() {
        return client;
    }
}
