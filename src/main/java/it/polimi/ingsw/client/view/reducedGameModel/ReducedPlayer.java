package it.polimi.ingsw.client.view.reducedGameModel;

import it.polimi.ingsw.client.view.reducedGameModel.reducedFaithTrack.ReducedVaticanReportSection;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.player.Player;

import java.io.Serializable;
import java.util.List;

public class ReducedPlayer implements Serializable {
    private int faithPosition;
    private Colors playerColor;
    private String nickName;
    private Integer turnPosition;
    private List<ReducedVaticanReportSection> vaticanReportSections;

    public ReducedPlayer(Player player) {
        this.faithPosition = player.getFaithPosition();
        this.nickName = player.getNickName();
    }

    public Integer getTurnPosition() {
        return turnPosition;
    }
    public void setTurnPosition(Integer turnPosition) {
        this.turnPosition = turnPosition;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getNickName() {
        return nickName;
    }

    public int getFaithPosition() {
        return faithPosition;
    }
    public void setFaithPosition(int faithPosition) {
        this.faithPosition = faithPosition;
    }

    public void setPlayerColor(Colors playerColor) {
        this.playerColor = playerColor;
    }
    public Colors getPlayerColor() {
        return playerColor;
    }


}
