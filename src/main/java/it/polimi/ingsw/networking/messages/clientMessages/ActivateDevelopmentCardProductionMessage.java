package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;

/**
 * Activate Dev card Production.
 */
public class ActivateDevelopmentCardProductionMessage extends ClientMessage{
    private final int developmentCardID;
    private final boolean isBaseProductionActivated;
    /**
     * Class constructor
     * @param nickname the nickname of the real player
     * @param developmentCardID id of the card where to find the production power
     */
    public ActivateDevelopmentCardProductionMessage(String nickname, int developmentCardID, boolean isBaseProductionActivated) {
        super(nickname);
        messageType = MessageType.ACTIVATE_DEV_CARD_PRODUCTION;
        this.developmentCardID = developmentCardID;
        this.isBaseProductionActivated = isBaseProductionActivated;
    }

    /**
     *
     * @return id of the card
     */
    public int getDevelopmentCardID() {
        return developmentCardID;
    }

    public boolean isBaseProductionActivated() {
        return isBaseProductionActivated;
    }
}
