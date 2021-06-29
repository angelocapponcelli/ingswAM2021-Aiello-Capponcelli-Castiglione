package it.polimi.ingsw.server.model.player;

import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.server.model.globalBoard.GlobalBoard;
import it.polimi.ingsw.server.model.interfaces.Revealable;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.soloToken.BlackCross1;
import it.polimi.ingsw.server.model.soloToken.BlackCross2;
import it.polimi.ingsw.server.model.soloToken.Discarder;

import java.util.ArrayList;
import java.util.Random;

/**
 * Lorenzo is the AI player that it is used only during Single player games
 */
public class Lorenzo extends Player {

    private ArrayList<Revealable> soloTokenDeck;

    /**
     * Class constructor. Instantiates a new Lorenzo. It also creates a list of solo Token
     * @param name of the player. Usually is 'Lorenzo'
     * @param gameController the controller of the single player game
     */
    public Lorenzo(String name, GameController gameController) {
        super(name, gameController);
        this.soloTokenDeck = new ArrayList<>();
    }

    /**
     * Class constructor. Instantiates a new Lorenzo. It also creates a list of solo Token
     * @param name of the player. Usually is 'Lorenzo'
     */
    public Lorenzo(String name) {
        super(name);
        this.soloTokenDeck = new ArrayList<>();
    }

    /**
     * Reveals the top solo token of the list
     * @return solo token
     */
    public Revealable reveal() {
        Random random = new Random();
        return this.soloTokenDeck.get(random.nextInt(this.soloTokenDeck.size()));
    }

    /**
     * Removes a solo token from the solo token deck
     * @param revealable the solo token that has to be discard
     */
    public void removeFromSoloTokenDeck(Revealable revealable) {
        this.soloTokenDeck.remove(revealable);
    }

    /**
     * Sets the solo token deck. It is used also when a BlackCross1 soloToken is revealed so the deck has to be refilled
     * with the solo token that have been discarded before
     */
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

    /**
     * Gets the solo token deck
     * @return solo token list
     */
    public ArrayList<Revealable> getSoloTokenDeck() {
        return this.soloTokenDeck;
    }
}
