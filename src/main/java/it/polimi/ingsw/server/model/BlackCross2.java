package it.polimi.ingsw.server.model;

public class BlackCross2 implements Revealable {

    @Override
    public void onReveal(Lorenzo lorenzo){
        lorenzo.increaseFaithPosition();
        lorenzo.increaseFaithPosition();
        /** it will be always lorenzo**/
    }

}
