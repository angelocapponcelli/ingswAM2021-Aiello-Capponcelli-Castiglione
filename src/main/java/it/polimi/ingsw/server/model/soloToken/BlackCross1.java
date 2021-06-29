package it.polimi.ingsw.server.model.soloToken;

import it.polimi.ingsw.server.model.interfaces.Revealable;
import it.polimi.ingsw.server.model.player.Lorenzo;

/**
 * BlackCross1 solo Token. Implements Revealable.
 */
public class BlackCross1 implements Revealable {
    /**
     * Increases the faith position of lorenzo and it sets the solo token deck.
     * @param lorenzo The "AI" player.
     */
    @Override
    public void onReveal(Lorenzo lorenzo) {
        lorenzo.increaseFaithPosition(); /*it will be always Lorenzo*/
        lorenzo.setSoloTokenDeck();
    }

}
