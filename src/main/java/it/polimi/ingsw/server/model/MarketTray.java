package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.interfaces.Takeable;

import java.util.ArrayList;
import java.util.List;

/**
 * The marketTray of the board.
 */

public class MarketTray {
    private Takeable[][] marketTray;
    private Takeable slide;

    public MarketTray(Takeable[][] marketTray, Takeable slide){
        this.marketTray = marketTray;
        this.slide = slide;
    }

    /**
     * @return The entire marketTray
     */
    public Takeable[][] getMarketTray() {
        return marketTray;
    }

    /**
     * @param row The row to return
     * @return The selected row
     */
    public List<Takeable> getRow(Integer row) {
        List<Takeable> rowList = new ArrayList<>();
        for (int i=0; i<marketTray[row].length; i++) {
            rowList.add(marketTray[row][i]);
        }
        return  rowList;
    }

    /**
     * @param column The column to return
     * @return The selected column
     */
    public List<Takeable> getColumn(Integer column) {
        List<Takeable> columnList = new ArrayList<>();
        for (int i=0; i<marketTray.length; i++) {
            columnList.add(marketTray[i][column]);
        }
        return  columnList;
    }

    /**
     * @return The current marble in the slide.
     */
    public Takeable getSlide() {
        return slide;
    }

    /**
     * @param row The selected row
     * @return The content of the selected row, consisting of takeable items.
     * @throws ArrayIndexOutOfBoundsException
     */
    public List<Takeable> selectRow (int row)  throws ArrayIndexOutOfBoundsException{
        List<Takeable> rowList = getRow(row);
        shiftRow(row);
        return rowList;
    }

    /**
     * @param column The selected column.
     *
     * @return The content of the selected column, consisting of takeable items.
     *
     * @throws ArrayIndexOutOfBoundsException
     */
    public List<Takeable> selectColumn (int column)  throws ArrayIndexOutOfBoundsException{
        List<Takeable> columnList = getColumn(column);
        shiftColumn(column);
        return columnList;
    }

    /**
     * Performs the shifting of a row and update the slide.
     *
     * @param row The row that must be shifted.
     */
    private void shiftRow (int row){
        Takeable toSlide = marketTray[row][0];
        for (int i=0; i<marketTray[row].length-1; i++){
            marketTray[row][i] = marketTray[row][i+1];
        }
        marketTray[row][marketTray[row].length-1] = slide;
        updateSlide(toSlide);
    }

    /**
     * Performs the shifting of a column and update the slide.
     *
     * @param column The column that must be shifted.
     */
    private void shiftColumn(int column) {
        Takeable toSlide = marketTray[0][column];
        for (int i=0; i<marketTray.length-1; i++){
            marketTray[i][column] = marketTray[i+1][column];
        }
        marketTray[marketTray.length-1][column] = slide;
        updateSlide(toSlide);
    }

    /**
     * Performs the updating of the slide.
     *
     * @param toSlide The marble that must be put in the slide.
     */
    private void updateSlide(Takeable toSlide){
        slide = toSlide;
    }

}
