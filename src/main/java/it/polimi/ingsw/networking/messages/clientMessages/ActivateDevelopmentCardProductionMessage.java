package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;

public class ActivateDevelopmentCardProductionMessage extends ClientMessage{
    private final int developmentCardID;

    public ActivateDevelopmentCardProductionMessage(String nickname, int developmentCardID) {
        super(nickname);
        messageType = MessageType.ACTIVATE_DEV_CARD_PRODUCTION;
        this.developmentCardID = developmentCardID;
    }

    public int getDevelopmentCardID() {
        return developmentCardID;
    }
}
