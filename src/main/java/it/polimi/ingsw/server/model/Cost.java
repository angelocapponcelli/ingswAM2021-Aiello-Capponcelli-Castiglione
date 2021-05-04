package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.exceptions.DepotException;
import it.polimi.ingsw.server.model.interfaces.Checkable;
import it.polimi.ingsw.server.model.interfaces.Payable;
import it.polimi.ingsw.server.model.resources.Resource;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.util.HashMap;
import java.util.Map;

/**
 * Cost of development cards.
 */
public class Cost implements Checkable, Payable {
    private Map<Resource, Integer> cost;

    public Cost() {
        this.cost = new HashMap<>();
    }

    public Map<Resource, Integer> getCost() {
        return cost;
    }

    public void add(ResourceType resourceType, Integer multiplicity) {
        cost.put(ResourceType.getResourceClass(resourceType), multiplicity);
    }

    /**
     * This method check if a RealPlayer has enough resources in his depots
     * For every cost entry check if is higher or lower than the amount of same resource type in the depots
     *
     * @return true if RealPlayer has enough resources in his depots otherwise false
     * @parameter realPlayer whose depots are checked
     */
    @Override
    public boolean check(RealPlayer realPlayer) {
        int requiredCount;
        for (Map.Entry<Resource, Integer> entry : cost.entrySet()) {
            requiredCount = entry.getValue();

            for (SpecialAbility specialAbility : realPlayer.getPersonalBoard().getInHandLeaderCards().getEnabledAbilities()) {
                if (specialAbility.getClass() == SpecialDiscount.class && entry.getKey() == ((SpecialDiscount) specialAbility).getResource()) {
                    requiredCount--;
                    break;
                }
            }

            if ( requiredCount > realPlayer.getPersonalBoard().getSpecificResourceCount(entry.getKey().getResourceType()) )
                return false;
        }
        return true;
    }

    /**
     * This method remove resources from RealPlayer Depot
     *
     * @parameter realPlayer which resources are removed from
     */
    @Override
    public void pay(RealPlayer realPlayer) throws DepotException {
        int count;
        if (this.check(realPlayer)) {
            for (Map.Entry<Resource, Integer> entry : cost.entrySet()) {
                count = entry.getValue();

                for (SpecialAbility specialAbility : realPlayer.getPersonalBoard().getInHandLeaderCards().getEnabledAbilities()) {
                    if (specialAbility.getClass() == SpecialDiscount.class && entry.getKey() == ((SpecialDiscount) specialAbility).getResource()) {
                        count--;
                        break;
                    }
                }

                while( count > 0 && realPlayer.getPersonalBoard().getWareHouseDepot().getSpecificResourceCount(entry.getKey().getResourceType()) > 0  ){
                    realPlayer.getPersonalBoard().getWareHouseDepot().removeResources(entry.getKey().getResourceType(),1);
                    count--;
                }
                while( count > 0 && realPlayer.getPersonalBoard().getSpecialDepots().getSpecificResourceCount(entry.getKey().getResourceType()) > 0  ){
                    realPlayer.getPersonalBoard().getSpecialDepots().removeResources(entry.getKey().getResourceType(),1);
                    count--;
                }
                while( count > 0 && realPlayer.getPersonalBoard().getStrongBoxDepot().getSpecificResourceCount(entry.getKey().getResourceType()) > 0  ){
                    realPlayer.getPersonalBoard().getStrongBoxDepot().removeResources(entry.getKey().getResourceType(),1);
                    count--;
                }


            }
        }
    }





}
