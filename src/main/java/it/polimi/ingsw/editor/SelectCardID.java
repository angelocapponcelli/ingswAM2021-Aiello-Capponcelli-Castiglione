package it.polimi.ingsw.editor;

import javafx.event.ActionEvent;

import java.io.IOException;

public class SelectCardID {

    public void devCardIDSelected(ActionEvent actionEvent) {
        try {
            Editor.setRoot("editDevCard");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leaderCardIDSelected(ActionEvent actionEvent) {
        try {
            Editor.setRoot("editLeaderCard");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
