package it.polimi.ingsw.server.model;


abstract class Player {
    protected String name;
    protected Integer faithPosition;
    protected VaticanReportStatus vaticanReportStatus;

    public Player (String name){
        this.name = name;
        this.faithPosition= 0;
        this.vaticanReportStatus= new VaticanReportStatus();
    }
    public String getName() { return name; }

    /**
     * Gets faith position.
     *
     * @return the faith position
     */
    public Integer getFaithPosition() {
        return faithPosition;
    }

    /**
     * Increase player's faith position by one and calls the onOccupy method
     */
    public void increaseFaithPosition(){
        this.faithPosition++;
        FaithTrack.getINSTANCE().getTrack().get(faithPosition).onOccupy(this);
    }

    /**
     * Performs the vatican report
     *
     * @param vaticanReportSection The vatican report section to check
     */
    public void doVaticanReportUpdate (VaticanReportSection vaticanReportSection){
        if ( vaticanReportSection.getCells().contains( FaithTrack.getINSTANCE().getTrack().get(faithPosition) ) ){
            vaticanReportStatus.flip(vaticanReportSection);
        }
    }

}

