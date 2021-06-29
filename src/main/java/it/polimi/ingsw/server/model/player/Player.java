package it.polimi.ingsw.server.model.player;


import it.polimi.ingsw.networking.messages.serverMessage.TurnPositionMessage;
import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.server.model.globalBoard.faithTrack.VaticanReportSection;
import it.polimi.ingsw.utils.observer.Observable;
import it.polimi.ingsw.utils.observer.Observer;

/**
 * Player is an abstract class. there can be two types of player: the real player and Lorenzo.
 */
public abstract class Player extends Observable {

    protected GameController gameController;
    protected final String nickName;
    protected Integer faithPosition;
    protected VaticanReportStatus vaticanReportStatus;
    protected Integer turnPosition;

    /**
     * Class constructor.
     * @param nickName of the player
     * @param gameController of the game in which he plays
     */
    public Player(String nickName, GameController gameController) {
        this.nickName = nickName;
        this.faithPosition = 0;
        this.vaticanReportStatus = new VaticanReportStatus();
        this.gameController = gameController;
    }

    /**
     * Class constructor
     * @param nickName of the player
     */
    public Player(String nickName) {
        this.nickName = nickName;
        this.faithPosition = 0;
        this.vaticanReportStatus = new VaticanReportStatus();
    }

    /**
     * Gets the game controller
     * @return game controller
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Gets nickname
     * @return nickname
     */
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
     * Sets the faith position
     * @param faithPosition the position of the player on the faith track
     */
    public void setFaithPosition(Integer faithPosition){
        this.faithPosition = faithPosition;
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

    /**
     * Gets turn position
     * @return turn position
     */
    public Integer getTurnPosition() {
        return turnPosition;
    }

    /**
     * Sets turn position
     * @param turnPosition of the player
     */
    public void setTurnPosition(Integer turnPosition) {
        this.turnPosition = turnPosition;
        notifyObserver(new TurnPositionMessage(turnPosition));
    }

    @Override
    public void addObserver(Observer obs) {
        super.addObserver(obs);
        vaticanReportStatus.addObserver(obs);
    }
}

