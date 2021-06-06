package it.polimi.ingsw.server.model.globalBoard.faithTrack;

import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedFaithPositionMessage;
import it.polimi.ingsw.server.model.player.Player;
import it.polimi.ingsw.utils.observer.Observable;

/**
 * The type Cell. Basic cell.
 */
public class Cell extends Observable {
    private final Integer victoryPoints;

    /**
     * Instantiates a new Cell.
     *
     * @param victoryPoints the victory points of the cell
     */
    public Cell(Integer victoryPoints) {
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
        notifyObserver(new UpdatedFaithPositionMessage(player.getNickName(), player.getFaithPosition()));
        //player.getGameController().sendBroadCastMessage(new UpdatedFaithPositionMessage(player.getNickName(), player.getFaithPosition()));
    }
}