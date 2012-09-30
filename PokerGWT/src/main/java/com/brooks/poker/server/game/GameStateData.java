package com.brooks.poker.server.game;

import com.brooks.common.client.event.EventBus;
import com.brooks.poker.client.model.Action;
import com.brooks.poker.client.model.User;
import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.states.GameStateFactory;
import com.brooks.poker.game.states.GameStateHandler;
import com.brooks.poker.server.playerAction.PlayerActionEvent;

/**
 * @author Trevor
 * 
 */
public class GameStateData{
    private final long id;
    private final GameState gameState;
    private final GameStateFactory factory;
    private GamePhase gamePhase;

    public GameStateData(long id, GameState gameState){
        this.id = id;
        this.gameState = gameState;
        this.factory = new GameStateFactory(gameState);      
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

    public void startGame(){
        gamePhase = GamePhase.BEGIN_HAND;
        nextGameStatePhase();
    }

    public void update(User user, Action action){
        PlayerActionEvent actionEvent = new PlayerActionEvent(user, action);
        EventBus.getInstance().fireEvent(actionEvent);

        nextGameStatePhase();
    }

    private void nextGameStatePhase(){
        GameStateHandler handler = factory.getStateHandler(gamePhase);
        handler.handleState();
        gamePhase = handler.getNextPhase();
    }
}
