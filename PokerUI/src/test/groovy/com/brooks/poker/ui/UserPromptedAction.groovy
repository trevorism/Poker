package com.brooks.poker.ui

import com.brooks.poker.game.GameActions
import com.brooks.poker.game.data.GameState
import com.brooks.poker.outcome.BettingOutcome
import com.brooks.poker.player.Player
import com.brooks.poker.player.action.PlayerAction
import com.brooks.poker.ui.input.BettingOutcomeFromUserInput
import com.brooks.poker.ui.input.UserInput

class UserPromptedAction implements PlayerAction{

	@Override
	public BettingOutcome getBettingOutcome(GameState gameState, Player player) {
		printGameState(gameState)
		printPlayer(player)

		UserInput userInput = new UserInput()

		int currentBet = gameState.getPots().currentBet
		BettingOutcomeFromUserInput outcome
		if(currentBet == 0){
			outcome = userInput.promptWithNoAction(GameActions.getMinBet(gameState))
		}
		else{
			outcome = userInput.promptWithAction(GameActions.getMinBet(gameState))
		}
		outcome.createBettingOutcome()
	}

	private void printGameState(GameState gameState){
		println("------------------------------------------------")
		println("Current Pot: ${gameState.getPots().pots.get(0).pot} -- Current Bet: ${gameState.getPots().currentBet}" )		
		
		println("Players in hand:")
		gameState.table.sortedActivePlayers.each { println it			 }
		if(!gameState.communityCards.empty){
			println("Community Cards:")
			gameState.communityCards.cards.each { print "${it} " }
			println()
		}
		println("------------------------------------------------")
	}

	private void printPlayer(Player player){
		println "${player} :: ${player.hand.cards.get(0)}, ${player.hand.cards.get(1)}"
	}
}
