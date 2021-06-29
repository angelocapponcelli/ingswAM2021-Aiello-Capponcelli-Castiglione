package it.polimi.ingsw.server.model.cards;

import it.polimi.ingsw.server.model.interfaces.Checkable;
import it.polimi.ingsw.server.model.interfaces.Requirement;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.ConcreteResource;

import java.util.HashMap;
import java.util.Map;

/**
 * Class useful to check if a player has LeaderRequirement
 * This class define them and provide a check method
 */
public class LeaderRequirements implements Checkable {
    private final Map<Requirement, Integer> leaderRequirements;

    /**
     * Class constructor.
     */

    public LeaderRequirements() {
        this.leaderRequirements = new HashMap<>();
    }

    /**
     * Gets leader requirements of this card.
     * @return leaderRequirements
     */

    public Map<Requirement, Integer> getLeaderRequirements() {
        return leaderRequirements;
    }

    /**
     * Adds requirements to the map of this class.
     * @param requirement
     * @param multiplicity
     */

    public void add(Requirement requirement, Integer multiplicity) {
        if (multiplicity > 0)
            this.leaderRequirements.put(requirement, multiplicity);
        //else ;//Need to launch exception (?) or we can simply ignore add request
    }


    /**
     * Check if realPlayer has requirements
     *
     * @param realPlayer The player on which perform the checking.
     * @return true if realPlayer has requirements otherwise false
     */

    @Override
    public boolean check(RealPlayer realPlayer) {
        int countCard;
        for (Map.Entry<Requirement, Integer> entry : leaderRequirements.entrySet()) {
            if (entry.getKey() instanceof ConcreteResource) {
                if (entry.getValue() > realPlayer.getPersonalBoard().getSpecificResourceCount(((ConcreteResource) entry.getKey()).getResourceType()))
                    return false;
            }
            if (entry.getKey() instanceof TypeLevel) {
                countCard = entry.getValue();
                for (DevelopmentCard developmentCard : realPlayer.getPersonalBoard().getPersonalDevelopmentBoard().getALlCards()) {
                    if (developmentCard.getTypeLevel().getType() == ((TypeLevel) entry.getKey()).getType() &&
                            ((((TypeLevel) entry.getKey()).getLevel() == 0) ||
                                    ((TypeLevel) entry.getKey()).getLevel() == developmentCard.getTypeLevel().getLevel()))
                        countCard--;
                    if (countCard == 0) break;
                }
                if (countCard > 0) return false;
            }
        }
        return true;
    }
}
