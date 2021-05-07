package it.polimi.ingsw.server.model.player;

import it.polimi.ingsw.server.model.interfaces.Revealable;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.soloToken.BlackCross1;
import it.polimi.ingsw.server.model.soloToken.BlackCross2;
import it.polimi.ingsw.server.model.soloToken.Discarder;

import java.util.ArrayList;
import java.util.Random;

public class Lorenzo extends Player {

    private ArrayList<Revealable> soloTokenDeck;

    public Lorenzo(String name) {
        super(name);
        this.soloTokenDeck = new ArrayList<>();
    }

    public Revealable reveal() {
        Random random = new Random();
        return this.soloTokenDeck.get(random.nextInt(this.soloTokenDeck.size()));
    }

    // don't know if it is unnecessary*/
    public void removeFromSoloTokenDeck(Revealable revealable) {
        this.soloTokenDeck.remove(revealable);
    }

    public void setSoloTokenDeck() {
        BlackCross1 blackCross1 = new BlackCross1();
        BlackCross2 blackCross2 = new BlackCross2();
        BlackCross1 blackCross11 = new BlackCross1();
        Discarder discarderY = new Discarder(Colors.YELLOW);
        Discarder discarderB = new Discarder(Colors.BLUE);
        Discarder discarderP = new Discarder(Colors.PURPLE);
        Discarder discarderG = new Discarder(Colors.GREEN);
        ArrayList<Revealable> soloToken = new ArrayList<>();
        soloToken.add(blackCross1);
        soloToken.add(blackCross2);
        soloToken.add(blackCross11);
        soloToken.add(discarderB);
        soloToken.add(discarderG);
        soloToken.add(discarderP);
        soloToken.add(discarderY);
        this.soloTokenDeck = soloToken;
    }

    public ArrayList<Revealable> getSoloTokenDeck() {
        return this.soloTokenDeck;
    }
}
