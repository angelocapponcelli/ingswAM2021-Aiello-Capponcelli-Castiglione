package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.networking.connection.ServerConnectionHandler;
import it.polimi.ingsw.server.controller.gameStates.GameState;
import it.polimi.ingsw.server.model.Game;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private Integer gameID;
    private Game game;
    private Integer maxPlayersNumber;
    private GameState currentGameState;
    private List<Client> clientList = new ArrayList<>();
    private List<ServerConnectionHandler> connectionHandlers = new ArrayList<>();

    public Integer getGameID(){
        return gameID;
    }

    public GameController(){
        currentGameState = GameState.LOGIN;
    }

    public void setMaxPlayersNumber(int maxPlayersNumber){
        this.maxPlayersNumber = maxPlayersNumber;
    }

    public void addClient(Client client){
        clientList.add(client);
    }

    public Game getGame(){
        return game;
    }

    private void updateGameState(GameState nextState){
        currentGameState = nextState;
    }



}
