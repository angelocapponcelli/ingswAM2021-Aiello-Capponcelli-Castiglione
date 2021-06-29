package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.view.GUI.FXGUI;
import it.polimi.ingsw.networking.messages.clientMessages.DiscardResourceMessage;
import it.polimi.ingsw.networking.messages.clientMessages.ReallocateResourceMessage;
import it.polimi.ingsw.server.model.resources.ResourceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.Objects;
import java.util.stream.IntStream;

public class MoveFromTemporaryController {

    public HBox firstShelfBox;
    public HBox secondShelfBox;
    public HBox thirdShelfBox;
    public HBox temporaryDepotResourceHbox;
    public BorderPane resourceToMovePane;

    @FXML
    public void initialize() {
        FXGUI.getClient().getView().getReducedGameModel().getTemporaryDepot().forEach((key, value) -> IntStream.range(0, value)
                .mapToObj(x -> key).filter(resourceType -> !resourceType.equals(ResourceType.ANY))
                .forEach(resourceType -> {
                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                    imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/resources/" + resourceType + ".png"))));
                    imageView.setAccessibleText(resourceType.toString());
                    temporaryDepotResourceHbox.getChildren().add(imageView);
                }));
        if (temporaryDepotResourceHbox.getChildren().size() > 0) {
            resourceToMovePane.setCenter(temporaryDepotResourceHbox.getChildren().get(0));
            ImageView imageView = (ImageView) resourceToMovePane.getCenter();
            imageView.setFitWidth(78);
            imageView.setFitHeight(78);
        }

        for (int i = 0; i < FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(0).getCount(); i++) {
            ImageView imageView = new ImageView();
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/resources/" + FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(0).getResourceType().toString() + ".png"))));
            imageView.setAccessibleText(FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(0).getResourceType().toString());
            firstShelfBox.getChildren().add(imageView);
        }

        for (int i = 0; i < FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(1).getCount(); i++) {
            ImageView imageView = new ImageView();
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/resources/" + FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(1).getResourceType().toString() + ".png"))));
            imageView.setAccessibleText(FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(1).getResourceType().toString());
            secondShelfBox.getChildren().add(imageView);
        }

        for (int i = 0; i < FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(2).getCount(); i++) {
            ImageView imageView = new ImageView();
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/resources/" + FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(2).getResourceType().toString() + ".png"))));
            imageView.setAccessibleText(FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(2).getResourceType().toString());
            thirdShelfBox.getChildren().add(imageView);
        }
    }

    public void onShelfClicked(ActionEvent event) {
        Button shelfClicked = (Button) event.getSource();
        int shelfNumber;
        ImageView imageView = (ImageView) resourceToMovePane.getCenter();
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        switch (shelfClicked.getId()) {
            case "firstShelfButton":
                shelfNumber = 0;
                firstShelfBox.getChildren().add(imageView);
                break;
            case "secondShelfButton":
                shelfNumber = 1;
                secondShelfBox.getChildren().add(imageView);
                break;
            case "thirdShelfButton":
                shelfNumber = 2;
                thirdShelfBox.getChildren().add(imageView);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + shelfClicked.getId());
        }
        ResourceType resourceType = ResourceType.valueOf(imageView.getAccessibleText());

        FXGUI.getClient().sendMessage(new ReallocateResourceMessage(FXGUI.getClient().getNickName(), resourceType, "Temporary", "WareHouse", -1, shelfNumber));

        if (temporaryDepotResourceHbox.getChildren().size() > 0) {
            resourceToMovePane.setCenter(temporaryDepotResourceHbox.getChildren().get(0));
            imageView = (ImageView) resourceToMovePane.getCenter();
            imageView.setFitWidth(78);
            imageView.setFitHeight(78);
        }
    }

    public void onDiscardClicked(ActionEvent event) {
        ImageView imageView = (ImageView) resourceToMovePane.getCenter();
        ResourceType key = ResourceType.valueOf(imageView.getAccessibleText());
        FXGUI.getClient().sendMessage(new DiscardResourceMessage(FXGUI.getClient().getNickName(), key));

        if (temporaryDepotResourceHbox.getChildren().size() > 0) {
            resourceToMovePane.setCenter(temporaryDepotResourceHbox.getChildren().get(0));
            imageView = (ImageView) resourceToMovePane.getCenter();
            imageView.setFitWidth(78);
            imageView.setFitHeight(78);
        }
    }
}
