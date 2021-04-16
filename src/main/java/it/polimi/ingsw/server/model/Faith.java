package it.polimi.ingsw.server.model;


public class Faith implements Takeable, Producible{
    private Colors color;

    public Faith (Colors color) {
        this.color = color;
    }


    public Colors getcolor() { return color; };

    public void setcolor(Colors color) { this.color= color; };

    @Override
    public void onProduction(RealPlayer realPlayer, Integer molteplicity){
        int loop;
        for (loop=0; loop>=molteplicity; loop++){
            realPlayer.increaseFaithPosition();
        }
    }
    @Override
    public void onTaking(RealPlayer realPlayer){
        realPlayer.increaseFaithPosition();
    }

}
