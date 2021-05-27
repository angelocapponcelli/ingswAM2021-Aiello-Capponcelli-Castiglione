package it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages;

import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.networking.messages.MessageType;

public class NewGameMessage extends Message {
    private final Integer playersNumber;

    public NewGameMessage(Integer playersNumber) {
        messageType = MessageType.NEW_GAME;
        this.playersNumber = playersNumber;
    }

    public Integer getPlayersNumber() {
        return playersNumber;
    }
}
