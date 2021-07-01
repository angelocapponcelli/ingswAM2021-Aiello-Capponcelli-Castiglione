package it.polimi.ingsw.networking.messages;

import java.io.Serializable;

/**
 * Abstract class Method
 */
public abstract class Message implements Serializable {
    protected MessageType messageType;

    /**
     * @return the messageType
     */
    public MessageType getMessageType() {
        return messageType;
    }
}
