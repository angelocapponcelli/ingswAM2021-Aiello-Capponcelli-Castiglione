package it.polimi.ingsw.server.model.soloToken;

import it.polimi.ingsw.server.model.interfaces.Revealable;
import it.polimi.ingsw.server.model.player.Lorenzo;

/**
 * BlackCross2 solo token. Implements revealable
 */
public class BlackCross2 implements Revealable {
    /**
     * Increases lorenzo's faith position two times.
     * @param lorenzo The "AI" player.
     */
    @Override
    public void onReveal(Lorenzo lorenzo) {
        lorenzo.increaseFaithPosition();
        lorenzo.increaseFaithPosition();
        /* it will be always lorenzo**/
    }

}
