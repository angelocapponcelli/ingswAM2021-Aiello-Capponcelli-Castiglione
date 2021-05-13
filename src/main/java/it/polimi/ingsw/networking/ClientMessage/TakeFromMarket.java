package it.polimi.ingsw.networking.ClientMessage;

public class TakeFromMarket extends ClientMessage {

    private String rowOrColumn;
    private Integer number;

    public TakeFromMarket(String nickname, String rowOrColumn, Integer number) {
        super(nickname);
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
            if (this.number < 0 || this.number > 3) {
                return false;
            }
        }
        return true;
    }
}
