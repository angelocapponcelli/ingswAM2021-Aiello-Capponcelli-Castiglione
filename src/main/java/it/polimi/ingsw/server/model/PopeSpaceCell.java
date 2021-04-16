package it.polimi.ingsw.server.model;

public class PopeSpaceCell extends Cell{

    private Boolean alreadyOccupied;

    /**
     * optional vaticanreportsection has to be put
     */
    public PopeSpaceCell(Integer victoryPoint) {
        super(victoryPoint);
        this.alreadyOccupied = false;
    }

    public void setAlreadyOccupied(){
        this.alreadyOccupied= true;
    }
}
