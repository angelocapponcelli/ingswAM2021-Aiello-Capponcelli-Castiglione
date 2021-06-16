package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.view.GUI.FXGUI;
import it.polimi.ingsw.networking.messages.clientMessages.DiscardResourceMessage;
import it.polimi.ingsw.networking.messages.clientMessages.ReallocateResourceMessage;
import it.polimi.ingsw.server.model.resources.ResourceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.stream.IntStream;

public class MoveFromTemporaryController {

    public VBox temporaryDepotResourceVbox;
    public VBox resourceToMoveVbox;

    @FXML
    public void initialize() {
        FXGUI.getClient().getView().getReducedGameModel().getTemporaryDepot().forEach((key, value) -> IntStream.range(0, value)
                .mapToObj(x -> key).filter(resourceType -> !resourceType.equals(ResourceType.ANY))
                .forEach(resourceType -> {
                    temporaryDepotResourceVbox.getChildren().add(new Label(resourceType.toString()));
                }));
        if (temporaryDepotResourceVbox.getChildren().size() > 0)
            resourceToMoveVbox.getChildren().add(temporaryDepotResourceVbox.getChildren().get(0));
    }

    public void onShelfClicked(ActionEvent event) {
        Button shelfClicked = (Button) event.getSource();
        int shelfNumber;
        switch (shelfClicked.getId()) {
            case "firstShelfButton":
                shelfNumber = 0;
                break;
            case "secondShelfButton":
                shelfNumber = 1;
                break;
            case "thirdShelfButton":
                shelfNumber = 2;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + shelfClicked.getId());
        }
        Label toMove = (Label) resourceToMoveVbox.getChildren().get(0);
        ResourceType resourceType = ResourceType.valueOf(toMove.getText());

        FXGUI.getClient().sendMessage(new ReallocateResourceMessage(FXGUI.getClient().getNickName(), resourceType, "Temporary", "WareHouse", -1, shelfNumber));

        resourceToMoveVbox.getChildren().remove(0);
        if (temporaryDepotResourceVbox.getChildren().size() > 0)
            resourceToMoveVbox.getChildren().add(temporaryDepotResourceVbox.getChildren().get(0));
    }

    public void onDiscardClicked(ActionEvent event) {
        Label toMove = (Label) resourceToMoveVbox.getChildren().get(0);
        ResourceType key = ResourceType.valueOf(toMove.getText());
        FXGUI.getClient().sendMessage(new DiscardResourceMessage(FXGUI.getClient().getNickName(), key));

        resourceToMoveVbox.getChildren().remove(0);
        if (temporaryDepotResourceVbox.getChildren().size() > 0)
            resourceToMoveVbox.getChildren().add(temporaryDepotResourceVbox.getChildren().get(0));
    }
}
