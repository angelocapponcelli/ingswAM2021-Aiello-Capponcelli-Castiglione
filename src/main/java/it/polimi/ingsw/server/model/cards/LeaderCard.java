package it.polimi.ingsw.server.model.cards;

import it.polimi.ingsw.server.model.player.RealPlayer;
import it.polimi.ingsw.server.model.specialAbilities.SpecialAbility;

/**
 * Leader Card
 */
public class LeaderCard {
    private final Integer id;
    private final LeaderRequirements requirements;
    private final Integer victoryPoint;
    private final SpecialAbility specialAbility;
    private Boolean played;

    /**
     * Class constructor.
     * @param id the value that is unique for each card
     * @param requirements the requisite that the player needs to have to play the card
     * @param victoryPoint the points that the player receive when the card is played
     * @param specialAbility the ability that is activated when the card is played
     */
    public LeaderCard(Integer id, LeaderRequirements requirements, Integer victoryPoint, SpecialAbility specialAbility) {
        this.id = id;
        this.requirements = requirements;
        this.victoryPoint = victoryPoint;
        this.specialAbility = specialAbility;
        this.played = false;
    }

    /**
     * @return id of this card
     */

    public Integer getId() {
        return id;
    }

    /**
     * @return leaderRequirements of this card
     */

    public LeaderRequirements getRequirements() {
        return requirements;
    }

     /**
     * @return victoryPoint of this card
     */

    public Integer getVictoryPoint() {
        return victoryPoint;
    }

    /**
     * @return specialAbility of this card
     */

    public SpecialAbility getSpecialAbility() {
        return specialAbility;
    }

    /**
     * @return true if the card has been played
     */

    public Boolean isPlayed() {
        return played;
    }

    /**
     * This method is used to play the card.
     * @param realPlayer that plays the card
     */

    public void playCard(RealPlayer realPlayer) {
        if (this.requirements.check(realPlayer)) {
            this.specialAbility.onActivation(realPlayer);
            this.played = true;
        }
    }
}
