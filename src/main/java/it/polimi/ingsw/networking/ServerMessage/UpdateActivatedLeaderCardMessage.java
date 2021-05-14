package it.polimi.ingsw.networking.ServerMessage;

import it.polimi.ingsw.server.model.specialAbilities.SpecialAbility;

public class UpdateActivatedLeaderCardMessage extends ServerMessage {
    private String nickname;
    private SpecialAbility specialAbility;

    public UpdateActivatedLeaderCardMessage(String nickname, SpecialAbility specialAbility){
        this.nickname=nickname;
        this.specialAbility=specialAbility;
    }

    public String getNickname() {
        return nickname;
    }

    public SpecialAbility getSpecialAbility() {
        return specialAbility;
    }
}
