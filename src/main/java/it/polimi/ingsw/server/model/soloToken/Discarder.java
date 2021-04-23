package it.polimi.ingsw.server.model.soloToken;

import it.polimi.ingsw.server.model.Colors;
import it.polimi.ingsw.server.model.Lorenzo;
import it.polimi.ingsw.server.model.interfaces.Revealable;

public class Discarder implements Revealable {
    private final Colors type;

    public Discarder(Colors type) {
        this.type = type;
    }


    @Override
    public void onReveal(Lorenzo lorenzo) {
        /*
         * search in deckcardgrid based on type of discarder and do two pop
         *
         */
    }
}
