package it.polimi.ingsw.client.view.reducedGameModel.reducedFaithTrack;

import java.io.Serializable;

public class ReducedFaithCell implements Serializable {
    private final String cellType;
    private final int victoryPoints;
    private final int vaticanSectionVictoryPoint;

    public ReducedFaithCell(int victoryPoints, int vaticanSectionVictoryPoint, String cellType) {
        this.victoryPoints = victoryPoints;
        this.vaticanSectionVictoryPoint = vaticanSectionVictoryPoint;
        this.cellType = cellType;
    }

    public int getVaticanSectionVictoryPoint() {
        return vaticanSectionVictoryPoint;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public String getCellType() {
        return cellType;
    }
}
