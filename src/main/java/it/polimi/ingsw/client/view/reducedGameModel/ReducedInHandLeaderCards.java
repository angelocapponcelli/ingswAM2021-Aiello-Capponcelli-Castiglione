package it.polimi.ingsw.client.view.reducedGameModel;


import java.util.List;

public class ReducedInHandLeaderCards {
    List<ReducedLeaderCard> inHandLeaderCards;

    public void update(List<ReducedLeaderCard> leaderCards) {
        inHandLeaderCards = leaderCards;
    }

    public List<ReducedLeaderCard> getInHandLeaderCards() {
        return inHandLeaderCards;
    }
}
