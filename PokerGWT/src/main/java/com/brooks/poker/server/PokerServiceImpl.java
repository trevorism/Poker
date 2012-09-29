package com.brooks.poker.server;

import java.util.concurrent.Executors;

import no.eirikb.gwtchannelapi.client.Message;
import no.eirikb.gwtchannelapi.server.ChannelServer;

import com.brooks.poker.client.PokerException;
import com.brooks.poker.client.PokerService;
import com.brooks.poker.client.model.Action;
import com.brooks.poker.client.model.GameStateCM;
import com.brooks.poker.client.model.User;
import com.brooks.poker.client.push.MessageEvent;
import com.brooks.poker.player.Player;
import com.brooks.poker.server.convert.GameStateCMConverter;
import com.brooks.poker.server.convert.UserPlayerConverter;
import com.brooks.poker.server.game.GameServer;
import com.brooks.poker.server.game.GameStateData;
import com.google.appengine.api.channel.ChannelServiceFactory;
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
        Player player = userPlayerConverter.createNewPlayerFromUser(user);
        GameServer.getInstance().addPlayer(player);

    }

    @Override
    public GameStateCM startHand() throws PokerException{
        GameStateCMConverter converter = new GameStateCMConverter();
        GameStateData data = GameServer.getInstance().createGameState();

        if (data.getGameState().invalid())
            throw new PokerException("Not enough players in the game to start.");

        data.startGame();
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