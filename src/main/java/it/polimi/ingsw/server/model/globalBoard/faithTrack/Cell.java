package it.polimi.ingsw.server.model.globalBoard.faithTrack;

import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedFaithPositionMessage;
import it.polimi.ingsw.server.model.player.Player;
import it.polimi.ingsw.utils.observer.Observable;

/**
 * The type Cell. Basic cell.
 */
public class Cell extends Observable {
    private final int id;
    private final Integer victoryPoints;

    /**
     * Class constructor. Instantiates a new Cell.
     *
     * @param victoryPoints the victory points of the cell
     */
    public Cell(Integer victoryPoints, int id) {
        this.id = id;
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
     * onOccupy. This method is called when a player steps on this cell.
     *
     * @param player the player who steps into the cell.
     */
    public void onOccupy(Player player) {
        notifyObserver(new UpdatedFaithPositionMessage(player.getNickName(), player.getFaithPosition()));
    }

    public int getId() {
        return id;
    }
}