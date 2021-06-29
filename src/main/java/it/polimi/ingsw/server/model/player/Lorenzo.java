package it.polimi.ingsw.server.model.player;

import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.server.model.interfaces.Revealable;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.soloToken.BlackCross1;
import it.polimi.ingsw.server.model.soloToken.BlackCross2;
import it.polimi.ingsw.server.model.soloToken.Discarder;

import java.util.*;

/**
 * Lorenzo is the cpu player. It is present only in the single player game mode
 */
public class Lorenzo extends Player {

    private final List<Revealable> soloTokenDeck = Arrays.asList(
            new BlackCross1(),
            new BlackCross2(),
            new BlackCross1(),
            new Discarder(Colors.YELLOW),
            new Discarder(Colors.BLUE),
            new Discarder(Colors.PURPLE),
            new Discarder(Colors.GREEN)
    );

    /**
     * Class constructor. Instantiate a new Lorenzo
     * @param gameController the controller which is referred to the game played
     */
    public Lorenzo(GameController gameController){
        super("Lorenzo",gameController);
        this.gameController = gameController;
        shuffleDeck();
    }

    /**
     * Reveals one solo token and performs the action of that token
     */
    public void reveal() {
        Revealable revealable = soloTokenDeck.get(0);
        Collections.rotate(soloTokenDeck, -1);
        revealable.onReveal(this);

    }

    /**
     * Shuffles the solo token deck
     */
    public void shuffleDeck() {
        Collections.shuffle(soloTokenDeck);
    }
}
