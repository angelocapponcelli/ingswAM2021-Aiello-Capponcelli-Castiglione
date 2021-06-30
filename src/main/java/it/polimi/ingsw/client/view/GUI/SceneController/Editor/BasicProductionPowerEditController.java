package it.polimi.ingsw.client.view.GUI.SceneController.Editor;

import it.polimi.ingsw.server.model.interfaces.Producible;
import it.polimi.ingsw.server.model.resources.*;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class BasicProductionPowerEditController {

    public TextField coin;
    public TextField shield;
    public TextField servant;
    public TextField stone;
    public TextField any;
    public TextField faith;
    public TextField coinInput;
    public TextField shieldInput;
    public TextField servantInput;
    public TextField stoneInput;
    public TextField anyInput;


    private int coins;
    private int shields;
    private int servants;
    private int stones;
    private int anyNumber;
    private int faiths;
    private int coinsInput;
    private int shieldsInput;
    private int servantsInput;
    private int stonesInput;
    private int anyNumberInput;

    private Map<Producible, Integer> output= new HashMap<>();
    private Map<Producible,Integer> input= new HashMap<>();

    public void inserted(ActionEvent event){
        coins= Integer.parseInt(coin.getText());
        shields= Integer.parseInt(shield.getText());
        servants= Integer.parseInt(servant.getText());
        stones= Integer.parseInt(stone.getText());
        faiths= Integer.parseInt(faith.getText());
        anyNumber= Integer.parseInt(any.getText());
        coinsInput= Integer.parseInt(coinInput.getText());
        shieldsInput= Integer.parseInt(shieldInput.getText());
        servantsInput= Integer.parseInt(servantInput.getText());
        stonesInput= Integer.parseInt(stoneInput.getText());
        anyNumberInput= Integer.parseInt(anyInput.getText());
    }

    public void createOutputMap(){
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

    public void createInputMap(){
        if (coinsInput>0){
            output.put(Coin.getInstance(),coinsInput);
        }
        if (shieldsInput>0){
            output.put(Shield.getInstance(),shieldsInput);
        }
        if (stonesInput>0){
            output.put(Stone.getInstance(),stonesInput);
        }
        if (anyNumberInput >0){
            output.put(Any.getInstance(),anyNumberInput);
        }
        if(servantsInput>0){
            output.put(Servant.getInstance(), servantsInput);
        }
    }
    public Map<Producible,Integer> getOutput(){
        return this.output;
    }

    public Map<Producible,Integer> getInput(){
        return this.input;
    }
}
