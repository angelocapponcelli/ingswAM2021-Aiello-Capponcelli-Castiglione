package it.polimi.ingsw.server.model.productionPower;

import it.polimi.ingsw.server.model.exceptions.DepotException;
import it.polimi.ingsw.server.model.interfaces.Checkable;
import it.polimi.ingsw.server.model.interfaces.Payable;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * The input of productionPower, it's useful in production process to check and pay resources needed by the production
 */
public class ProductionPowerInput implements Checkable, Payable {
    private final Map<Resource, Integer> productionPowerInput;

    public ProductionPowerInput() {
        this.productionPowerInput = new HashMap<>();
    }

    public void add(Resource resource, Integer multiplicity) {
        productionPowerInput.put(resource, multiplicity);

    }

    /**
     * This method check if a RealPlayer has enough resources in his depots
     * For every productionPowerInput entry check if is higher or lower than the amount of same resource type in the depots
     *
     * @return true if RealPlayer has enough resources in his depots otherwise false
     * @parameter realPlayer whose depots are checked
     */
    @Override
    public boolean check(RealPlayer realPlayer) {
        for (Map.Entry<Resource, Integer> entry : productionPowerInput.entrySet()) {
            if (entry.getValue() > realPlayer.getPersonalBoard().getSpecificResourceCount(entry.getKey().getResourceType()))
                return false;
        }
        return true;
    }

    /**
     * This method remove resources from RealPlayer Depot
     *
     * @parameter player which resources are removed from
     */
    @Override
    public void pay(RealPlayer realPlayer) throws DepotException {
        if (check(realPlayer)) {
            for (Map.Entry<Resource, Integer> entry : productionPowerInput.entrySet()) {
                int count = entry.getValue();


                while (count > 0 && realPlayer.getPersonalBoard().getWareHouseDepot().getSpecificResourceCount(entry.getKey().getResourceType()) > 0) {
                    realPlayer.getPersonalBoard().getWareHouseDepot().removeResources(entry.getKey().getResourceType(), 1);
                    count--;
                }
                while (count > 0 && realPlayer.getPersonalBoard().getSpecialDepots().getSpecificResourceCount(entry.getKey().getResourceType()) > 0) {
                    realPlayer.getPersonalBoard().getSpecialDepots().removeResources(entry.getKey().getResourceType(), 1);
                    count--;
                }
                while (count > 0 && realPlayer.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(entry.getKey().getResourceType()) > 0) {
                    realPlayer.getPersonalBoard().getStrongBoxDepot().removeResources(entry.getKey().getResourceType(), 1);
                    count--;
                }
            }
        }
    }


}
