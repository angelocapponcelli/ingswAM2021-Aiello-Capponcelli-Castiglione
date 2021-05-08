package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.cards.LeaderCard;
import it.polimi.ingsw.server.model.cards.LeaderRequirements;
import it.polimi.ingsw.server.model.globalBoard.faithTrack.FaithTrack;
import it.polimi.ingsw.server.model.personalBoard.InHandLeaderCard;
import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.resources.Coin;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.server.model.resources.Servant;
import it.polimi.ingsw.server.model.specialAbilities.SpecialDiscount;
import it.polimi.ingsw.server.model.specialAbilities.SpecialWhiteMarble;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InHandLeaderCardTest {
    RealPlayer realPlayer;
    FaithTrack faithTrack;
    InHandLeaderCard inHandLeaderCard;

    @BeforeEach
    void init() {
        realPlayer = new RealPlayer("Seth Gecko");
        inHandLeaderCard = new InHandLeaderCard();
        LeaderRequirements leaderRequirements = new LeaderRequirements();
        leaderRequirements.add(Coin.getInstance(), 1);
        LeaderCard leaderCard1 = new LeaderCard(1, leaderRequirements, 1, new SpecialDiscount(Coin.getInstance()));
        LeaderCard leaderCard2 = new LeaderCard(2, new LeaderRequirements(), 3, new SpecialWhiteMarble(Servant.getInstance()));
        inHandLeaderCard.getCards().add(leaderCard1);
        inHandLeaderCard.getCards().add(leaderCard2);
        realPlayer.getPersonalBoard().getStrongBoxDepot().addResources(ResourceType.COIN, 3);
        leaderCard1.playCard(realPlayer);
    }


    @Test
    void getEnabledAbilities() {
        int tmp = 0;
        int tmp1 = 0;
        for (LeaderCard leaderCard : inHandLeaderCard.getCards()) {
            if (inHandLeaderCard.getCards().get(tmp1).isPlayed()) {
                tmp++;
            }
            tmp1++;
        }
        assertEquals(1, tmp);
    }

    @Test
    void getVictoryPoint() {
        int tmp = 0;
        int tmp1 = 0;
        for (LeaderCard leaderCard : inHandLeaderCard.getCards()) {
            if (inHandLeaderCard.getCards().get(tmp1).isPlayed()) {
                tmp = tmp + inHandLeaderCard.getCards().get(tmp1).getVictoryPoint();
            }
            tmp1++;
        }
        assertEquals(1, tmp);
    }
}