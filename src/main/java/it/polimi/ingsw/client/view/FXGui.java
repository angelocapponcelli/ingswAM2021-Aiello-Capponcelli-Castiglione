package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;

public class FXGui extends Application {

    private static Scene mainScene;
    private static GUI gui = new GUI(new Client(true));
    private static Stage stage;

    @Override
    public void start(Stage s) {
        stage = s;
        Parent login = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("FXML/Login.fxml"));
            loader.setController(gui);
            login = loader.load();
            //root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainScene = new Scene(login);
        stage.setTitle("Maestri del rinacimento");
        //stage.setWidth(1280d);
        //stage.setHeight(720d);
        stage.setResizable(false);
        //stage.setMaximized(true);
        //stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setScene(mainScene);
        stage.show();

        gui.askForNickName();
    }

    public static void ChangePane(String sourceFile) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(gui);
            loader.setLocation(FXGui.class.getClassLoader().getResource(sourceFile));
            Parent root = loader.load();
            mainScene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Stage getStage (){
        return stage;
    }

}
