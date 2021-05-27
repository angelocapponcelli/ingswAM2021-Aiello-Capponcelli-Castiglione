package it.polimi.ingsw.networking.messages.serverMessage;

import it.polimi.ingsw.server.model.specialAbilities.SpecialAbility;

public class UpdateActivatedLeaderCardMessage extends ServerMessage {
    private final String nickname;
    private final SpecialAbility specialAbility;

    public UpdateActivatedLeaderCardMessage(String nickname, SpecialAbility specialAbility) {
        this.nickname = nickname;
        this.specialAbility = specialAbility;
    }

    public String getNickname() {
        return nickname;
    }

    public SpecialAbility getSpecialAbility() {
        return specialAbility;
    }
}
