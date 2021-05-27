package it.polimi.ingsw.client.view.reducedGameModel;

import it.polimi.ingsw.server.model.cards.LeaderCard;
import it.polimi.ingsw.server.model.cards.TypeLevel;
import it.polimi.ingsw.server.model.interfaces.Requirement;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.resources.*;
import it.polimi.ingsw.server.model.specialAbilities.SpecialAdditionalProductionPower;
import it.polimi.ingsw.server.model.specialAbilities.SpecialDiscount;
import it.polimi.ingsw.server.model.specialAbilities.SpecialExtraDepot;

import java.util.HashMap;
import java.util.Map;

public class ReducedPlayer {
    private int position;
    private Colors playerColor;
    private String nickName;

    public ReducedPlayer() {
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setPlayerColor(Colors playerColor) {
        this.playerColor = playerColor;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
