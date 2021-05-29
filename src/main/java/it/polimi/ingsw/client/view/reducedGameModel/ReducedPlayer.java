package it.polimi.ingsw.client.view.reducedGameModel;

import it.polimi.ingsw.server.model.misc.Colors;

public class ReducedPlayer {
    private int faithPosition;
    private Colors playerColor;
    private String nickName;
    private Integer turnPosition;

    public ReducedPlayer() {
    }

    public Integer getTurnPosition() {
        return turnPosition;
    }

    public void setTurnPosition(Integer turnPosition) {
        this.turnPosition = turnPosition;
    }

    public void setFaithPosition(int faithPosition) {
        this.faithPosition = faithPosition;
    }

    public void setPlayerColor(Colors playerColor) {
        this.playerColor = playerColor;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
