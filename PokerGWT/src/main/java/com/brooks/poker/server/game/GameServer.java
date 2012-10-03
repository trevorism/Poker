package com.brooks.poker.server.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brooks.poker.game.data.BlindsAnte;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.player.Player;
import com.brooks.poker.server.convert.UserPlayerConverter;
import com.google.appengine.api.channel.ChannelServiceFactory;

/**
 * @author Trevor
 *
 */
public class GameServer{
    private Map<Integer, Player> pendingPlayers;
    private static long currentId = 0;
    private String gameToken;
    
    private static final GameServer instance = new GameServer();
    
    public static GameServer getInstance(){
        return instance;
    }
    
    private GameServer(){
        newGameToken();
    }
    
    private void newGameToken(){
        pendingPlayers = new HashMap<Integer, Player>();
        currentId++;
        gameToken = ChannelServiceFactory.getChannelService().createChannel(getChannelId(currentId));
    }

    public void addPlayer(int index, Player player){
        pendingPlayers.put(index, player);
    }

    public String getGameToken(){
        return gameToken;
    }

    public String getChannelId(long id){
        return "POKER_GAME_" + id;
    }
    
    public GameStateData createGameState(){
        BlindsAnte blindsAnte = createBlindsAnte();        
        List<Player> players = createPlayerList();
        GameState gameState = GameState.configureGameState(blindsAnte, players);
        gameState.setId(currentId);
        GameStateData gsId = new GameStateData(gameState);
        newGameToken();
        return gsId;
    }

    private List<Player> createPlayerList(){
        UserPlayerConverter converter = new UserPlayerConverter();
        List<Player> players = converter.convertMapToList(pendingPlayers);
        return players;
    }

    private BlindsAnte createBlindsAnte(){
        BlindsAnte blindsAnte = new BlindsAnte();
        blindsAnte.bigBlind = 25;
        blindsAnte.smallBlind = 10;
        return blindsAnte;
    }

    public Map<Integer, Player> getPendingPlayers(){       
        return pendingPlayers;
    }

    public static long getCurrentId(){
        return currentId;
    }
        
}
