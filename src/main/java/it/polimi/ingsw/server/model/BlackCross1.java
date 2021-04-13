package it.polimi.ingsw.server.model;

public class BlackCross1 implements Revealable{

    @Override
    public void onReveal(Lorenzo lorenzo){
        lorenzo.increaseFaithPosition(); /** it will be always Lorenzo**/
        /**
         * rimescolare tutti i solotoken
         */
    }

}
