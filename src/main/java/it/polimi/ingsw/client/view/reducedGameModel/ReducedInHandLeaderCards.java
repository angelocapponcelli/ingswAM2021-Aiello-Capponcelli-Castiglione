package it.polimi.ingsw.client.view.reducedGameModel;


import java.util.List;

public class ReducedInHandLeaderCards {
    List<Integer> inHandLeaderCards;

    public List<Integer> getInHandLeaderCards() {
        return inHandLeaderCards;
    }

    public void update(List<Integer> leaderCardsIDs){
        inHandLeaderCards = leaderCardsIDs;
    }
}
