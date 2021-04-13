package it.polimi.ingsw.server.model;

public class GlobalBoard {

    private MarketTray marketTray;
    private DevelpmentCardGrid develpmentCardGrid;
    private FaithTrack faithTrack;
    private ProductionPower basicProductionPower;

    public  GlobalBoard (MarketTray marketTray, DevelpmentCardGrid develpmentCardGrid, FaithTrack faithTrack, ProductionPower basicProductionPower){
        this.marketTray = marketTray;
        this.develpmentCardGrid = develpmentCardGrid;
        this.faithTrack = faithTrack;
        this.basicProductionPower = basicProductionPower;
    }

    public MarketTray getMarketTray() {return marketTray;}
    public DevelpmentCardGrid getDevelpmentCardGrid() {return develpmentCardGrid;}
    public FaithTrack getFaithTrack() {return faithTrack;}
    public ProductionPower getBasicProductionPower() {return basicProductionPower;}


}
