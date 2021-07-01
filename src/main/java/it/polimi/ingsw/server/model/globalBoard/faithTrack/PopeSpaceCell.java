package it.polimi.ingsw.server.model.globalBoard.faithTrack;

import it.polimi.ingsw.server.model.player.Player;

import java.util.List;

/**
 * The type Pope space cell. When a Faith Marker reaches a Pope
 * space cell, a Vatican Report occurs.
 */
public class PopeSpaceCell extends Cell {
    private Boolean alreadyOccupied;

    /**
     * Class constructor. Instantiates a new Pope Space Cell.
     * @param victoryPoints the points that are associated with the cell.
     */
    public PopeSpaceCell(Integer victoryPoints, int id) {
        super(victoryPoints,id);
        this.alreadyOccupied = false;
    }

    /**
     * Gets if the cell has been already occupied by some other player before.
     * @return true if some player has already stepped on that cell
     */

    public Boolean getAlreadyOccupied() {
        return alreadyOccupied;
    }

    /**
     * Set the cell already occupied in order to prevent another Vatican Report occurrence Son an already reported vatican section.
     */
    private void setAlreadyOccupied() {
        this.alreadyOccupied = true;
    }

    /**
     * Notify all the players so they can do a vatican report update.
     *
     * @param player The player who step into the cell.
     */
    @Override
    public void onOccupy(Player player) {
        super.onOccupy(player);
        if (!alreadyOccupied) {
            for (Player player1 : player.getGameController().getPlayerList()) {
                player1.doVaticanReportUpdate(player.getGameController().getGameModel().getGlobalBoard().getFaithTrack().getVaticanReportSectionFromCell(this));
            }
            setAlreadyOccupied();
        }
    }
}
