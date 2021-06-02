package it.polimi.ingsw.server.controller.gameStates;

public enum GameState {
    LOGIN, //wait for all the players to join the game
    INIT, // initial phase of the game
    IN_GAME, //in game phase: players can performs the main actions
    END
}
