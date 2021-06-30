package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.server.model.resources.ResourceType;

public class ActivateLeaderCardMessage extends ClientMessage {
    private final int id;

    public ActivateLeaderCardMessage(String nickname, int id) {
        super(nickname);
        messageType = MessageType.ACTIVATE_LEADER_CARD;
        this.id = id;
    }


    public int getId() {
        return id;
    }
}
