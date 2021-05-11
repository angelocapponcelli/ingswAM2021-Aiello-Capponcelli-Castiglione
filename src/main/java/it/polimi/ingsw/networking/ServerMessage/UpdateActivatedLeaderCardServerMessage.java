package it.polimi.ingsw.networking.ServerMessage;

import it.polimi.ingsw.server.model.specialAbilities.SpecialAbility;

public class UpdateActivatedLeaderCardServerMessage extends ServerMessage {
    private String nickname;
    private SpecialAbility specialAbility;

    public UpdateActivatedLeaderCardServerMessage(String nickname, SpecialAbility specialAbility){
        this.nickname=nickname;
        this.specialAbility=specialAbility;
    }
}
