package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.interfaces.Checkable;
import it.polimi.ingsw.server.model.interfaces.Payable;
import it.polimi.ingsw.server.model.resources.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The input of productionPower, it's useful in production process to check and pay resources needed by the production
 */
public class    ProductionPowerInput implements Checkable, Payable {
    private Map<Resource, Integer> productionPowerInput;

    public ProductionPowerInput() {
        this.productionPowerInput = new HashMap<>();
    }

    public void add(ResourceType resourceType, Integer multiplicity){
        productionPowerInput.put(ResourceType.getResourceClass(resourceType), multiplicity);

    }

    /**
     * This method check if a RealPlayer has enough resources in his depots
     * For every productionPowerInput entry check if is higher or lower than the amount of same resource type in the depots
     * @parameter player whose depots are checked
     * @return true if RealPlayer has enough resources in his depots otherwise false
     */
    @Override
    public boolean check(RealPlayer realPlayer) {
        int count = 0;
        for (Map.Entry<Resource, Integer> entry : productionPowerInput.entrySet()) {
            for (Depot depotForMarket : realPlayer.getPersonalBoard().getDepotForMarket()) {
                count = count + depotForMarket.getResourceCount(entry.getKey());
            }
            count = count + realPlayer.getPersonalBoard().getStrongBoxDepot().getResourceCount(entry.getKey());
            if (entry.getValue() > count)
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
        int count;
        if (this.check(realPlayer)) {
            for (Map.Entry<Resource, Integer> entry : productionPowerInput.entrySet()) {
                count = entry.getValue();
                for (SpecialDepot specialDepot : realPlayer.getPersonalBoard().getSpecialDepot()) { //Special depot loop, in case there was more than one
                    if (specialDepot.getResourceCount(entry.getKey()) != 0) {
                        if (count <= specialDepot.getResourceCount(entry.getKey())) {
                            specialDepot.remove(entry.getKey(), count);
                            count = 0;
                            break;
                        } else {
                            count = count - specialDepot.getResourceCount(entry.getKey());
                            specialDepot.remove(entry.getKey(), specialDepot.getResourceCount(entry.getKey()));
                        }
                    }
                }
                if (count > 0) { // in case there are still resource to pay
                    for (WareHouseDepot wareHouseDepot : realPlayer.getPersonalBoard().getWarehouseDepot()) { //WareHouse depot loop, in case there was more than one
                        if (wareHouseDepot.getResourceCount(entry.getKey()) != 0) {
                            if (count <= wareHouseDepot.getResourceCount(entry.getKey())) {
                                wareHouseDepot.remove(entry.getKey(), count);
                                count = 0;
                                break;
                            } else {
                                count = count - wareHouseDepot.getResourceCount(entry.getKey());
                                wareHouseDepot.remove(entry.getKey(), wareHouseDepot.getResourceCount(entry.getKey()));
                            }
                        }
                    }
                    if (count > 0) { //StrongBox remove part, in case there are still resource to pay
                        realPlayer.getPersonalBoard().getStrongBoxDepot().remove(entry.getKey(), count);
                    }
                }


            }
        }
        /**
         * todo:
         * else launch an exception
         */
    }
}
