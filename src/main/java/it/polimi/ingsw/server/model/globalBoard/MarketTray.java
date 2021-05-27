package it.polimi.ingsw.server.model.globalBoard;

import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedMarketTrayMessage;
import it.polimi.ingsw.server.model.resources.Resource;
import it.polimi.ingsw.utils.observer.Observable;
import it.polimi.ingsw.utils.parsers.MarketTrayParser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The marketTray of the board.
 */

public class MarketTray extends Observable {
    private final Resource[][] marketTray;
    private Resource slide;

    /**
     * This constructor is use by the MarketTrayParser to create a new market tray.
     *
     * @param marketTray the marble in the marketTray grid.
     * @param slide      the marble in the slide.
     */
    public MarketTray(Resource[][] marketTray, Resource slide) {
        this.marketTray = marketTray;
        this.slide = slide;
    }

    public MarketTray() throws FileNotFoundException {
        MarketTray temp = MarketTrayParser.getMarketTray();
        this.marketTray = temp.getMarketTray();
        this.slide = temp.getSlide();
    }


    /**
     * @return The entire marketTray
     */
    public Resource[][] getMarketTray() {
        return marketTray;
    }

    /**
     * @param row The row to return
     * @return The selected row
     */
    public List<Resource> getRow(Integer row) {
        List<Resource> rowList = new ArrayList<>();
        Collections.addAll(rowList, marketTray[row]);
        return rowList;
    }

    /**
     * @param column The column to return
     * @return The selected column
     */
    public List<Resource> getColumn(Integer column) {
        List<Resource> columnList = new ArrayList<>();
        for (Resource[] resources : marketTray) {
            columnList.add(resources[column]);
        }
        return columnList;
    }

    /**
     * @return The current marble in the slide.
     */
    public Resource getSlide() {
        return slide;
    }

    /**
     * Performs the main action of selecting a row and notify the observers about the new market situation
     *
     * @param row The selected row
     * @return The content of the selected row, consisting of takeable items.
     * @throws ArrayIndexOutOfBoundsException Row not exists.
     */
    public List<Resource> selectRow(int row) throws ArrayIndexOutOfBoundsException {
        List<Resource> rowList = getRow(row);
        shiftRow(row);
        notifyObserver(new UpdatedMarketTrayMessage(this));
        return rowList;
    }

    /**
     * Performs the main action of selecting a column and notify the observers about the new market situation
     *
     * @param column The selected column.
     * @return The content of the selected column, consisting of takeable items.
     * @throws ArrayIndexOutOfBoundsException Column not exists.
     */
    public List<Resource> selectColumn(int column) throws ArrayIndexOutOfBoundsException {
        List<Resource> columnList = getColumn(column);
        shiftColumn(column);
        notifyObserver(new UpdatedMarketTrayMessage(this));
        return columnList;
    }

    /**
     * Performs the shifting of a row and update the slide.
     *
     * @param row The row that must be shifted.
     */
    private void shiftRow(int row) {
        Resource toSlide = marketTray[row][0];
        if (marketTray[row].length - 1 >= 0) {
            System.arraycopy(marketTray[row], 1, marketTray[row], 0, marketTray[row].length - 1);
        }
        marketTray[row][marketTray[row].length - 1] = slide;
        updateSlide(toSlide);
    }

    /**
     * Performs the shifting of a column and update the slide.
     *
     * @param column The column that must be shifted.
     */
    private void shiftColumn(int column) {
        Resource toSlide = marketTray[0][column];
        for (int i = 0; i < marketTray.length - 1; i++) {
            marketTray[i][column] = marketTray[i + 1][column];
        }
        marketTray[marketTray.length - 1][column] = slide;
        updateSlide(toSlide);
    }

    /**
     * Performs the updating of the slide.
     *
     * @param toSlide The marble that must be put in the slide.
     */
    private void updateSlide(Resource toSlide) {
        slide = toSlide;
    }


}