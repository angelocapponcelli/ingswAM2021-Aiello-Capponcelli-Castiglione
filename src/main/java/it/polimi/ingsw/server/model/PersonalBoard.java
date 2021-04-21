package it.polimi.ingsw.server.model;

import java.util.List;

/** HAS TO BE MODIFIED BECAUSE THERE ARE NO DEPOT FOR MARKET*/
public class PersonalBoard {
    private WareHouseDepot wareHouseDepot;
    private StrongBoxDepot strongBoxDepot;
    private PersonalDevelopmentBoard personalDevelopmentBoard;
    private InHandLeaderCard inHandLeaderCard;
    private List<ProductionPower> productionPower;

    public PersonalBoard(WareHouseDepot wareHouseDepot, StrongBoxDepot strongBoxDepot, PersonalDevelopmentBoard developmentBoard,InHandLeaderCard inHandLeaderCard, List<ProductionPower> productionPower){
        this.wareHouseDepot=wareHouseDepot;
        this.strongBoxDepot=strongBoxDepot;
        this.personalDevelopmentBoard= developmentBoard;
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

    public PersonalDevelopmentBoard getDevelopmentBoard() {
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
