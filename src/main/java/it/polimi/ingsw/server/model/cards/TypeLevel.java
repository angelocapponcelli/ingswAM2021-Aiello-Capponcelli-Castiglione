package it.polimi.ingsw.server.model.cards;

import it.polimi.ingsw.server.model.interfaces.Requirement;
import it.polimi.ingsw.server.model.misc.Colors;

/**
 * Type and the level of development cards.
 */
public class TypeLevel implements Requirement {
    private final Colors type;
    private final int level;

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
}
