package it.polimi.ingsw.editor;

import javafx.event.ActionEvent;

import java.io.IOException;

public class SpecialAbilityEdit {

    public void marbleButton(ActionEvent event){
        try {
            Editor.setRoot("editWhiteMarble");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void productionButton(ActionEvent event){
        try {
            Editor.setRoot("editAdditionalProductionPower");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void depotButton(ActionEvent event){
        try {
            Editor.setRoot("editSpecialDepot");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void discountButton(ActionEvent event){
        try {
            Editor.setRoot("editSpecialDiscount");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
