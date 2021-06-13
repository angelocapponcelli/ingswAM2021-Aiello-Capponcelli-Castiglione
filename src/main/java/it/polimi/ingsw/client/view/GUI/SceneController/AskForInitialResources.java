package it.polimi.ingsw.client.view.GUI.SceneController;


import it.polimi.ingsw.client.view.GUI.FXGUI;
import it.polimi.ingsw.networking.messages.clientMessages.ChosenInitialResourcesMessage;
import it.polimi.ingsw.server.model.resources.ResourceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class AskForInitialResources {

    private List<ResourceType> initialResources = new ArrayList<>();
    private int numOfResources = 4;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private HBox container;

    @FXML
    public void initialize() {
        switch (FXGUI.getClient().getView().getReducedGameModel().getPlayerTurnPosition()) {
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
    }

    public void initialResourcesEvent(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btn1":
                initialResources.add(ResourceType.COIN);
                container.getChildren().add(new Label("COIN"));
                break;
            case "btn2":
                initialResources.add(ResourceType.SHIELD);
                container.getChildren().add(new Label("SHIELD"));
                break;
            case "btn3":
                initialResources.add(ResourceType.STONE);
                container.getChildren().add(new Label("STONE"));
                break;
            case "btn4":
                initialResources.add(ResourceType.SERVANT);
                container.getChildren().add(new Label("SERVANT"));
                break;
        }
        numOfResources--;
        if (numOfResources == 0) {
            btn.getParent().setDisable(true);
            FXGUI.getClient().sendMessage(new ChosenInitialResourcesMessage(FXGUI.getClient().getNickName(), initialResources));
        }

    }
}