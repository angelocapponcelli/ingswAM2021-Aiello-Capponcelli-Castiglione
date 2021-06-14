package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.server.model.resources.ResourceType;
import javafx.event.ActionEvent;

import java.util.HashMap;
import java.util.Map;

public class ActivateBaseProductionController {

    private Map<ResourceType, Integer> input = new HashMap<>();
    private Map<ResourceType, Integer> output = new HashMap<>();

    public void onResourceClick(ActionEvent event) {
        if (input.size() == 0) {
            //todo
        }
    }
}
