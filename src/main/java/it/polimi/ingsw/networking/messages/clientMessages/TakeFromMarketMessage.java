package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;

/**
 * Take from Market Message
 */
public class TakeFromMarketMessage extends ClientMessage {

    private final String rowOrColumn;
    private final Integer number;

    /**
     * Class constructor
     * @param nickname the nickname of the real player
     * @param rowOrColumn the string that define if it's a row or a column
     * @param number the number of row or column
     */
    public TakeFromMarketMessage(String nickname, String rowOrColumn, Integer number) {
        super(nickname);
        messageType = MessageType.TAKE_FROM_MARKET;
        this.rowOrColumn = rowOrColumn;
        this.number = number;
    }

    /**
     *
     * @return the string that define if it's a row or a column
     */
    public String getRowOrColumn() {
        return rowOrColumn;
    }

    /**
     *
     * @return the number of the row or column
     */
    public Integer getNumber() {
        return number;
    }
}
