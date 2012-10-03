package com.brooks.poker.server.game;

import no.eirikb.gwtchannelapi.server.ChannelServer;

import com.brooks.common.client.event.EventBus;
import com.brooks.poker.client.model.Action;
import com.brooks.poker.client.model.GameStateCM;
import com.brooks.poker.client.model.User;
import com.brooks.poker.client.push.GameStateMessage;
import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.states.GameStateFactory;
import com.brooks.poker.game.states.GameStateHandler;
import com.brooks.poker.server.convert.GameStateCMConverter;
import com.brooks.poker.server.playerAction.PlayerActionEvent;

/**
 * @author Trevor
 * 
 */
public class GameStateData{
    private final String channelKey;
    private final GameState gameState;
    private final GameStateFactory factory;
    private GamePhase gamePhase;
    private GameStateCMConverter converter;
    
    public GameStateData(String channelKey, GameState gameState){
        this.channelKey = channelKey;
        this.gameState = gameState;
        this.factory = new GameStateFactory(gameState);
        this.converter = new GameStateCMConverter();
    }

    public GamePhase getGamePhase(){
        return gamePhase;
    }

    public void setGamePhase(GamePhase gamePhase){
        this.gamePhase = gamePhase;
    }
 
    public String getChannelKey(){
        return channelKey;
    }

    public GameState getGameState(){
        return gameState;
    }

    public void startGame(){
        gamePhase = GamePhase.BEGIN_HAND;
        while (!gamePhase.equals(GamePhase.END_GAME)){
            GameStateHandler handler = factory.getStateHandler(gamePhase);
            handler.handleState();
            gamePhase = handler.getNextPhase();
            
            GameStateCM clientModel = converter.convert(gameState);
            ChannelServer.send(channelKey, new GameStateMessage(clientModel));
        }
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
