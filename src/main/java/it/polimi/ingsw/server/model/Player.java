package it.polimi.ingsw.server.model;

//TODO: all of the class

abstract class Player {
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

    /** public VaticanReportStatus getVaticanReportStatus(VaticanReportSection vaticanReportSection) {
        search in this.vaticanStatus vaticanreportsection and return its boolean value

    }*/

    public void doVaticanReportUpdate (VaticanReportSection vaticanReportSection){
        /** to do*/
    }

    public void increaseFaithPosition(){
        this.faithPosition= this.faithPosition+1;
    }

}

