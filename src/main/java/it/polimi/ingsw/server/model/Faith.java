package it.polimi.ingsw.server.model;


public class Faith implements Takeable, Producible{
    private Colors color;

    public Faith (Colors color) {
        this.color = color;
    }

    public Colors getcolor() { return color; };

    public void setcolor(Colors color) { this.color= color; };

    @Override
    public void onProduction(RealPlayer realPlayer){
        realPlayer.increaseFaithPosition();
    }
    @Override
    public void onTaking(RealPlayer realPlayer){
        realPlayer.increaseFaithPosition();
    }

}
