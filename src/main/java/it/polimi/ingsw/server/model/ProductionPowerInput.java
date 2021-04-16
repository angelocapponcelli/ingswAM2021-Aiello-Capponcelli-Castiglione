package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.interfaces.Checkable;
import it.polimi.ingsw.server.model.interfaces.Payable;
import it.polimi.ingsw.server.model.resources.*;

import java.util.HashMap;
import java.util.Map;

public class    ProductionPowerInput implements Checkable, Payable {
    private Map<Resource, Integer> productionPowerInput;

    public ProductionPowerInput() {
        this.productionPowerInput = new HashMap<>();
    }

    public void add(ResourceType resourceType, Integer multiplicity){
        switch (resourceType.toString()){
            case "COIN":
                productionPowerInput.put(Coin.getInstance(), multiplicity);
                break;
            case "STONE":
                productionPowerInput.put(Stone.getInstance(), multiplicity);
                break;
            case "SHIELD":
                productionPowerInput.put(Shield.getInstance(), multiplicity);
                break;
            case "SERVANT":
                productionPowerInput.put(Servant.getInstance(), multiplicity);
                break;
        }
    }
    @Override
    public boolean check(RealPlayer realPlayer) {
        return true;
    }

    @Override
    public void pay(RealPlayer realPlayer) {

    }
}
