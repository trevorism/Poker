package com.brooks.poker.server;

import java.util.LinkedList;
import java.util.List;

import com.brooks.common.client.event.EventBus;
import com.brooks.poker.client.PokerException;
import com.brooks.poker.client.PokerService;
import com.brooks.poker.client.model.Action;
import com.brooks.poker.client.model.GameStateCM;
import com.brooks.poker.client.model.User;
import com.brooks.poker.client.push.ChannelKey;
import com.brooks.poker.client.push.GameStateMessage;
import com.brooks.poker.client.push.PushEvent;
import com.brooks.poker.game.data.BlindsAnte;
import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.states.GameStateFactory;
import com.brooks.poker.game.states.GameStateHandler;
import com.brooks.poker.player.Player;
import com.brooks.poker.server.convert.GameStateCMConverter;
import com.brooks.poker.server.playerAction.EventDrivenPlayerAction;
import com.brooks.poker.server.playerAction.PlayerActionEvent;
import com.brooks.poker.server.store.GameStateDao;
import com.brooks.poker.server.store.IndexedString;
import com.brooks.poker.server.store.PendingPlayerDao;
import com.brooks.poker.server.store.SequenceNumberDao;
import com.google.appengine.api.ThreadManager;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author Trevor
 * 
 */
public class PokerServiceImpl extends RemoteServiceServlet implements PokerService{

    private static final long serialVersionUID = 1L;

    private final SequenceNumberDao sequenceNumberDao = new SequenceNumberDao();
    private final PendingPlayerDao pendingPlayerDao = new PendingPlayerDao();
    private final GameStateDao gameStateDao = new GameStateDao();

    @Override
    public ChannelKey connectToChannel(String clientKey){
        long id = sequenceNumberDao.getPendingGameSequenceNumber();
        List<IndexedString> pendingUsers = pendingPlayerDao.queryForPendingPlayers(id);        
        gameStateDao.savePendingGame(pendingUsers, id);
        return createChannelKey(clientKey, id);
    }

    private ChannelKey createChannelKey(String clientKey, long id){
        return new ChannelKey(clientKey, id);
    }

    @Override
    public void addUser(User user) throws PokerException{
        String username = user.getName();
        long id = sequenceNumberDao.getPendingGameSequenceNumber();

        List<IndexedString> pendingUsers = pendingPlayerDao.queryForPendingPlayers(id);
        boolean found = findName(pendingUsers, username);
        if (found)
            throw new PokerException("A player already has joined the game with that name.");
        pendingPlayerDao.addUser(user, id);

        gameStateDao.savePendingGame(pendingPlayerDao.queryForPendingPlayers(id), -1);

    }

    private boolean findName(List<IndexedString> pendingUsers, String name){
        for (IndexedString pu : pendingUsers){
            if (pu.getName().equals(name))
                return true;
        }
        return false;
    }

    @Override
    public void startGame() throws PokerException{
        long id = sequenceNumberDao.getPendingGameSequenceNumber();
        List<IndexedString> pendingUsers = pendingPlayerDao.queryForPendingPlayers(id);
        final GameState gameState = createGameState(pendingUsers, id);

        Thread thread = ThreadManager.createBackgroundThread(new Runnable(){

            @Override
            public void run(){
                playGame(gameState);
            }
        });
        thread.start();
        sequenceNumberDao.incrementSequenceNumber();
        pendingPlayerDao.deleteIndexedStrings(id);
    }

    private void playGame(GameState gameState){
        GameStateFactory factory = new GameStateFactory(gameState);
        GamePhase currentPhase = GamePhase.BEGIN_HAND;
        GameStateCMConverter converter = new GameStateCMConverter();
        while (!currentPhase.equals(GamePhase.END_GAME)){
            if (currentPhase == GamePhase.BEGIN_HAND){
                GameStateCM clientModel = converter.convert(gameState);
                gameStateDao.saveGameState(clientModel);
            }

            GameStateHandler handler = factory.getStateHandler(currentPhase);
            handler.handleState();
            currentPhase = handler.getNextPhase();
        }
    }

    private GameState createGameState(List<IndexedString> pendingUsers, long id){
        List<Player> players = new LinkedList<Player>();
        for (IndexedString user : pendingUsers){
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

    @Override
    public PushEvent receiveServerPush(ChannelKey key){
        return new GameStateMessage(gameStateDao.retrieveGameState());
    }

}