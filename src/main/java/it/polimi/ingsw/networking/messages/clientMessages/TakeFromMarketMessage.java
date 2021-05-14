package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;

public class TakeFromMarketMessage extends ClientMessage {

    private String rowOrColumn;
    private Integer number;

    public TakeFromMarketMessage(String nickname, String rowOrColumn, Integer number) {
        super(nickname);
        messageType= MessageType.TAKE_FROM_MARKET;
        this.rowOrColumn = rowOrColumn;
        this.number = number;
    }

    @Override
    public Boolean check() {
        if (this.rowOrColumn != "row" || this.rowOrColumn != "column") {
            return false;
        }
        if (this.rowOrColumn == "row") {
            if (this.number < 0 || this.number > 2) {
                return false;
            }
        }
        if (this.rowOrColumn == "column") {
            return this.number >= 0 && this.number <= 3;
        }
        return true;
    }

    public String getRowOrColumn() {
        return rowOrColumn;
    }

    public Integer getNumber() {
        return number;
    }
}
