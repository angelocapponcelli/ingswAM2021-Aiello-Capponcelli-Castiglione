package it.polimi.ingsw.server.model;

public class RealPlayer extends Player{
    private PersonalBoard personalBoard;

    private Boolean first;

    private Integer victoryPoint;


    public RealPlayer (String name, Integer faithPosition, VaticanReportStatus vaticanReportStatus,PersonalBoard personalBoard, Boolean first, Integer victoryPoint) {
        super(name,faithPosition,vaticanReportStatus);
        this.personalBoard = personalBoard;
        this.first = first;
        this.victoryPoint = victoryPoint;
    }

    //da completare
    public Integer getVictoryPoint(FaithTrack faithTrack){
        int sum=0;
        int tmpResourceCount=0;
        /* points from leadercard*/
        sum= sum+ this.getPersonalBoard().getInHandLeaderCard().getVictoryPoint();
        /*points from developmentcards*/
        sum= sum+ this.getPersonalBoard().getPersonalDevelopmentBoard().getVictoryPoint();
        /*sum of the resource then divided by 5. every 5 resources the player is given 1 victory point*/
        for(Depot depot: this.getPersonalBoard().getDepotForMarket()){
            tmpResourceCount= tmpResourceCount+ depot.getResourceCount();
        }
        tmpResourceCount= tmpResourceCount+ this.getPersonalBoard().getStrongBoxDepot().getResourceCount();
        sum = sum + (tmpResourceCount / 5);

        /* points from vaticanreportstatus*/
        sum=sum+this.vaticanReportStatus.getVictoryPoint();
        /* points from the faithposition*/
        sum= sum+ faithTrack.getTrack().get(faithPosition).getVictoryPoint();
        return sum;
    }

    public Boolean isFirst(){
        return this.first;
    }

    public PersonalBoard getPersonalBoard(){
        return this.personalBoard;
    }
}
