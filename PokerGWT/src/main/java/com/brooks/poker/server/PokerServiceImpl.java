package com.brooks.poker.server;

import java.util.LinkedList;
import java.util.List;

import no.eirikb.gwtchannelapi.server.ChannelServer;

import com.brooks.common.client.event.EventBus;
import com.brooks.poker.client.PokerException;
import com.brooks.poker.client.PokerService;
import com.brooks.poker.client.model.Action;
import com.brooks.poker.client.model.GameStateCM;
import com.brooks.poker.client.model.User;
import com.brooks.poker.client.push.GameStateMessage;
import com.brooks.poker.client.push.UserMessage;
import com.brooks.poker.game.data.BlindsAnte;
import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.states.GameStateFactory;
import com.brooks.poker.game.states.GameStateHandler;
import com.brooks.poker.player.Player;
import com.brooks.poker.server.convert.GameStateCMConverter;
import com.brooks.poker.server.model.PendingUser;
import com.brooks.poker.server.playerAction.EventDrivenPlayerAction;
import com.brooks.poker.server.playerAction.PlayerActionEvent;
import com.google.appengine.api.ThreadManager;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author Trevor
 * 
 */
public class PokerServiceImpl extends RemoteServiceServlet implements PokerService{

    private static final long serialVersionUID = 1L;

    @Override
    public String connectToChannel(){
        long id = DataStoreUtils.getPendingGameSequenceNumber();
        String channelId = DataStoreUtils.getChannelId(id);
        String gameToken = ChannelServiceFactory.getChannelService().createChannel(channelId);

        Thread thread = ThreadManager.createBackgroundThread(new SyncUsersWithClient(channelId));
        thread.start();
        return gameToken;
    }

    @Override
    public void addUser(UserMessage userAndIndex) throws PokerException{
        String username = userAndIndex.getUser().getName();

        List<PendingUser> pendingUsers = DataStoreUtils.queryForPendingPlayers();
        boolean found = findName(pendingUsers, username);
        if (found)
            throw new PokerException("A player already has joined the game with that name.");
        DataStoreUtils.addUser(userAndIndex);
        long id = DataStoreUtils.getPendingGameSequenceNumber();
        ChannelServer.send(DataStoreUtils.getChannelId(id), userAndIndex);
    }

    private boolean findName(List<PendingUser> pendingUsers, String name){
        for (PendingUser pu : pendingUsers){
            if (pu.getName().equals(name))
                return true;
        }
        return false;
    }

    @Override
    public void startGame() throws PokerException{
        List<PendingUser> pendingUsers = DataStoreUtils.queryForPendingPlayers();
        long id = DataStoreUtils.getPendingGameSequenceNumber();
        final GameState gameState = createGameState(pendingUsers, id);

        Thread thread = ThreadManager.createBackgroundThread(new Runnable(){

            @Override
            public void run(){
                playGame(gameState);
            }
        });
        thread.start();
        DataStoreUtils.incrementSequenceNumber();
        DataStoreUtils.deletePendingUsers();
    }
    
    private static void playGame(GameState gameState){
        GameStateFactory factory = new GameStateFactory(gameState);
        GamePhase currentPhase = GamePhase.BEGIN_HAND;
        GameStateCMConverter converter = new GameStateCMConverter();
        while (!currentPhase.equals(GamePhase.END_GAME)){
            if(currentPhase == GamePhase.BEGIN_HAND){
                GameStateCM clientModel = converter.convert(gameState);
                ChannelServer.send(DataStoreUtils.getChannelId(gameState.getId()), new GameStateMessage(clientModel));
            }
            
            GameStateHandler handler = factory.getStateHandler(currentPhase);
            handler.handleState();
            currentPhase = handler.getNextPhase();
        }
    }

    private GameState createGameState(List<PendingUser> pendingUsers, long id){
        List<Player> players = new LinkedList<Player>();
        for (PendingUser user : pendingUsers){
            Player player = new Player(user.getName(), 1000, new EventDrivenPlayerAction(user.getName(), id));
            players.add(player);
        }

        BlindsAnte blindsAnte = new BlindsAnte();
        blindsAnte.bigBlind = 25;
        blindsAnte.smallBlind = 10;

        GameState gameState = GameState.configureGameState(blindsAnte, players);
        gameState.setId(id);
        return gameState;
    }

    @Override
    public void placeBet(User user, Action action){
        PlayerActionEvent actionEvent = new PlayerActionEvent(user, action);
        EventBus.getInstance().fireEvent(actionEvent);
    }

}