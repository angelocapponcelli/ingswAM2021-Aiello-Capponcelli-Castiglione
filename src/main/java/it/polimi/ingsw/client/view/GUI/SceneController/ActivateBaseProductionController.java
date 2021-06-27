package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.view.GUI.FXGUI;
import it.polimi.ingsw.networking.messages.clientMessages.ActivateBasicProductionMessage;
import it.polimi.ingsw.server.model.resources.ResourceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.Map;

public class ActivateBaseProductionController {

    public Label inputLabel;
    public Label outputLabel;
    private Map<ResourceType, Integer> input = new HashMap<>();
    private Map<ResourceType, Integer> output = new HashMap<>();
    private int inputCount;
    private int outputCount;

    @FXML
    public void initialize() {
        inputCount = FXGUI.getClient().getView().getReducedGameModel().getProductionPowerInputBoard().get(ResourceType.ANY);
        outputCount = FXGUI.getClient().getView().getReducedGameModel().getProductionPowerOutputBoard().get(ResourceType.ANY);
    }

    public void onResourceClick(ActionEvent event) {
        ResourceType resourceReplacement;
        Button buttonClicked = (Button) event.getSource();
        switch (buttonClicked.getId()){
            case "coinButton":
                resourceReplacement = ResourceType.COIN;
                break;
            case "stoneButton":
                resourceReplacement = ResourceType.STONE;
                break;
            case "shieldButton":
                resourceReplacement = ResourceType.SHIELD;
                break;
            case "servantButton":
                resourceReplacement = ResourceType.SERVANT;
                break;
            default:
                throw new IllegalStateException("Unexpected value");
        }

        if (input.size() < inputCount) {
            if (input.containsKey(resourceReplacement)){
                input.put(resourceReplacement, input.get(resourceReplacement)+1);
            } else input.put(resourceReplacement,1);
            inputLabel.setText(inputLabel.getText() + " " + resourceReplacement);
        } else {
            if (output.containsKey(resourceReplacement)){
                output.put(resourceReplacement, output.get(resourceReplacement)+1);
            } else output.put(resourceReplacement,1);
            outputLabel.setText(outputLabel.getText() + " " + resourceReplacement);
        }
        if (outputCount == output.size()) FXGUI.getClient().sendMessage(new ActivateBasicProductionMessage(FXGUI.getClient().getNickName(),input,output));
    }
}
