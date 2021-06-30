package it.polimi.ingsw.server.model.player;

import it.polimi.ingsw.client.view.reducedGameModel.SpecialAbilityType;
import it.polimi.ingsw.server.controller.GameController;
import it.polimi.ingsw.server.model.personalBoard.PersonalBoard;
import it.polimi.ingsw.server.model.resources.ResourceType;
import it.polimi.ingsw.server.model.specialAbilities.SpecialAbility;
import it.polimi.ingsw.utils.observer.Observer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Real player extends player. it is a type of player.
 */
public class RealPlayer extends Player {
    private final PersonalBoard personalBoard;
    private final Integer victoryPoint;
    private final Map<SpecialAbilityType, ResourceType> activatedSpecialAbilities = new HashMap<>();

    /**
     * Class construction. Instantiates a new Real Player
     * @param nickName the name of the player
     * @param gameController the game controller which manages the game that contains the player
     */
    public RealPlayer(String nickName, GameController gameController) {
        super(nickName, gameController);
        this.personalBoard = new PersonalBoard();
        this.victoryPoint = 0;
    }

    /**
     * Class construction. Instantiates a new Real Player
     * @param nickName the name of the player
     */
    public RealPlayer(String nickName) {
        super(nickName);
        this.personalBoard = new PersonalBoard();
        this.victoryPoint = 0;
    }

    /**
     * Performs the sum of the victory points that the player gets during the game
     * @return total of victory points
     */
    public Integer getVictoryPoint() {
        int sum = 0;
        /* points from leaderCard*/
        sum = sum + this.getPersonalBoard().getInHandLeaderCards().getVictoryPoint();
        /*points from developmentCards*/
        sum = sum + this.getPersonalBoard().getPersonalDevelopmentBoard().getVictoryPoint();
        /*sum of the resource then divided by 5. every 5 resources the player is given 1 victory point*/

        sum = sum + (getPersonalBoard().getAllResourceCount() / 5);

        /* points from vaticanReportStatus*/
        sum = sum + this.vaticanReportStatus.getVictoryPoint();
        /* points from the faithPosition*/
        sum = sum + gameController.getGameModel().getGlobalBoard().getFaithTrack().getTrack().get(faithPosition).getVictoryPoints();
        return sum;
    }

    /**
     * Gets personal board of the player
     * @return personal board
     */
    public PersonalBoard getPersonalBoard() {
        return this.personalBoard;
    }


    public void addActivatedSpecialAbility(SpecialAbility specialAbility){
        activatedSpecialAbilities.put( specialAbility.getSpecialAbilityType(), specialAbility.getResource().getResourceType());
    }

    @Override
    public void addObserver(Observer obs) {
        super.addObserver(obs);
        personalBoard.addObserver(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        super.removeObserver(obs);
        personalBoard.addObserver(obs);
    }
}
