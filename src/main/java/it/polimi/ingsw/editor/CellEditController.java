package it.polimi.ingsw.editor;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class CellEditController {
    public TextField points;

    private Integer victory;

    public void insertedPoints(ActionEvent event){
        victory= Integer.parseInt(points.getText());
    }

    public Integer getNumber(){
        return this.victory;
    }
}
