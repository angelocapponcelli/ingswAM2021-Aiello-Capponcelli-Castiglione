package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;

public class TakeFromMarketMessage extends ClientMessage {

    private final String rowOrColumn;
    private final Integer number;

    public TakeFromMarketMessage(String nickname, String rowOrColumn, Integer number) {
        super(nickname);
        messageType = MessageType.TAKE_FROM_MARKET;
        this.rowOrColumn = rowOrColumn;
        this.number = number;
    }

    public String getRowOrColumn() {
        return rowOrColumn;
    }

    public Integer getNumber() {
        return number;
    }
}
