package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.interfaces.Checkable;
import it.polimi.ingsw.server.model.interfaces.Payable;
import it.polimi.ingsw.server.model.resources.*;

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

    @Override
    public boolean check(RealPlayer realPlayer) {
        // TODO: check if player owns the necessary resources
        return true;
    }

    @Override
    public void pay(RealPlayer realPlayer) {
        // TODO: TODO if (this.check(player) == true) Remove resources from player
    }

}
