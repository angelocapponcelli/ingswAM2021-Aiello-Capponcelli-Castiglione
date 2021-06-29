package it.polimi.ingsw.server.model.player;

import it.polimi.ingsw.server.model.globalBoard.faithTrack.VaticanReportSection;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is an attribute of each player. It has a list of every vatican report section that the player manages to flip
 */
public class VaticanReportStatus {
    private final List<VaticanReportSection> flippedVaticanReportSections;

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
