package it.polimi.ingsw.client.view.GUI.SceneController.Editor;

import it.polimi.ingsw.server.model.resources.*;
import it.polimi.ingsw.server.model.specialAbilities.*;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class LeaderCardEditController {
    public TextField coin;
    public TextField shield;
    public TextField servant;
    public TextField stone;
    public TextField green;
    public TextField blue;
    public TextField purple;
    public TextField yellow;
    public TextField points;

    private Resource resource= Any.getInstance();
    private SpecialAbility specialAbility;
    private int quantity=0;

    private int coins;
    private int shields;
    private int stones;
    private int servants;
    private int greenLvl;
    private int blueLvl;
    private int yellowLvl;
    private int purpleLvl;
    private int victoryPoints;


    public void edit(ActionEvent event){
        coins= Integer.parseInt(coin.getText());
        shields= Integer.parseInt(shield.getText());
        servants= Integer.parseInt(servant.getText());
        stones= Integer.parseInt(stone.getText());
        greenLvl= Integer.parseInt(green.getText());
        blueLvl= Integer.parseInt(blue.getText());
        yellowLvl= Integer.parseInt(yellow.getText());
        purpleLvl= Integer.parseInt(purple.getText());
        victoryPoints= Integer.parseInt(points.getText());
    }

    public void discount(ActionEvent event){
        specialAbility= new SpecialDiscount(resource, quantity);
    }

    public void marble(ActionEvent event){
        specialAbility= new SpecialWhiteMarble(resource,quantity);
    }

    public void additionalProductionPower(ActionEvent event){
        specialAbility= new SpecialAdditionalProductionPower(resource);
    }

    public void depotSpecial(ActionEvent event){
        specialAbility= new SpecialExtraDepot(resource,quantity);
    }

    public void coinResource(ActionEvent event){
        resource= Coin.getInstance();
    }
    public void shieldResource(ActionEvent event){
        resource= Shield.getInstance();
    }
    public void stoneResource(ActionEvent event){
        resource= Stone.getInstance();
    }
    public void servantResource(ActionEvent event){
        resource= Servant.getInstance();
    }

}
