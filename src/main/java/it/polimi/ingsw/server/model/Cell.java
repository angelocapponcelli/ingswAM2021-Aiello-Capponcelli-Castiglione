package it.polimi.ingsw.server.model;

abstract class Cell {
    private Integer victoryPoint;
    /** optional vaticanreportsection*/


    public Cell (Integer victoryPoint /**optional vaticanreportsection*/){
        this.victoryPoint= victoryPoint;
        /**optional victoryreportsection*/
    }

    public Integer getVictoryPoint() {
        return victoryPoint;
    }

    /** to do get vaticanreportsection*/

    public void OnOccupy(Player player){
        /** it can be used to notify and update the view*/
    }



}
