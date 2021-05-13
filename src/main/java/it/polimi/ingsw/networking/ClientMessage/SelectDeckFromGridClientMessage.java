package it.polimi.ingsw.networking.ClientMessage;

public class SelectDeckFromGridClientMessage extends ClientMessage {
    private Integer row;
    private Integer column;

    public SelectDeckFromGridClientMessage(String nickname, Integer row, Integer column) {
        super(nickname);
        this.row = row;
        this.column = column;
    }

    @Override
    public Boolean check() {
        if (this.column < 0 || this.column > 3) {
            return false;
        }
        if (this.row < 0 || this.row > 2) {
            return false;
        } else
            return true;
    }

    public Integer getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }
}
