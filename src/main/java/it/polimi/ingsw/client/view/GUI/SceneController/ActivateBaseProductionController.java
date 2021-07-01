package it.polimi.ingsw.client.view.GUI.SceneController;

import it.polimi.ingsw.client.view.GUI.FXGUI;
import it.polimi.ingsw.networking.messages.clientMessages.ActivateBasicProductionMessage;
import it.polimi.ingsw.server.model.resources.ResourceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ActivateBaseProductionController {

    public Label inputLabel;
    public Label outputLabel;
    @FXML
    private Label dialogLabel;
    @FXML
    private VBox outputVBox;
    @FXML
    private VBox inputVBox;
    @FXML
    private HBox firstShelfBox;
    @FXML
    private HBox secondShelfBox;
    @FXML
    private HBox thirdShelfBox;
    private Map<ResourceType, Integer> input = new HashMap<>();
    private Map<ResourceType, Integer> output = new HashMap<>();
    private int inputCount;
    private int outputCount;
    private int inputClicked;
    private int outputClicked;

    @FXML
    public void initialize() {
        inputCount = FXGUI.getClient().getView().getReducedGameModel().getProductionPowerInputBoard().get(ResourceType.ANY);
        outputCount = FXGUI.getClient().getView().getReducedGameModel().getProductionPowerOutputBoard().get(ResourceType.ANY);
        inputClicked = 0;
        outputClicked = 0;
        outputVBox.setVisible(false);
        dialogLabel.setText("Select a resource from your personal depots to trasform it");

        for (int i = 0; i < FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(0).getCount(); i++) {
            ImageView imageView = new ImageView();
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/resources/" + FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(0).getResourceType().toString() + ".png"))));
            imageView.setAccessibleText(FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(0).getResourceType().toString());
            imageView.setOnMouseClicked(mouseEvent -> onResourceClick(mouseEvent));
            imageView.setCursor(Cursor.HAND);
            firstShelfBox.getChildren().add(imageView);
        }

        for (int i = 0; i < FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(1).getCount(); i++) {
            ImageView imageView = new ImageView();
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/resources/" + FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(1).getResourceType().toString() + ".png"))));
            imageView.setAccessibleText(FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(1).getResourceType().toString());
            imageView.setOnMouseClicked(mouseEvent -> onResourceClick(mouseEvent));
            imageView.setCursor(Cursor.HAND);
            secondShelfBox.getChildren().add(imageView);
        }

        for (int i = 0; i < FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(2).getCount(); i++) {
            ImageView imageView = new ImageView();
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/resources/" + FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(2).getResourceType().toString() + ".png"))));
            imageView.setAccessibleText(FXGUI.getClient().getView().getReducedGameModel().getWareHouseDepot().get(2).getResourceType().toString());
            imageView.setOnMouseClicked(mouseEvent -> onResourceClick(mouseEvent));
            imageView.setCursor(Cursor.HAND);
            thirdShelfBox.getChildren().add(imageView);
        }
    }

    private void onResourceClick(MouseEvent event) {
        ResourceType resourceReplacement;
        ImageView resourceClicked = (ImageView) event.getSource();

        if (input.containsKey(ResourceType.valueOf(resourceClicked.getAccessibleText()))) {
            input.put(ResourceType.valueOf(resourceClicked.getAccessibleText()), input.get(ResourceType.valueOf(resourceClicked.getAccessibleText())) + 1);
        } else input.put(ResourceType.valueOf(resourceClicked.getAccessibleText()), 1);

        resourceClicked.setDisable(true);
        resourceClicked.setFitHeight(75);
        resourceClicked.setFitWidth(75);
        inputVBox.getChildren().add(resourceClicked);
        inputClicked ++;
        if (inputCount == inputClicked) {
            firstShelfBox.setDisable(true);
            secondShelfBox.setDisable(true);
            thirdShelfBox.setDisable(true);
            outputVBox.setVisible(true);
            dialogLabel.setText("Great! Now you have to choose the resource that you will get");
        }
    }

    public void onOutputResourceClicked(MouseEvent mouseEvent) {
        ImageView resourceClicked = (ImageView) mouseEvent.getSource();
        if (output.containsKey(ResourceType.valueOf(resourceClicked.getAccessibleText()))) {
            output.put(ResourceType.valueOf(resourceClicked.getAccessibleText()), output.get(ResourceType.valueOf(resourceClicked.getAccessibleText())) + 1);
        } else output.put(ResourceType.valueOf(resourceClicked.getAccessibleText()), 1);

        outputClicked ++;
        if (outputCount == outputClicked)
            FXGUI.getClient().sendMessage(new ActivateBasicProductionMessage(FXGUI.getClient().getNickName(), input, output));
    }

    public void onCancel(ActionEvent event) {
        FXGUI.getClient().sendMessage(new ActivateBasicProductionMessage(FXGUI.getClient().getNickName(), null, null));
    }
}
