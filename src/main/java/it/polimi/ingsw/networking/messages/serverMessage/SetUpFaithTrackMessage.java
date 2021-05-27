package it.polimi.ingsw.networking.messages.serverMessage;

import it.polimi.ingsw.server.model.globalBoard.faithTrack.FaithTrack;

public class SetUpFaithTrackMessage extends ServerMessage {
    private final FaithTrack faithTrack;

    public SetUpFaithTrackMessage(FaithTrack faithTrack) {
        this.faithTrack = faithTrack;
    }

    public FaithTrack getFaithTrack() {
        return faithTrack;
    }
}
