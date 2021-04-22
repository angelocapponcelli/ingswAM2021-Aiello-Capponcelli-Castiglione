package it.polimi.ingsw.server.model;

import java.util.List;

/** HAS TO BE MODIFIED BECAUSE THERE ARE NO DEPOT FOR MARKET*/
public class PersonalBoard {
    private List<Depot> depotForMarket;
    private StrongBoxDepot strongBoxDepot;
    private PersonalDevelopmentBoard personalDevelopmentBoard;
    private InHandLeaderCard inHandLeaderCard;
    private List<ProductionPower> productionPower;

    public PersonalBoard(List<Depot> depotForMarket, StrongBoxDepot strongBoxDepot, PersonalDevelopmentBoard personalDevelopmentBoard,InHandLeaderCard inHandLeaderCard, List<ProductionPower> productionPower){
        this.depotForMarket=depotForMarket;
        this.strongBoxDepot=strongBoxDepot;
        this.personalDevelopmentBoard= personalDevelopmentBoard;
        this.inHandLeaderCard=inHandLeaderCard;
        this.productionPower=productionPower;
    }
    /**to do verify resources(?)*/

    public void addProductionPower(ProductionPower productionPower1){
        this.productionPower.add(productionPower1);
    }

    public List<Depot> getDepotForMarket(){
        return depotForMarket;
    }

    public PersonalDevelopmentBoard getPersonalDevelopmentBoard() {
        return personalDevelopmentBoard;
    }

    public InHandLeaderCard getInHandLeaderCard() {
        return inHandLeaderCard;
    }

    public List<ProductionPower> getProductionPower() {
        return productionPower;
    }

    public StrongBoxDepot getStrongBoxDepot() {
        return strongBoxDepot;
    }

}
