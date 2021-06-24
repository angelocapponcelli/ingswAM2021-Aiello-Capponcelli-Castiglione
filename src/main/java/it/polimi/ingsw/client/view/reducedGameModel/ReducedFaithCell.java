package it.polimi.ingsw.client.view.reducedGameModel;

import java.io.Serializable;

public class ReducedFaithCell implements Serializable {
    private final int victoryPoints;
    private final int vaticanSectionVictoryPoint;

    public ReducedFaithCell(int victoryPoints, int vaticanSectionVictoryPoint) {
        this.victoryPoints = victoryPoints;
        this.vaticanSectionVictoryPoint = vaticanSectionVictoryPoint;
    }

    public int getVaticanSectionVictoryPoint() {
        return vaticanSectionVictoryPoint;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }
}
