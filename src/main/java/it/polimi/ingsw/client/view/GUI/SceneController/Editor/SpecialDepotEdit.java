package it.polimi.ingsw.client.view.GUI.SceneController.Editor;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class SpecialDepotEdit {
    public TextField capacity;

    private Integer number;

    public void capacityInserted(ActionEvent event){
        number= Integer.parseInt(capacity.getText());
    }

    public Integer getNumber(){
        return this.number;
    }
}
