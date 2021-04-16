package it.polimi.ingsw.server.model;

import java.util.List;

/** HAS TO BE MODIFIED BECAUSE THERE ARE NO DEPOT FOR MARKET*/
public class PersonalBoard {
    private WareHouseDepot wareHouseDepot;
    private StrongBoxDepot strongBoxDepot;
    private DevelopmentBoard developmentBoard;
    private InHandLeaderCard inHandLeaderCard;
    private List<ProductionPower> productionPower;

    public PersonalBoard(WareHouseDepot wareHouseDepot, StrongBoxDepot strongBoxDepot, DevelopmentBoard developmentBoard,InHandLeaderCard inHandLeaderCard, List<ProductionPower> productionPower){
        this.wareHouseDepot=wareHouseDepot;
        this.strongBoxDepot=strongBoxDepot;
        this.developmentBoard= developmentBoard;
        this.inHandLeaderCard=inHandLeaderCard;
        this.productionPower=productionPower;
    }
    /**to do verify resources(?)*/

    public void addProductionPower(ProductionPower productionPower1){
        this.productionPower.add(productionPower1);
    }

    public WareHouseDepot getWareHouseDepot(){
        return wareHouseDepot;
    }

    public DevelopmentBoard getDevelopmentBoard() {
        return developmentBoard;
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
