package it.polimi.ingsw.editor;

import it.polimi.ingsw.client.view.GUI.FXGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * the editor application
 */
public class Editor extends Application {

    private static Scene scene;
    private static Stage stage;

    /**
     * Starts to show the scene
     * @param stage the place where the scene is shown
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Editor.stage = stage;
        scene = new Scene(loadFXML("editor"));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the root
     * @param fxml fxml name of the file
     * @throws IOException
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     *
     * @return the scene
     */
    public static Scene getScene() {
        return scene;
    }

    /**
     * loads the file
     * @param fxml the file to load
     * @return the loaded file
     * @throws IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FXGUI.class.getResource("/FXML/editor/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main() {
        launch();
    }

    /**
     *
     * @return the stage
     */
    public static Stage getStage() {
        return stage;
    }
}
