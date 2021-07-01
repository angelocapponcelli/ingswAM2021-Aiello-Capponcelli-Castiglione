package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;

/**
 * Activate Dev card Production.
 */
public class ActivateDevelopmentCardProductionMessage extends ClientMessage{
    private final int developmentCardID;

    /**
     * Class constructor
     * @param nickname the nickname of the real player
     * @param developmentCardID id of the card where to find the production power
     */
    public ActivateDevelopmentCardProductionMessage(String nickname, int developmentCardID) {
        super(nickname);
        messageType = MessageType.ACTIVATE_DEV_CARD_PRODUCTION;
        this.developmentCardID = developmentCardID;
    }

    /**
     *
     * @return id of the card
     */
    public int getDevelopmentCardID() {
        return developmentCardID;
    }
}
