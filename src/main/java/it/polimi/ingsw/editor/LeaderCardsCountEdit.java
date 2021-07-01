package it.polimi.ingsw.editor;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class LeaderCardsCountEdit {
    public TextField cardsCount;

    private Integer count;

    public void insertedCount(ActionEvent event){
        count= Integer.parseInt(cardsCount.getText());
    }

    public Integer getNumber(){
        return this.count;
    }
}
