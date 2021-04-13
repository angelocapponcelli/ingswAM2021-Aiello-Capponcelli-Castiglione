package it.polimi.ingsw.server.model;


public class Faith implements Takeable, Producible{
    private Colors color;

    public Faith (Colors color) {
        this.color = color;
    }

    public Colors getcolor() { return color; };

    public void setcolor(Colors color) { this.color= color; };

    @Override
    public void onProduction(Player player){
        player.increaseFaithPosition();
    }
    @Override
    public void onTaking(Player player){
        player.increaseFaithPosition();
    }

}
