package it.polimi.ingsw.server.model.player;

import it.polimi.ingsw.server.Server;
import it.polimi.ingsw.server.model.personalBoard.PersonalBoard;

public class RealPlayer extends Player {
    private final PersonalBoard personalBoard;
    private final Integer victoryPoint;
    private Boolean first;


    public RealPlayer(String nickName) {
        super(nickName);
        this.personalBoard = new PersonalBoard();
        this.first = false;
        this.victoryPoint = 0;
    }

    public void setFirst() {
        this.first = true;
    }


    public Integer getVictoryPoint() {
        int sum = 0;
        int tmpResourceCount = 0;
        /* points from leadercard*/
        sum = sum + this.getPersonalBoard().getInHandLeaderCards().getVictoryPoint();
        /*points from developmentcards*/
        sum = sum + this.getPersonalBoard().getPersonalDevelopmentBoard().getVictoryPoint();
        /*sum of the resource then divided by 5. every 5 resources the player is given 1 victory point*/

        sum = sum + (getPersonalBoard().getAllResourceCount() / 5);

        /* points from vaticanReportStatus*/
        sum = sum + this.vaticanReportStatus.getVictoryPoint();
        /* points from the faithPosition*/
        sum = sum + Server.getOnGoingGame().getGlobalBoard().getFaithTrack().getTrack().get(faithPosition).getVictoryPoints();
        return sum;
    }

    public Boolean isFirst() {
        return this.first;
    }

    public PersonalBoard getPersonalBoard() {
        return this.personalBoard;
    }
}
