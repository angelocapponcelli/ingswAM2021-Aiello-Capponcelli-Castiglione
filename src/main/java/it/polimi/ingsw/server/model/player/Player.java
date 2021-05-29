package it.polimi.ingsw.server.model.player;


import it.polimi.ingsw.networking.messages.serverMessage.TurnPositionMessage;
import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.server.model.globalBoard.faithTrack.VaticanReportSection;
import it.polimi.ingsw.utils.observer.Observable;
import it.polimi.ingsw.utils.observer.Observer;

public abstract class Player extends Observable {

    protected GameController gameController;
    protected String nickName;
    protected Integer faithPosition;
    protected VaticanReportStatus vaticanReportStatus;
    protected Integer turnPosition;

    public Player(String nickName, GameController gameController) {
        this.nickName = nickName;
        this.faithPosition = 0;
        this.vaticanReportStatus = new VaticanReportStatus();
        this.gameController = gameController;
    }

    public Player(String nickName) {
        this.nickName = nickName;
        this.faithPosition = 0;
        this.vaticanReportStatus = new VaticanReportStatus();
    }

    public GameController getGameController() {
        return gameController;
    }

    public String getNickName() {
        return nickName;
    }

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
    public void increaseFaithPosition() {
        this.faithPosition++;
        gameController.getGameModel().getGlobalBoard().getFaithTrack().getTrack().get(faithPosition).onOccupy(this);
    }

    /**
     * Performs the vatican report
     *
     * @param vaticanReportSection The vatican report section to check
     */
    public void doVaticanReportUpdate(VaticanReportSection vaticanReportSection) {
        if (vaticanReportSection.getCells().contains(gameController.getGameModel().getGlobalBoard().getFaithTrack().getTrack().get(faithPosition))) {
            vaticanReportStatus.flip(vaticanReportSection);
        }
    }


    public void setTurnPosition(Integer turnPosition) {
        this.turnPosition = turnPosition;
        notifyObserver(new TurnPositionMessage(turnPosition));
    }

    public Integer getTurnPosition() {
        return turnPosition;
    }

}

