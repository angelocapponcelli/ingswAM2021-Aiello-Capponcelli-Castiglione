package it.polimi.ingsw.server.model.player;


import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.globalBoard.faithTrack.FaithTrack;
import it.polimi.ingsw.server.model.globalBoard.faithTrack.VaticanReportSection;

public abstract class Player {

    protected GameController gameController;
    protected String nickName;
    protected Integer faithPosition;
    protected VaticanReportStatus vaticanReportStatus;

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
        FaithTrack.getINSTANCE().getTrack().get(faithPosition).onOccupy(this);
    }

    /**
     * Performs the vatican report
     *
     * @param vaticanReportSection The vatican report section to check
     */
    public void doVaticanReportUpdate(VaticanReportSection vaticanReportSection) {
        if (vaticanReportSection.getCells().contains(FaithTrack.getINSTANCE().getTrack().get(faithPosition))) {
            vaticanReportStatus.flip(vaticanReportSection);
        }
    }

}

