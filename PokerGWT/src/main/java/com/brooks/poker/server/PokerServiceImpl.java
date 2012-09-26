package com.brooks.poker.server;

import com.brooks.poker.client.PokerService;
import com.brooks.poker.client.model.Action;
import com.brooks.poker.client.model.GameStateCM;
import com.brooks.poker.client.model.User;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.player.Player;
import com.brooks.poker.server.convert.GameStateCMConverter;
import com.brooks.poker.server.convert.UserPlayerConverter;
import com.brooks.poker.server.game.GameServer;
import com.brooks.poker.server.game.GameStateData;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author Trevor
 * 
 */
public class PokerServiceImpl extends RemoteServiceServlet implements PokerService{

    private static final long serialVersionUID = 1L;

    @Override
    public void addUser(User user){
        UserPlayerConverter userPlayerConverter = new UserPlayerConverter();
        Player player = userPlayerConverter.convertUserToPlayer(user);
        GameServer.getInstance().addPlayer(player);
    }

    @Override
    public GameStateCM startHand(){
        GameStateCMConverter converter = new GameStateCMConverter();
        GameStateData data = GameServer.getInstance().createGameState();

        GameStateCM clientModel = converter.convert(data);

        return clientModel;
    }

    @Override
    public GameStateCM placeBet(User user, Action action){
        GameStateCMConverter converter = new GameStateCMConverter();
        GameStateData gameStateData = GameServer.getInstance().getGameStateDataById(action.getGameId());
        
        gameStateData.update(user, action);
        GameStateCM clientModel = converter.convert(gameStateData);        
        return clientModel;
    }

}