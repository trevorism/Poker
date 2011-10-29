/**
 * 
 */
package com.brooks.poker.game.states;

import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.data.Table;
import com.brooks.poker.player.Player;


/**
 * @author Trevor
 *
 */
public abstract class GameStateHandler {

    protected GameState gameState;
    
    public GameStateHandler(GameState gameState){
        this.gameState = gameState;
    }
    
	public abstract void handleState();
	
	public abstract GamePhase getNextPhase();
	
	public Player getPlayerAfterDealer(){
	    Table table = gameState.getTable();
        
        Player dealer = table.getDealer();
        Player nextPlayer = table.getNextActivePlayer(dealer);
        
        return nextPlayer;
	}
}
