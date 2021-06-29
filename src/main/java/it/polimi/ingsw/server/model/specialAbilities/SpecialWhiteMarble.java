package it.polimi.ingsw.server.model.specialAbilities;

import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Resource;

/**
 * Special White Marble extends Special Ability. It is used when a player want to buy from the Market.
 * The player that has this type of ability activated can transform a white marble in the resource specified by this ability.
 *
 */
public class SpecialWhiteMarble extends SpecialAbility {
    /**
     * Class constructor. Instantiates a new Special White Marble
     * @param resource that can substitute any white marble bought in the Market tray
     */
    public SpecialWhiteMarble(Resource resource) {
        this.resource = resource;
    }

    /**
     * The player that has this ability can exchange every white marble that he takes from the market in the resource specified
     * by this special ability. This method activates this ability
     * @param player that activates this special ability
     */
    @Override
    public void onActivation(RealPlayer player) {
        super.onActivation(player);
    }
}
