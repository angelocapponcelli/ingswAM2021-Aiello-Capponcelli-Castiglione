package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.interfaces.Takeable;

import java.util.ArrayList;
import java.util.List;

/**
 * The marketTray of the board where you can get Takeable
 */

public class MarketTray {
    private Takeable[][] marketTray;
    private Takeable slide;

    /**
     * don't know if this constructors okay or not
     */
    public MarketTray(Takeable[][] marketTray, Takeable slide){
        this.marketTray = marketTray;
        this.slide = slide;
    }

    public List<Takeable> selectRow (int row)  throws ArrayIndexOutOfBoundsException{
        List<Takeable> rowList = new ArrayList<>();
        for (int i=0; i<marketTray[row].length; i++) {
            rowList.add(marketTray[row][i]);
        }
        shiftRow(row);
        return rowList;
    }

    public List<Takeable> selectColumn (int column)  throws ArrayIndexOutOfBoundsException{
        List<Takeable> columnList = new ArrayList<>();
        for (int i=0; i<marketTray.length; i++) {
            columnList.add(marketTray[i][column]);
        }
        shiftColumn(column);
        return columnList;
    }

    private void shiftRow (int row){
        Takeable toSlide = marketTray[row][0];
        for (int i=0; i<marketTray[row].length-1; i++){
            marketTray[row][i] = marketTray[row][i+1];
        }
        marketTray[row][marketTray[row].length-1] = slide;
        updateSlide(toSlide);
    }

    private void shiftColumn(int column) {
        Takeable toSlide = marketTray[0][column];
        for (int i=0; i<marketTray.length-1; i++){
            marketTray[i][column] = marketTray[i+1][column];
        }
        marketTray[marketTray.length-1][column] = slide;
        updateSlide(toSlide);
    }

    private void updateSlide(Takeable toSlide){
        slide = toSlide;
    }

}
