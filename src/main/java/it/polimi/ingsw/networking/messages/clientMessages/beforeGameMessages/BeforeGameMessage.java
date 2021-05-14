package it.polimi.ingsw.networking.messages.clientMessages.beforeGameMessages;

import it.polimi.ingsw.networking.messages.Message;


/**
 * Message sent before the creating or joining a game.
 */
public abstract class BeforeGameMessage extends Message {

    public abstract Boolean check();
}
