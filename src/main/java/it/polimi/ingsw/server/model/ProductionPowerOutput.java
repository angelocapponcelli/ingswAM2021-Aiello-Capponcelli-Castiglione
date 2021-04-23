package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.interfaces.Activable;
import it.polimi.ingsw.server.model.interfaces.Producible;
import it.polimi.ingsw.server.model.resources.*;

import java.util.HashMap;
import java.util.Map;

public class ProductionPowerOutput implements Activable {
    private final Map<Producible, Integer> productionPowerOutput;

    public ProductionPowerOutput() {
        this.productionPowerOutput = new HashMap<>();
    }


    public void add(ResourceType resourceType, Integer multiplicity) {
        switch (resourceType.toString()) {
            case "COIN":
                productionPowerOutput.put(Coin.getInstance(), multiplicity);
                break;
            case "STONE":
                productionPowerOutput.put(Stone.getInstance(), multiplicity);
                break;
            case "SHIELD":
                productionPowerOutput.put(Shield.getInstance(), multiplicity);
                break;
            case "SERVANT":
                productionPowerOutput.put(Servant.getInstance(), multiplicity);
                break;
            case "FAITH":
                productionPowerOutput.put(Faith.getInstance(), multiplicity);
                break;
        }

    }

    @Override
    public void onActivation(RealPlayer realPlayer) {
        /*asks what it does*/
    }
}
