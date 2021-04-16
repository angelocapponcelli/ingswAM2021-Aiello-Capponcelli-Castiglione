package it.polimi.ingsw.server.model;

import java.util.Map;

public class LeaderRequirements implements Checkable{
    private Map<Requirement,Integer> leaderRequirements;

    public LeaderRequirements(Map<Requirement, Integer> leaderRequirements) {
        this.leaderRequirements = leaderRequirements;
    }

    @Override
    public boolean check(RealPlayer realPlayer) {
        return true;
    }
}
