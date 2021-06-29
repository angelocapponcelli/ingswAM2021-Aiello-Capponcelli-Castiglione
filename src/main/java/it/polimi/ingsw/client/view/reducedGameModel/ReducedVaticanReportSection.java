package it.polimi.ingsw.client.view.reducedGameModel;

import it.polimi.ingsw.server.model.globalBoard.faithTrack.FinalCell;
import it.polimi.ingsw.server.model.globalBoard.faithTrack.PopeSpaceCell;
import it.polimi.ingsw.server.model.globalBoard.faithTrack.VaticanReportSection;

import java.util.List;
import java.util.stream.Collectors;

public class ReducedVaticanReportSection {
    private final Integer victoryPoints;
    private final List<ReducedFaithCell> cells;

    public ReducedVaticanReportSection(VaticanReportSection vaticanReportSection){
        this.victoryPoints = vaticanReportSection.getVictoryPoints();
        this.cells = vaticanReportSection.getCells().stream().map(cell -> {
            String cellType = "NORMAL";
            if(cell instanceof PopeSpaceCell) cellType = "POPE";
            if (cell instanceof FinalCell) cellType = "FINAL";
            return new ReducedFaithCell(cell.getVictoryPoints(), vaticanReportSection.getVictoryPoints(),cellType);
        }
        ).collect(Collectors.toList());
    }

    public Integer getVictoryPoints() {
        return victoryPoints;
    }

    public List<ReducedFaithCell> getCells() {
        return cells;
    }
}
