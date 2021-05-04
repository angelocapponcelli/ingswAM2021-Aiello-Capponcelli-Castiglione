package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.exceptions.DepotException;
import it.polimi.ingsw.server.model.resources.Coin;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.server.model.resources.Servant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InHandLeaderCardTest {
    RealPlayer realPlayer;
    FaithTrack faithTrack;
    InHandLeaderCard inHandLeaderCard;

    @BeforeEach
    void init() throws DepotException {
        realPlayer= new RealPlayer("Seth Gecko");
        inHandLeaderCard= new InHandLeaderCard();
        LeaderRequirements leaderRequirements= new LeaderRequirements();
        leaderRequirements.add(Coin.getInstance(),1);
        LeaderCard leaderCard1= new LeaderCard(1,leaderRequirements,1,new SpecialDiscount(Coin.getInstance()));
        LeaderCard leaderCard2= new LeaderCard(2, new LeaderRequirements(),3, new SpecialWhiteMarble(Servant.getInstance()));
        inHandLeaderCard.cards.add(leaderCard1);
        inHandLeaderCard.cards.add(leaderCard2);
        realPlayer.getPersonalBoard().getStrongBoxDepot().addResources(ResourceType.COIN,3);
        leaderCard1.playCard(realPlayer);
    }


    @Test
    void getEnabledAbilities() {
        int tmp=0;
        int tmp1=0;
        for (LeaderCard leaderCard: inHandLeaderCard.cards){
            if(inHandLeaderCard.cards.get(tmp1).isPlayed()){
                tmp++;
            }
            tmp1++;
        }
        assertEquals(1,tmp);
    }

    @Test
    void getVictoryPoint() {
        int tmp=0;
        int tmp1=0;
        for (LeaderCard leaderCard: inHandLeaderCard.cards){
            if(inHandLeaderCard.cards.get(tmp1).isPlayed()){
                tmp= tmp + inHandLeaderCard.cards.get(tmp1).getVictoryPoint();
            }
            tmp1++;
        }
        assertEquals(1,tmp);
    }
}