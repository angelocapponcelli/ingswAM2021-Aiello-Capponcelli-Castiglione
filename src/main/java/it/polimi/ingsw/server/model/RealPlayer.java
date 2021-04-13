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
    public Integer getVictoryPoint(){
        int sum=0; /** i'll delete the initialitation later

        //chiama i depositi
        //chiama il vatican status
        //chiama la cella
        //chiama le carte leader
        //chiama la development board
        //fa somma sum=1+2+...**/
        return sum;
    }

    public Boolean isFirst(){
        return this.first;
    }

    public PersonalBoard getPersonalBoard(){
        return this.personalBoard;
    }
}
