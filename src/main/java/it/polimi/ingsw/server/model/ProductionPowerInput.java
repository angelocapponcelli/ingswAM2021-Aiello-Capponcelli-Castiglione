package it.polimi.ingsw.server.model;

import java.util.Map;

public class ProductionPowerInput implements Checkable, Payable{
    private Map<Resource, Integer> productionPowerInput;

    public ProductionPowerInput(Map<Resource, Integer> productionPowerInput) {
        this.productionPowerInput = productionPowerInput;
    }

    @Override
    public boolean check(Player player) {
        return true;
    }

    @Override
    public void pay(Player player) {

    }
}
