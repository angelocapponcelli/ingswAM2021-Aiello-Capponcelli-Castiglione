package it.polimi.ingsw.server.model;

import java.util.Map;

public class ProductionPowerOutput implements Activable {
    private Map<Producible, Integer> productionOutput;

    @Override
    public void onActivation(RealPlayer realPlayer) {
    /** asks what it does*/
    }
}
