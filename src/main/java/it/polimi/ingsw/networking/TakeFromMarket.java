package it.polimi.ingsw.networking;

import it.polimi.ingsw.server.model.player.RealPlayer;

public class TakeFromMarket extends Message{

    private String rowOrColumn;
    private Integer number;

    public TakeFromMarket(RealPlayer realPlayer1, String rowOrColumn, Integer number) {
        super(realPlayer1);
        this.rowOrColumn=rowOrColumn;
        this.number=number;
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
