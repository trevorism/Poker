package com.brooks.poker.game.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.brooks.poker.cards.CardUtils;
import com.brooks.poker.command.PlayerCommand;
import com.brooks.poker.player.Player;

/**
 * @author Trevor
 * 
 */

public class Table{

    private List<Player> collapsedPositions = new ArrayList<Player>();
    private Set<Player> notActivePlayers = new HashSet<Player>();
    private int dealerIndex = -1;

    public Player getNextActivePlayer(Player startPlayer){
        Player player = getNextPlayer(startPlayer);

        while (notActivePlayers.contains(player)){
            player = getNextPlayer(player);
            if (player.isNullPlayer()){
                return Player.NOBODY;
            }
            if(player.equals(startPlayer)){
                return Player.NOBODY;
            }
        }
        return player;
    }

    public void makeNextPlayerDealer(){
        Player nextPlayer = getNextPlayer(getDealer());
        setDealer(nextPlayer);
    }

    public Player getDealer(){
        if (dealerIndex < 0 || dealerIndex >= collapsedPositions.size())
            dealerIndex = 0;

        return collapsedPositions.get(dealerIndex);
    }

    public void setDealer(Player player){
        dealerIndex = collapsedPositions.indexOf(player);
    }

    public void joinTable(Player player){
        collapsedPositions.add(player);
    }

    public void removePlayer(Player player){
        collapsedPositions.remove(player);
    }

    public void makeInactive(Player player){
        notActivePlayers.add(player);
    }

    public boolean isInactive(Player player){
        return notActivePlayers.contains(player);
    }

    public void randomizeDealer(){
        Random random = new Random();
        dealerIndex = random.nextInt(collapsedPositions.size());
    }

    public void reset(){
        notActivePlayers.clear();
        for (Player player : getAllPlayers()){
            player.reset();
        }
    }

    public Set<Player> getAllPlayers(){
        Set<Player> allPlayers = new HashSet<Player>(collapsedPositions);
        return allPlayers;
    }

    public List<Player> getSortedActivePlayers(){
        final List<Player> activePlayers = new LinkedList<Player>();
        
        Player firstActivePlayer = getNextActivePlayer(getDealer());
        executeOnEachActivePlayer(firstActivePlayer, new PlayerCommand(){
            @Override
            public void execute(Player player){
                activePlayers.add(player);
            }            
        });
        
        return activePlayers;
    }

    public int getActivePlayersSize(){
        return collapsedPositions.size() - notActivePlayers.size();
    }
    
    public void executeOnEachActivePlayer(Player startPlayer, PlayerCommand command){
        if(isInactive(startPlayer))
            startPlayer = getNextActivePlayer(startPlayer);
        Player firstPlayer = startPlayer;
        
        do{
            command.execute(startPlayer);
            startPlayer = getNextActivePlayer(startPlayer);
            
            if(startPlayer.isNullPlayer())
                break;
            
        } while (!firstPlayer.equals(startPlayer));
    }

    private Player getNextPlayer(Player player){
        return attemptGetNextPlayer(player);
    }

    private Player attemptGetNextPlayer(Player player){
        int playerIndex = collapsedPositions.indexOf(player);
        if (playerIndex == -1)
            return Player.NOBODY;

        int nextIndex = CardUtils.circularIncrement(playerIndex, collapsedPositions.size());
        return collapsedPositions.get(nextIndex);
    }

}
