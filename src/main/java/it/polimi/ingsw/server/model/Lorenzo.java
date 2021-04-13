package it.polimi.ingsw.server.model;

public class Lorenzo extends Player{

    public Lorenzo (String name, Integer faithPosition, VaticanReportStatus vaticanReportStatus){
        super(name, faithPosition, vaticanReportStatus);
    }

    public void setName(){
        this.name = "LORENZO";
    }
}
