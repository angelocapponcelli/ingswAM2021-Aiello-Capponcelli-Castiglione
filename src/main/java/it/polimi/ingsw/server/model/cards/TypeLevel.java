package it.polimi.ingsw.server.model.cards;

import it.polimi.ingsw.client.view.CLI.CLIColors;
import it.polimi.ingsw.client.view.reducedGameModel.ReducedRequirement;
import it.polimi.ingsw.server.model.interfaces.Requirement;
import it.polimi.ingsw.server.model.misc.Colors;

import java.io.Serializable;

/**
 * Type and the level of development cards.
 */
public class TypeLevel implements Requirement, ReducedRequirement, Serializable {
    private final Colors type;
    private final int level;

    /**
     * Class constructor.
     * @param type the color of the banner on the card
     * @param level of the card
     */

    public TypeLevel(Colors type, int level) {
        this.type = type;
        this.level = level;
    }

    /**
     * @return The type of the card.
     */
    public Colors getType() {
        return type;
    }

    /**
     * @return The level of the card.
     */
    public int getLevel() {
        return level;
    }

    @Override
    public String toCLI() {
        return type.getColor()+"█"+level+CLIColors.getAnsiReset();
    }
}
