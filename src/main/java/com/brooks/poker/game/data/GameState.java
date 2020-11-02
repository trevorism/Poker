package com.brooks.poker.game.data;

import java.util.List;

import com.brooks.poker.cards.Deck;
import com.brooks.poker.game.data.pot.Pots;
import com.brooks.poker.game.handler.GameStateHandler;
import com.brooks.poker.game.handler.GameStateHandlerCollection;
import com.brooks.poker.player.Player;

/**
 * @author Trevor
 */
public class GameState{
	
    private BlindsAnte blindsAnte;
    private GamePhase gamePhase;
    private final Table table;
    private final Deck deck;
    private final Pots pots;
    private final CommunityCards communityCards;
    private final GameStateHandlerCollection gameStateHandlerCollection;

    
    private GameState(){
    	this.blindsAnte = BlindsAnte.NO_BLINDS_ANTE;
    	this.gamePhase = GamePhase.BEGIN_GAME;
        this.table = new Table();
        this.deck = new Deck();
        this.pots = new Pots();
        this.communityCards = new CommunityCards();
        this.gameStateHandlerCollection = new GameStateHandlerCollection(this);
    }

    public static GameState configureTournamentGameState(BlindsAnte blindsAnte, List<Player> players){
        GameState gameState = new GameState();
        gameState.blindsAnte = blindsAnte;

        for(Player player: players){
            gameState.getTable().joinTable(player);
        }
        
        return gameState;
    }
        
    public void addGameStateHandler(GameStateHandler handler){
    	gameStateHandlerCollection.add(handler);
    }

	public void invokeGameStateHandlerFor(GamePhase currentPhase) {
		gameStateHandlerCollection.handlePhase(currentPhase);
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

    public Deck getDeck(){
    	return deck;
    }
    
	public BlindsAnte getBlindsAnte(){
        return blindsAnte;
    }
    
    public void setBlindsAnte(BlindsAnte blindsAnte) {
		this.blindsAnte = blindsAnte;
	}

    public GamePhase getGamePhase() {
        return gamePhase;
    }

    public void setGamePhase(GamePhase gamePhase) {
        this.gamePhase = gamePhase;
    }
}
