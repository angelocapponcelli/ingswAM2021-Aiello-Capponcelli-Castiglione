package it.polimi.ingsw.networking.ServerMessage;

import it.polimi.ingsw.server.model.interfaces.Revealable;

public class SoloTokenRevealedServerMessage extends ServerMessage{
    private Revealable revealable;

    public SoloTokenRevealedServerMessage(Revealable revealable){
        this.revealable=revealable;
    }

    public Revealable getRevealable() {
        return revealable;
    }
}
