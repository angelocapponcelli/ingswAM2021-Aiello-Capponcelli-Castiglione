package it.polimi.ingsw.networking.messages.serverMessage;

import it.polimi.ingsw.server.model.interfaces.Revealable;

import java.io.Serializable;

public class SoloTokenRevealedServerMessage implements Serializable {
    private final Revealable revealable;

    public SoloTokenRevealedServerMessage(Revealable revealable) {
        this.revealable = revealable;
    }

    public Revealable getRevealable() {
        return revealable;
    }
}
