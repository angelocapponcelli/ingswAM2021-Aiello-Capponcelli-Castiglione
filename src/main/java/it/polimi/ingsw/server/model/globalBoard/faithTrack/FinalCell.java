package it.polimi.ingsw.server.model.globalBoard.faithTrack;


import it.polimi.ingsw.server.Server;
import it.polimi.ingsw.server.model.player.Player;

/**
 * The type Final cell.
 */
public class FinalCell extends PopeSpaceCell {
    /**
     * Instantiates a new Final cell.
     *
     * @param victoryPoints the victory points of the cell.
     */
    public FinalCell(Integer victoryPoints) {
        super(victoryPoints);
    }

    @Override
    public void onOccupy(Player player) {
        super.onOccupy(player);
        Server.getOnGoingGame().endGame(player);
    }

}
