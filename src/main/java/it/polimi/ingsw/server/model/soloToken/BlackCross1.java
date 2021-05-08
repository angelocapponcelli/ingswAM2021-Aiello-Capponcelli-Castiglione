package it.polimi.ingsw.server.model.soloToken;

import it.polimi.ingsw.server.model.interfaces.Revealable;
import it.polimi.ingsw.server.model.player.Lorenzo;

public class BlackCross1 implements Revealable {

    @Override
    public void onReveal(Lorenzo lorenzo) {
        lorenzo.increaseFaithPosition(); /*it will be always Lorenzo*/
        lorenzo.setSoloTokenDeck();
    }

}
