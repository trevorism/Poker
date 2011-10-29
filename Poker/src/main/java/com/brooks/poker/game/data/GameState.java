package com.brooks.poker.game.data;

import com.brooks.poker.cards.Card;
import com.brooks.poker.cards.Deck;
import com.brooks.poker.game.data.pot.Pots;
import com.brooks.poker.player.Player;

/**
 * @author Trevor
 * 
 */
public class GameState{
    private final BlindsAnte blindsAnte;
    private final Table table;
    private final Deck deck;
    private final Pots pots;
    private final CommunityCards communityCards;
    
    public GameState(){
        blindsAnte = new BlindsAnte();
        table = new Table();
        deck = new Deck();
        pots = new Pots();
        communityCards = new CommunityCards();
    }

    public void beginHand(){
        if(table.getAllPlayers().size() == 0)
            throw new RuntimeException("This round has no active players.");

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

    public BlindsAnte getBlindsAnte(){
        return blindsAnte;
    }
    
    public Pots getPots(){
        return pots;
    }

    public CommunityCards getCommunityCards(){
        return communityCards;
    }
    
}
