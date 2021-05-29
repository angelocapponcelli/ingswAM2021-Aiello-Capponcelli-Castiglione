package it.polimi.ingsw.server.model.player;

import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.server.model.personalBoard.PersonalBoard;
import it.polimi.ingsw.utils.observer.Observer;

public class RealPlayer extends Player {
    private final PersonalBoard personalBoard;
    private final Integer victoryPoint;
    //private Boolean first;


    public RealPlayer(String nickName, GameController gameController) {
        super(nickName, gameController);
        this.personalBoard = new PersonalBoard();
        //this.first = false;
        this.victoryPoint = 0;
    }

    public RealPlayer(String nickName) {
        super(nickName);
        this.personalBoard = new PersonalBoard();
        //this.first = false;
        this.victoryPoint = 0;
    }

    /*public void setFirst() {
        this.first = true;
    }*/


    public Integer getVictoryPoint() {
        int sum = 0;
        /* points from leaderCard*/
        sum = sum + this.getPersonalBoard().getInHandLeaderCards().getVictoryPoint();
        /*points from developmentCards*/
        sum = sum + this.getPersonalBoard().getPersonalDevelopmentBoard().getVictoryPoint();
        /*sum of the resource then divided by 5. every 5 resources the player is given 1 victory point*/

        sum = sum + (getPersonalBoard().getAllResourceCount() / 5);

        /* points from vaticanReportStatus*/
        sum = sum + this.vaticanReportStatus.getVictoryPoint();
        /* points from the faithPosition*/
        sum = sum + gameController.getGameModel().getGlobalBoard().getFaithTrack().getTrack().get(faithPosition).getVictoryPoints();
        return sum;
    }

    /*public Boolean isFirst() {
        return this.first;
    }*/

    public PersonalBoard getPersonalBoard() {
        return this.personalBoard;
    }

    @Override
    public void addObserver(Observer obs) {
        super.addObserver(obs);
        personalBoard.addObserver(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        super.removeObserver(obs);
        personalBoard.addObserver(obs);
    }
}
