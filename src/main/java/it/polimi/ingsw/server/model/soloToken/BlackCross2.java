package it.polimi.ingsw.server.model.soloToken;

import it.polimi.ingsw.server.model.Lorenzo;
import it.polimi.ingsw.server.model.interfaces.Revealable;

public class BlackCross2 implements Revealable {

    @Override
    public void onReveal(Lorenzo lorenzo){
        lorenzo.increaseFaithPosition();
        lorenzo.increaseFaithPosition();
        /** it will be always lorenzo**/
    }

}
