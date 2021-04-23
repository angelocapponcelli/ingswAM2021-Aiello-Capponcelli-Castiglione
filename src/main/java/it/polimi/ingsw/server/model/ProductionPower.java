package it.polimi.ingsw.server.model;

/**
 * Used by the development cards, by the board's basic production power and by the special ability that give an additional production power
 */
public class ProductionPower {
    private final ProductionPowerInput productionPowerInput;
    private final ProductionPowerOutput productionPowerOutput;

    public ProductionPower(ProductionPowerInput productionPowerInput, ProductionPowerOutput productionPowerOutput) {
        this.productionPowerInput = productionPowerInput;
        this.productionPowerOutput = productionPowerOutput;
    }

    public ProductionPowerInput getProductionInput() {
        return productionPowerInput;
    }

    public ProductionPowerOutput getProductionOutput() {
        return productionPowerOutput;
    }
}
