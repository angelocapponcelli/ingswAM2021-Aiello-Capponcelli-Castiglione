package it.polimi.ingsw.client.view.GUI.SceneController.Editor;

import it.polimi.ingsw.server.model.interfaces.Producible;
import it.polimi.ingsw.server.model.resources.*;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.effect.SepiaTone;

import java.util.HashMap;
import java.util.Map;

public class AdditionalProductionPowerEditController {

    public TextField coin;
    public TextField shield;
    public TextField servant;
    public TextField stone;
    public TextField any;
    public TextField faith;

    private int coins;
    private int shields;
    private int servants;
    private int stones;
    private int anyNumber;
    private int faiths;
    private Map<Producible, Integer> output= new HashMap<>();

    public void insertedOutput(ActionEvent event){
        coins= Integer.parseInt(coin.getText());
        shields= Integer.parseInt(shield.getText());
        servants= Integer.parseInt(servant.getText());
        stones= Integer.parseInt(stone.getText());
        faiths= Integer.parseInt(faith.getText());
        anyNumber= Integer.parseInt(any.getText());
    }

    public void createMap(){
        if (coins>0){
            output.put(Coin.getInstance(),coins);
        }
        if (shields>0){
            output.put(Shield.getInstance(),shields);
        }
        if (stones>0){
            output.put(Stone.getInstance(),stones);
        }
        if (anyNumber >0){
            output.put(Any.getInstance(),anyNumber);
        }
        if (faiths>0){
            output.put(Faith.getInstance(),faiths);
        }
        if(servants>0){
            output.put(Servant.getInstance(), servants);
        }

    }

    public Map<Producible,Integer> getMap(){
        return this.output;
    }

}
