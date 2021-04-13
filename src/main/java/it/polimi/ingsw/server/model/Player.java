package it.polimi.ingsw.server.model;

//TODO: all of the class

public class Player {
    protected String name;
    protected Integer faithPosition;
    protected VaticanReportStatus vaticanReportStatus;

    public Player (String name, Integer faithPosition, VaticanReportStatus vaticanReportStatus){
        this.name = name;
        this.faithPosition= faithPosition;
        this.vaticanReportStatus= vaticanReportStatus;
    }
    public String getName() { return name; }

    public Integer getFaithPosition() {
        return faithPosition;
    }
//da fare getvaticanreportstatus
//da fare dovaticanreportupdate

    public void increaseFaithPosition(){
        this.faithPosition= this.faithPosition+1;
    }

}

