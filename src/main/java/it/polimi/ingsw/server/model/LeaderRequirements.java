package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.interfaces.Checkable;
import it.polimi.ingsw.server.model.interfaces.Requirement;
import it.polimi.ingsw.server.model.resources.ConcreteResource;
import it.polimi.ingsw.server.model.resources.Resource;

import java.util.HashMap;
import java.util.Map;

public class LeaderRequirements implements Checkable {
    private final Map<Requirement, Integer> leaderRequirements;

    public LeaderRequirements() {
        this.leaderRequirements = new HashMap<>();
    }

    public void add(Requirement requirement, Integer multiplicity) {
        this.leaderRequirements.put(requirement,multiplicity);
    }


    /**
     * todo test if run correctly !
     * Check if realPlayer has requirements
     * @param realPlayer The player on which perform the checking.
     * @return true if realPlayer has requirements otherwise false
     */

    @Override
    public boolean check(RealPlayer realPlayer) {
        int countCard;
        for (Map.Entry<Requirement, Integer> entry : leaderRequirements.entrySet()) {
            if (entry.getKey() instanceof ConcreteResource) {
                if (entry.getValue() > realPlayer.getPersonalBoard().getResourceCount((Resource) entry.getKey()))
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
                if  (countCard > 0) return false;
            }
        }
        return true;
    }
}
