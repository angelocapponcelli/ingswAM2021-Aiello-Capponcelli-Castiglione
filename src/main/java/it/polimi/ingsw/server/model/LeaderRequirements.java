package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.interfaces.Checkable;
import it.polimi.ingsw.server.model.interfaces.Requirement;

import java.util.Map;

public class LeaderRequirements implements Checkable {
    private final Map<Requirement, Integer> leaderRequirements;

    public LeaderRequirements(Map<Requirement, Integer> leaderRequirements) {
        this.leaderRequirements = leaderRequirements;
    }

    @Override
    public boolean check(RealPlayer realPlayer) {
        return true;
    }
}
