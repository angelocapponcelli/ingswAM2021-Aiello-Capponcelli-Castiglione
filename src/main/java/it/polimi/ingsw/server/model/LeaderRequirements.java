package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.interfaces.Checkable;
import it.polimi.ingsw.server.model.interfaces.Requirement;

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


    @Override
    public boolean check(RealPlayer realPlayer) {
        return true;
    }
}
