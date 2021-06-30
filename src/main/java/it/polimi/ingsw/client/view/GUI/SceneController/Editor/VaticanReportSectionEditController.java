package it.polimi.ingsw.client.view.GUI.SceneController.Editor;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class VaticanReportSectionEditController {
    public TextField points;

    private Integer victory;

    public void insertedVictoryPoints(ActionEvent event){
        victory= Integer.parseInt(points.getText());
    }

    public Integer getNumber(){
        return this.victory;
    }
}
