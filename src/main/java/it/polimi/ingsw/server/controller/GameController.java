package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.networking.messages.Message;
import it.polimi.ingsw.server.controller.gameStates.GameState;
import it.polimi.ingsw.server.model.Game;

public class GameController {

    Game game;
    GameState currentGameState;
    GameTurn currentTurn;

    public GameController(Game game){
        this.game = game;
        currentGameState = GameState.LOGIN;
        }

    void receiveMessage(Message message) {
    }





    private void updateGameState(GameState nextState){
        currentGameState = nextState;
    }
}
