package it.polimi.ingsw.server.model;

import java.util.Map;

/**
 * to do
 */

public class VaticanReportStatus {
    private Map<VaticanReportSection, Boolean> vaticanReportStatus;

    public VaticanReportStatus(Map<VaticanReportSection, Boolean> vaticanReportStatus){
        this.vaticanReportStatus = vaticanReportStatus;
    }

    public Integer getVictoryPoint(){
        int tmpvictoryPoints=0;
        /** for each victoryreportsection getvictorypoint add to victorypoints if the value in the map is true
         *
         */
        return tmpvictoryPoints;
    }

    public void flip(VaticanReportSection vaticanReportSection){
        /** search victorysection and set true*/
    }

    public void remove(VaticanReportSection vaticanReportSection){
        /** search VaticanSection and delete it from the map*/
    }
}
