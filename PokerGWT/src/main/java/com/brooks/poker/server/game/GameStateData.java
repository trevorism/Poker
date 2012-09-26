package com.brooks.poker.server.game;

import com.brooks.poker.client.model.Action;
import com.brooks.poker.client.model.User;
import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;

/**
 * @author Trevor
 *
 */
public class GameStateData{
    private final long id;
    private final GameState gameState;
    private GamePhase gamePhase;
    
    public GameStateData(long id, GameState gameState){
        this.id = id;
        this.gameState = gameState;
        gamePhase = GamePhase.BEGIN_HAND;
    }
    
    public GamePhase getGamePhase(){
        return gamePhase;
    }
    public void setGamePhase(GamePhase gamePhase){
        this.gamePhase = gamePhase;
    }
    public long getId(){
        return id;
    }
    public GameState getGameState(){
        return gameState;
    }

    public void update(User user, Action action){
    }
    
    
}
