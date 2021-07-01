package it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages;

import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.networking.messages.MessageType;

/**
 * New Game Message
 */
public class NewGameMessage extends Message {
    private final Integer playersNumber;

    /**
     * Class constructor
     * @param playersNumber the number of players that can join the game
     */
    public NewGameMessage(Integer playersNumber) {
        messageType = MessageType.NEW_GAME;
        this.playersNumber = playersNumber;
    }

    /**
     *
     * @return the number of players that can join the game
     */
    public Integer getPlayersNumber() {
        return playersNumber;
    }
}
