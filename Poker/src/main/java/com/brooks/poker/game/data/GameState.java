package com.brooks.poker.game.data;

import java.util.List;
import java.util.Set;

import com.brooks.poker.cards.Card;
import com.brooks.poker.cards.Deck;
import com.brooks.poker.game.data.pot.Pots;
import com.brooks.poker.player.Player;

/**
 * @author Trevor
 */
public class GameState{
    
    private static final int MAXIMUM_NUMBER_OF_PLAYERS = 20;
	private static final int MINIMUM_NUMBER_OF_PLAYERS = 2;
	
    private BlindsAnte blindsAnte = BlindsAnte.NO_BLINDS_ANTE;
    private final Table table;
    private final Deck deck;
    private final Pots pots;
    private final CommunityCards communityCards;
        
    private GameState(){
        this.table = new Table();
        this.deck = new Deck();
        this.pots = new Pots();
        this.communityCards = new CommunityCards();
    }

    public static GameState configureTournamentGameState(BlindsAnte blindsAnte, List<Player> players){
        GameState gameState = new GameState();
        gameState.blindsAnte = blindsAnte;

        for(Player player: players){
            gameState.getTable().joinTable(player);
        }
        
        return gameState;
    }
    
    public boolean invalid(){
        Set<Player> players = table.getAllPlayers();
        if(players == null)
            return true;
        if(players.size() < MINIMUM_NUMBER_OF_PLAYERS)
            return true;
        if(players.size() > MAXIMUM_NUMBER_OF_PLAYERS)
            return true;        
        return false;
    }

    public void beginHand(){
        if(invalid())
            return;

        table.reset();
        deck.reset();
        communityCards.reset();
        table.makeNextPlayerDealer();
        pots.reset(table.getAllPlayers());
    }

    public void dealCardToPlayer(Player player){
        Card card = deck.dealCard();
        player.addCard(card);
    }
    
    public void burnCard(){
        deck.dealCard();
    }
    
    public void dealCommunityCard(){
        Card dealtCard = deck.dealCard();
        communityCards.add(dealtCard);
        for(Player p : table.getSortedActivePlayers()){
            p.addCard(dealtCard);
        }
    }
    
    public void updateCurrentBet(int pendingBet){
        pots.updateAmountOwed(pendingBet);       
    }
    
    public void endBettingRound(){
        pots.putPendingBetsIntoPots(table.getAllPlayers());
    }
    
    public int getMinBet(){
        int minBet = pots.getCurrentBet() * 2;
        if(minBet < blindsAnte.bigBlind){
            minBet = blindsAnte.bigBlind;
        }
        return minBet;
    }
    
    public Table getTable(){
        return table;
    }
   
    public Pots getPots(){
        return pots;
    }

    public CommunityCards getCommunityCards(){
        return communityCards;
    }

    public BlindsAnte getBlindsAnte(){
        return blindsAnte;
    }
    
    public void setBlindsAnte(BlindsAnte blindsAnte) {
		this.blindsAnte = blindsAnte;
	}   
        
}
