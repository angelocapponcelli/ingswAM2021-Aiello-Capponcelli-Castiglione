package it.polimi.ingsw.editor;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class WhiteMarbleEditController {

    public TextField insertedOutput;

    private Integer number;

    public void outputInserted(ActionEvent event){
        number= Integer.parseInt(insertedOutput.getText());
    }

    public Integer getNumber(){
        return this.number;
    }
}
