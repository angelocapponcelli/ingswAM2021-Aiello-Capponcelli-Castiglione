package it.polimi.ingsw.client.view.reducedGameModel.reducedFaithTrack;

import it.polimi.ingsw.server.model.globalBoard.faithTrack.VaticanReportSection;

import java.io.Serializable;

public class ReducedVaticanReportSection implements Serializable {
    private final int startCell;
    private final int endCell;
    private final Integer victoryPoints;

    public ReducedVaticanReportSection(VaticanReportSection vaticanReportSection){
        this.victoryPoints = vaticanReportSection.getVictoryPoints();
        startCell = vaticanReportSection.getCells().get(0).getId();
        endCell = vaticanReportSection.getCells().get(vaticanReportSection.getCells().size() - 1).getId();
    }

    public Integer getVictoryPoints() {
        return victoryPoints;
    }

    public int getStartCell() {
        return startCell;
    }

    public int getEndCell() {
        return endCell;
    }
}
