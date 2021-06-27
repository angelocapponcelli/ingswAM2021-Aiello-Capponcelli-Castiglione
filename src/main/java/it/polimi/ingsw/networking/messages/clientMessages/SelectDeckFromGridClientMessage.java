package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;

public class SelectDeckFromGridClientMessage extends ClientMessage {
    private final Integer row;
    private final Integer column;

    public SelectDeckFromGridClientMessage(String nickname, Integer row, Integer column) {
        super(nickname);
        this.row = row;
        this.column = column;
        this.messageType = MessageType.SELECT_DECK_FROM_GRID;
    }


    public Integer getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }
}
