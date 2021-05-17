package it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages;

import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.networking.messages.MessageType;

public class NewGameMessage extends Message {
    public NewGameMessage() {
        messageType = MessageType.NEW_GAME;
    }
}
