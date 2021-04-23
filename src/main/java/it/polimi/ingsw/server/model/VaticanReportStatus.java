package it.polimi.ingsw.server.model;

import java.util.ArrayList;
import java.util.List;

public class VaticanReportStatus {
    private List<VaticanReportSection> flippedVaticanReportSections;

    public VaticanReportStatus() {
        this.flippedVaticanReportSections = new ArrayList<>();
    }

    /**
     * Performs the flipping of the Pope's favor tile by adding the Vatican Report section in the flippedVaticanReportSections list.
     *
     * @param vaticanReportSection The Vatican Report Section involved in the flipping of the Pope's favor tile
     */
    public void flip(VaticanReportSection vaticanReportSection) {
        flippedVaticanReportSections.add(vaticanReportSection);
    }

    /**
     * @return The sum of the victory points of all the flipped Vatican Report Sections.
     */
    public Integer getVictoryPoint() {
        Integer tmpvictoryPoints = 0;
        for (VaticanReportSection vaticanReportSection : flippedVaticanReportSections) {
            tmpvictoryPoints += vaticanReportSection.getVictoryPoints();
        }
        return tmpvictoryPoints;

    }

}
