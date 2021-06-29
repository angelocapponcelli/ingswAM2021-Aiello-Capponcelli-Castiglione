package it.polimi.ingsw.server.model.productionPower;

import it.polimi.ingsw.server.model.interfaces.Activable;
import it.polimi.ingsw.server.model.interfaces.Producible;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The production power output is what the player will receive if he decides to activate the production.
 */
public class ProductionPowerOutput implements Activable {
    private final Map<Producible, Integer> productionPowerOutput;

    /**
     * Class constructor. Instantiates a new Production Power Output.
     */
    public ProductionPowerOutput() {
        this.productionPowerOutput = new HashMap<>();
    }

    /**
     * Class construction. Instantiates a new Production Power Output.
     * @param input map that represents the resources and quantity that characterizes the production power
     */

    public ProductionPowerOutput(Map<ResourceType, Integer> input) {
        Map<Producible, Integer> tmpOutput = new HashMap<>();
        input.forEach( (k,v) -> tmpOutput.put(ResourceType.getResourceClass(k), v));
        productionPowerOutput = tmpOutput;
    }


    /**
     * Gets the map that represents the resources and quantity that the player will receive
     * @return map
     */
    public Map<Producible, Integer> getProductionPowerOutput() {
        return productionPowerOutput;
    }


    /**
     * Adds the resources and quantity to the map
     * @param resourceType the resource of the new requirement
     * @param multiplicity the quantity that the player will receive
     */
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

    /**
     * Performs the on production of the resources that are produced when the player decides to activate the production
     * @param realPlayer The player who performs the activation.
     */
    @Override
    public void onActivation(RealPlayer realPlayer) {
        for (Map.Entry<Producible, Integer> entry : productionPowerOutput.entrySet()) {
            entry.getKey().onProduction(realPlayer, entry.getValue());
        }
    }
}
