package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.networking.ClientMessage;
import it.polimi.ingsw.networking.PlayersNumberMessage;
import it.polimi.ingsw.server.controller.gameStates.GameState;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.MultiplayerGame;
import it.polimi.ingsw.server.model.SinglePlayerGame;
import it.polimi.ingsw.server.model.player.RealPlayer;

public class GameController {

    Game game;
    GameState currentGameState;
    GameTurn currentTurn;

    public GameController(int id, int playerNumber){
        if (playerNumber==1) game = new SinglePlayerGame( id);
        else game = new MultiplayerGame(id, playerNumber);
        currentGameState = GameState.LOGIN;
        }

    public void receiveMessage(ClientMessage message) {
        switch (currentGameState){
            case LOGIN:
                loginState(message);
                break;
        }
    }

    private void loginState(ClientMessage message) {

    }


    private void updateGameState(GameState nextState){
        currentGameState = nextState;
    }

    public void addPlayer(String nickname) throws Exception {
        System.out.println("Crea giocatore " + nickname);
        game.addPlayer(new RealPlayer(nickname));
    }
}
