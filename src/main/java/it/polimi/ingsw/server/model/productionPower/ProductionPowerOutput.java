package it.polimi.ingsw.server.model.productionPower;

import it.polimi.ingsw.server.model.interfaces.Activable;
import it.polimi.ingsw.server.model.interfaces.Producible;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.*;

import java.util.HashMap;
import java.util.Map;

public class ProductionPowerOutput implements Activable {
    private final Map<Producible, Integer> productionPowerOutput;

    public ProductionPowerOutput() {
        this.productionPowerOutput = new HashMap<>();
    }

    public ProductionPowerOutput(Map<ResourceType, Integer> input) {
        Map<Producible, Integer> tmpOutput = new HashMap<>();
        input.forEach( (k,v) -> tmpOutput.put(ResourceType.getResourceClass(k), v));
        productionPowerOutput = tmpOutput;
    }

    public Map<Producible, Integer> getProductionPowerOutput() {
        return productionPowerOutput;
    }

    public void add(ResourceType resourceType, Integer multiplicity) {
        switch (resourceType.toString()) {
            case "ANY":
                productionPowerOutput.put(Any.getInstance(), multiplicity);
                break;
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
        for (Map.Entry<Producible, Integer> entry : productionPowerOutput.entrySet()) {
            entry.getKey().onProduction(realPlayer, entry.getValue());
        }
    }
}
