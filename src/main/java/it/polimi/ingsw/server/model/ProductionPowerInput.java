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
        productionPowerInput.put(ResourceType.getResourceClass(resourceType), multiplicity);

    }
    @Override
    public boolean check(RealPlayer realPlayer) {
        return true;
    }

    @Override
    public void pay(RealPlayer realPlayer) {

    }
}
