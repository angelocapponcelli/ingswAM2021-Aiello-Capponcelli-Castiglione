package it.polimi.ingsw.server.model;

import java.util.Map;

/**
 * Cost of development cards.
 */
public class Cost implements Checkable, Payable{
    private Map<Resource,Integer> cost;

    public Cost(Map<Resource, Integer> cost) {
        this.cost = cost;
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
