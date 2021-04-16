package it.polimi.ingsw.server.model;

public class PopeSpaceCell extends Cell{

    private Boolean alreadyOccupied;

    /**
     * optional vaticanreportsection has to be put
     */
    public PopeSpaceCell(Integer victoryPoint, Boolean alreadyOccupied) {
        super(victoryPoint);
        this.alreadyOccupied = alreadyOccupied;
    }

    public void setAlreadyOccupied(){
        this.alreadyOccupied= true;
    }
}
