package it.polimi.ingsw.networking.ServerMessage;

import it.polimi.ingsw.server.model.globalBoard.faithTrack.FaithTrack;

public class SetUpFaithTrackMessage extends ServerMessage{
    private FaithTrack faithTrack;

    public SetUpFaithTrackMessage(FaithTrack faithTrack){
        this.faithTrack= faithTrack;
    }

    public FaithTrack getFaithTrack() {
        return faithTrack;
    }
}
