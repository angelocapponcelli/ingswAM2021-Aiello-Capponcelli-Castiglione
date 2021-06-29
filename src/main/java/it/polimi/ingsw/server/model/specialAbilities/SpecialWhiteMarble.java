package it.polimi.ingsw.server.model.specialAbilities;

import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Resource;

/**
 * It's one of the four abilities. If activated it permits the player to exchange a white marble in the resource
 * specified
 */
public class SpecialWhiteMarble extends SpecialAbility {
    private final int multiplicity;

    /**
     * Class constructor.
     * @param resource the resource in which the player can change the white marble
     * @param multiplicity
     */
    public SpecialWhiteMarble(Resource resource, int multiplicity) {
        this.resource = resource;
        this.multiplicity = multiplicity;
    }

    /**
     * Enables this ability
     * @param player that activates this special ability
     */
    @Override
    public void onActivation(RealPlayer player) {
        super.onActivation(player);
    }
}
