package it.polimi.ingsw.server.model;

public class GlobalBoard {

    private MarketTray marketTray;
    private DevelopmentCardGrid developmentCardGrid;
    private FaithTrack faithTrack;
    private ProductionPower basicProductionPower;

    public GlobalBoard(MarketTray marketTray, DevelopmentCardGrid developmentCardGrid, FaithTrack faithTrack, ProductionPower basicProductionPower) {
        this.marketTray = marketTray;
        this.developmentCardGrid = developmentCardGrid;
        this.faithTrack = faithTrack;
        this.basicProductionPower = basicProductionPower;
    }

    public MarketTray getMarketTray() {
        return marketTray;
    }

    public DevelopmentCardGrid getDevelpmentCardGrid() {
        return developmentCardGrid;
    }

    public FaithTrack getFaithTrack() {
        return faithTrack;
    }

    public ProductionPower getBasicProductionPower() {
        return basicProductionPower;
    }


}
