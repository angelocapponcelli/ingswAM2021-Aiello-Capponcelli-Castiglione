package it.polimi.ingsw.server.model.globalBoard;

import it.polimi.ingsw.server.model.globalBoard.faithTrack.FaithTrack;
import it.polimi.ingsw.server.model.productionPower.ProductionPower;
import it.polimi.ingsw.utils.observer.Observable;
import it.polimi.ingsw.utils.observer.Observer;
import it.polimi.ingsw.utils.parsers.SettingsParser;

import java.io.FileNotFoundException;

/**
 * Global Board class. It contains the Market tray, the Card grid, the track and the basic production power.
 * It is shared by every player that plays the same game.
 */
public class GlobalBoard extends Observable {

    private MarketTray marketTray;
    private DevelopmentCardGrid developmentCardGrid;
    private FaithTrack faithTrack;
    private ProductionPower basicProductionPower;

    /**
     * Class constructor. Instantiates a new Global Board
     * @param marketTray where players can buy resources.
     * @param developmentCardGrid where players can buy development card.
     * @param faithTrack where players move their position.
     * @param basicProductionPower the production power that every player can use to activate production
     */

    public GlobalBoard(MarketTray marketTray, DevelopmentCardGrid developmentCardGrid, FaithTrack faithTrack, ProductionPower basicProductionPower) {
        this.marketTray = marketTray;
        this.developmentCardGrid = developmentCardGrid;
        this.faithTrack = faithTrack;
        this.basicProductionPower = basicProductionPower;
    }

    /**
     * Class Constructor. Instantiates a new GlobalBoard.
     */
    public GlobalBoard() {
        try {
            marketTray = new MarketTray();
            faithTrack = new FaithTrack();
            developmentCardGrid = new DevelopmentCardGrid();
            basicProductionPower = SettingsParser.getInstance().getBasicProductionPower();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets Market Tray of this game.
     * @return market tray of this global board
     */
    public MarketTray getMarketTray() {
        return marketTray;
    }

    /**
     * Gets Development card grid of this game.
     * @return grid of this global board
     */
    public DevelopmentCardGrid getDevelopmentCardGrid() {
        return developmentCardGrid;
    }

    /**
     * Gets the track of this game
     * @return
     */
    public FaithTrack getFaithTrack() {
        return faithTrack;
    }

    /**
     * Gets basic production
     * @return basic production that can be used from every player
     */
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
