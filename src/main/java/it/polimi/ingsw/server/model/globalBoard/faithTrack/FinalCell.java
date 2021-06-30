package it.polimi.ingsw.server.model.globalBoard.faithTrack;


import it.polimi.ingsw.networking.messages.serverMessage.GameEndedMessage;
import it.polimi.ingsw.server.model.player.Player;

/**
 * The type Final cell.
 */
public class FinalCell extends PopeSpaceCell {
    /**
     * Class constructor. Instantiates a new Final cell.
     *
     * @param victoryPoints the victory points of the cell.
     */
    public FinalCell(Integer victoryPoints, int id) {
        super(victoryPoints, id);
    }

    /**
     * onOccupy. This method is called when a player steps on this cell.
     * @param player The player who step into the cell.
     */
    @Override
    public void onOccupy(Player player) {
        super.onOccupy(player);
        player.getGameController().getGameModel().endGame(player);
        notifyObserver(new GameEndedMessage(player.getNickName()));
    }

}
