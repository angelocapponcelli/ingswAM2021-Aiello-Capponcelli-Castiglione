package it.polimi.ingsw.server.model.globalBoard;

import it.polimi.ingsw.server.model.globalBoard.faithTrack.FaithTrack;
import it.polimi.ingsw.server.model.productionPower.ProductionPower;
import it.polimi.ingsw.utils.observer.Observable;
import it.polimi.ingsw.utils.observer.Observer;
import it.polimi.ingsw.utils.parsers.BasicProductionPowerParser;

import java.io.FileNotFoundException;

public class GlobalBoard extends Observable {

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

    public GlobalBoard() {
        try {
            marketTray = new MarketTray();
            faithTrack = new FaithTrack();
            developmentCardGrid = new DevelopmentCardGrid();
            basicProductionPower = BasicProductionPowerParser.getBasicProductionPower();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public MarketTray getMarketTray() {
        return marketTray;
    }

    public DevelopmentCardGrid getDevelopmentCardGrid() {
        return developmentCardGrid;
    }

    public FaithTrack getFaithTrack() {
        return faithTrack;
    }

    public ProductionPower getBasicProductionPower() {
        return basicProductionPower;
    }


    @Override
    public void addObserver(Observer obs) {
        super.addObserver(obs);
        marketTray.addObserver(obs);
        developmentCardGrid.addObserver(obs);
        faithTrack.addObserver(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        super.removeObserver(obs);
        marketTray.removeObserver(obs);
        developmentCardGrid.removeObserver(obs);
        faithTrack.removeObserver(obs);
    }
}
