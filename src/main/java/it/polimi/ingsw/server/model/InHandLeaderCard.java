package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.resources.Faith;

import java.util.LinkedList;
import java.util.List;

public class InHandLeaderCard {
    List<LeaderCard> cards;

    public InHandLeaderCard(List<LeaderCard> cards){
        this.cards=cards;
    }

    //public LeaderCard getcard(int)
    public static void remove(LeaderCard leaderCard) {
        /** to do*/
    }
    /** has to be checked*/
    public void discard(LeaderCard leaderCard, RealPlayer realPlayer){
        List<LeaderCard> tmpCards= cards;
        for (LeaderCard leaderCard1: tmpCards){
            if(leaderCard1.equals(leaderCard)){
                InHandLeaderCard.remove(leaderCard);
                //Faith faith= new Faith(Colors.RED);
                //faith.onTaking(realPlayer);
            }
        }
    }

    public List<SpecialAbility> getEnabledAbilities(){
        List<SpecialAbility> enabledAbilities = new LinkedList<>();
        List<LeaderCard> tmpCards = cards;
        for (LeaderCard leaderCard : tmpCards){
            if(leaderCard.isPlayed()){
                enabledAbilities.add(leaderCard.getSpecialAbility());
            }
        }
        return enabledAbilities;
    }
}
