package it.polimi.ingsw.networking.messages.serverMessage;

import it.polimi.ingsw.networking.messages.MessageType;

public class ItIsMyTurnMessage extends ServerMessage{

    private final Boolean canPlayLeaderCard;

    public ItIsMyTurnMessage(Boolean canPlayLeaderCard) {
        messageType = MessageType.MY_TURN_MESSAGE;
        this.canPlayLeaderCard = canPlayLeaderCard;
    }

    public Boolean getCanPlayLeaderCard() {
        return canPlayLeaderCard;
    }
}
