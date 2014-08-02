package com.brooks.poker.ui

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.handler.GameStateHandlerAdaptor
import com.brooks.poker.player.Player;

class PrintGameState extends GameStateHandlerAdaptor {

	@Override
	public void handleEndHandState(GameState gameState) {
		gameState.table.sortedActivePlayers.each {
			printPlayer(it)
		}
	}

	@Override
	public void handleEndGameState(GameState gameState) {

	}
	
	private void printPlayer(Player player){
		print "${player} :: "
		player.hand.cards.each {
			print "${it}, "
		}
		println (player.hand.handValue)
	}
	
}
