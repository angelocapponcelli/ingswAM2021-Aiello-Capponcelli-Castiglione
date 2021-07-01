package it.polimi.ingsw.editor;

import it.polimi.ingsw.client.view.GUI.FXGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Editor extends Application {

    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Editor.stage = stage;
        scene = new Scene(loadFXML("editor"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Scene getScene() {
        return scene;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FXGUI.class.getResource("/FXML/editor/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main() {
        launch();
    }

    public static Stage getStage() {
        return stage;
    }
}
