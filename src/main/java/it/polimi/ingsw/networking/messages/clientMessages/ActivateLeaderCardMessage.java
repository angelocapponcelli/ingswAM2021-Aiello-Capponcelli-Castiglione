package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.server.model.resources.ResourceType;

/**
 * Activate leader card message
 */
public class ActivateLeaderCardMessage extends ClientMessage {
    private final int id;

    /**
     * Class constructor
     * @param nickname the nickname of the real player
     * @param id the id of the card to activate
     */
    public ActivateLeaderCardMessage(String nickname, int id) {
        super(nickname);
        messageType = MessageType.ACTIVATE_LEADER_CARD;
        this.id = id;
    }

    /**
     *
     * @return the id of the card to activate
     */
    public int getId() {
        return id;
    }
}
