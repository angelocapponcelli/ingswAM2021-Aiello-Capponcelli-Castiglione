package it.polimi.ingsw.networking.ServerMessage;

import it.polimi.ingsw.server.model.globalBoard.faithTrack.FaithTrack;

public class FaithTrackServerMessage extends ServerMessage{
    private FaithTrack faithTrack;

    public FaithTrackServerMessage(FaithTrack faithTrack){
        this.faithTrack= faithTrack;
    }

    public FaithTrack getFaithTrack() {
        return faithTrack;
    }
}
