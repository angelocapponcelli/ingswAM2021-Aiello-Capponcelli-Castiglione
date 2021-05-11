package it.polimi.ingsw.networking.ServerMessage;

import java.util.HashMap;

public class VaticanReportUpdateServerMessage extends ServerMessage{
    private HashMap<Integer, Boolean> vaticanReportUpdate;

    public VaticanReportUpdateServerMessage(HashMap<Integer,Boolean> vaticanReportUpdate){
        this.vaticanReportUpdate=vaticanReportUpdate;
    }
}
