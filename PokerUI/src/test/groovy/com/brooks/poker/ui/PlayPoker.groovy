/**
 * 
 */
package com.brooks.poker.ui

import com.brooks.poker.game.PokerGame
import com.brooks.poker.game.data.BlindsAnte
import com.brooks.poker.game.data.GameState
import com.brooks.poker.player.Player
import com.brooks.poker.player.action.AlwaysCallPlayerAction

/**
 * @author trevo_000
 *
 */
class PlayPoker {

	public static void main(String [] args){
		Player trevor = new Player("Trevor", 1000, new AlwaysCallPlayerAction())
		Player vaughn = new Player("Vaughn", 1000, new AlwaysCallPlayerAction())
		Player brooks = new Player("Brooks", 1000, new AlwaysCallPlayerAction())
		
		List<Player> players = [trevor, vaughn, brooks]
		
		GameState gameState = GameState.configureTournamentGameState(BlindsAnte.STANDARD_TOURNAMENT, players)
		
		PokerGame.playGame(gameState)
		
	}
	
}
