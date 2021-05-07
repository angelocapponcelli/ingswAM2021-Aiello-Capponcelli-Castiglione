package it.polimi.ingsw.server.model.soloToken;

import it.polimi.ingsw.server.model.interfaces.Revealable;
import it.polimi.ingsw.server.model.player.Lorenzo;

public class BlackCross2 implements Revealable {

    @Override
    public void onReveal(Lorenzo lorenzo) {
        lorenzo.increaseFaithPosition();
        lorenzo.increaseFaithPosition();
        /* it will be always lorenzo**/
    }

}
