package it.polimi.ingsw.server.model;


import java.util.List;

public class VaticanReportSection {
    private final List<Cell> cells;
    private final Integer victoryPoints;

    public VaticanReportSection(List<Cell> cells, Integer victoryPoints) {
        this.cells = cells;
        this.victoryPoints = victoryPoints;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public Integer getVictoryPoints() {
        return victoryPoints;
    }

}
