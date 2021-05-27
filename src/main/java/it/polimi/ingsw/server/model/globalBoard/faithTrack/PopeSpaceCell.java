package it.polimi.ingsw.server.model.globalBoard.faithTrack;

import it.polimi.ingsw.server.model.player.Player;

/**
 * The type Pope space cell. When a Faith Marker reaches a Pope
 * space cell, a Vatican Report occurs.
 */
public class PopeSpaceCell extends Cell {
    private Boolean alreadyOccupied;

    public PopeSpaceCell(Integer victoryPoints) {
        super(victoryPoints);
        this.alreadyOccupied = false;
    }

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
            for (Player player1 : player.getGameController().getGameModel().getPlayers()) {
                player1.doVaticanReportUpdate(player.getGameController().getGameModel().getGlobalBoard().getFaithTrack().getVaticanReportSectionFromCell(this));
            }
            setAlreadyOccupied();
        }
    }
}
