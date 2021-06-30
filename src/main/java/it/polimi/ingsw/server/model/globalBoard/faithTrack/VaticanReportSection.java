package it.polimi.ingsw.server.model.globalBoard.faithTrack;


import it.polimi.ingsw.client.view.reducedGameModel.reducedFaithTrack.ReducedVaticanReportSection;

import java.util.List;

/**
 * Vatican report section class. It is an area composed by a number of cells. If a player is on one of this cells
 * when it is called the method "onOccupy" of the corresponding Pope Space Cell the player flips the Vatican Report Section.
 * Otherwise the player has to discard it and cannot be activated anymore.
 */
public class VaticanReportSection {
    private final List<Cell> cells;
    private final Integer victoryPoints;

    /**
     * Class constructor. Instantiates a new Vatican Report Section
     * @param cells the list of cells that are contained in the Vatican Report Section
     * @param victoryPoints the points the player receives when this section is flipped
     */

    public VaticanReportSection(List<Cell> cells, Integer victoryPoints) {
        this.cells = cells;
        this.victoryPoints = victoryPoints;
    }

    /**
     * Gets the list of cells that compose the section
     * @return list of cells
     */
    public List<Cell> getCells() {
        return cells;
    }

    /**
     * Gets the points correlated to this section
     * @return victory Points of this section
     */

    public Integer getVictoryPoints() {
        return victoryPoints;
    }

    /**
     * Gets a lightweight version of a vatican report section.
     *
     * @return the reduced vatican report section
     */
    public ReducedVaticanReportSection toReduced(){

        return new ReducedVaticanReportSection(this);
    }

}
