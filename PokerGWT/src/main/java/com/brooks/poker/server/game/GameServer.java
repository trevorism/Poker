package com.brooks.poker.server.game;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.brooks.poker.game.data.BlindsAnte;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.player.Player;

/**
 * @author Trevor
 *
 */
public class GameServer{
    private final Map<Long, GameStateData> gameStateCache;
    private List<Player> pendingPlayers;
    private static long currentId = 0;
    
    private static final GameServer instance = new GameServer();
    
    public static GameServer getInstance(){
        return instance;
    }
    
    private GameServer(){
        gameStateCache = new HashMap<Long, GameStateData>();
        pendingPlayers = new LinkedList<Player>();
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
        pendingPlayers = new LinkedList<Player>();
        return gsId;
    }

    private BlindsAnte createBlindsAnte(){
        BlindsAnte blindsAnte = new BlindsAnte();
        blindsAnte.bigBlind = 25;
        blindsAnte.smallBlind = 10;
        return blindsAnte;
    }
}
