package it.polimi.ingsw.server.model;

/**
 * The type Cell. Basic cell.
 */
public class Cell {
    private final Integer victoryPoints;

    /**
     * Instantiates a new Cell.
     *
     * @param victoryPoints the victory points of the cell
     */
    public Cell(Integer victoryPoints){
        this.victoryPoints = victoryPoints;
    }

    /**
     * Gets victory points.
     *
     * @return the victory points of the cell
     */
    public Integer getVictoryPoints() {
        return victoryPoints;
    }

    /**
     * On occupy.
     *
     * @param player the player who steps into the cell.
     */
    public void onOccupy(Player player) {
        /* it can be used to notify and update the view*/
    }
}