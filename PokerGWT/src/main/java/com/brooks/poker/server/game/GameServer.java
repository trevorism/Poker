package com.brooks.poker.server.game;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.brooks.poker.game.data.BlindsAnte;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.player.Player;
import com.google.appengine.api.channel.ChannelServiceFactory;

/**
 * @author Trevor
 *
 */
public class GameServer{
    private final Map<Long, GameStateData> gameStateCache;
    private List<Player> pendingPlayers;
    private static long currentId = 0;
    private String gameToken;
    
    private static final GameServer instance = new GameServer();
    
    public static GameServer getInstance(){
        return instance;
    }
    
    private GameServer(){
        gameStateCache = new HashMap<Long, GameStateData>();
        newGameToken();
    }
    
    private void newGameToken(){
        pendingPlayers = new LinkedList<Player>();
        currentId++;
        gameToken = ChannelServiceFactory.getChannelService().createChannel("POKER_GAME_" + currentId);
    }

    public void addPlayer(Player player){
        pendingPlayers.add(player);
    }
    
    public GameStateData getGameStateDataById(long currentId){
        return gameStateCache.get(currentId);
    }
    
    public GameStateData createGameState(){
        BlindsAnte blindsAnte = createBlindsAnte();        
        GameStateData gsId = new GameStateData(currentId++, GameState.configureGameState(blindsAnte, pendingPlayers));
        gameStateCache.put(gsId.getId(), gsId);
        newGameToken();
        return gsId;
    }

    public String getGameToken(){
        return gameToken;
    }

    private BlindsAnte createBlindsAnte(){
        BlindsAnte blindsAnte = new BlindsAnte();
        blindsAnte.bigBlind = 25;
        blindsAnte.smallBlind = 10;
        return blindsAnte;
    }
}
