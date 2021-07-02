package it.polimi.ingsw.editor;

import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * Manages to send the player to different branches based on what he wants to change.
 */
public class SpecialAbilityEdit {
    /**
     * Changes the scene in order to modify the white marble ability
     * @param event the click on the marble button
     */
    public void marbleButton(ActionEvent event){
        try {
            Editor.setRoot("editWhiteMarble");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes the scene in order to modify the production ability
     * @param event the click on the production button
     */
    public void productionButton(ActionEvent event){
        try {
            Editor.setRoot("editAdditionalProductionPower");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes the scene in order to modify the depot ability
     * @param event the click on the depot button
     */
    public void depotButton(ActionEvent event){
        try {
            Editor.setRoot("editSpecialDepot");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Changes the scene in order to modify the discount ability
     * @param event the click on the discount button
     */
    public void discountButton(ActionEvent event){
        try {
            Editor.setRoot("editSpecialDiscount");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
