package it.polimi.ingsw.server.model.specialAbilities;

import it.polimi.ingsw.client.view.reducedGameModel.SpecialAbilityType;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Resource;

/**
 * It's one of the four abilities. It guarantees a discount on a certain resource when it is the moment of payment
 */
public class SpecialDiscount extends SpecialAbility {

    private final int discount;

    /**
     * Class constructor.
     * @param resource the resource on which it is applied the discount
     * @param discount the quantity of the discount
     */
    public SpecialDiscount(Resource resource, int discount) {
        specialAbilityType = SpecialAbilityType.DISCOUNT;
        this.resource = resource;
        this.discount = discount;
    }

    /**
     * It's a passive ability, should consider It in the pay transaction
     */
    @Override
    public void onActivation(RealPlayer player) {
        super.onActivation(player);
    }

    /**
     * @return resource
     */
    public Resource getResource() {
        return resource;
    }
}
