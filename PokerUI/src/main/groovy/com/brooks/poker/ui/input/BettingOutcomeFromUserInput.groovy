package com.brooks.poker.ui.input

import com.brooks.poker.outcome.BettingOutcome
import com.brooks.poker.outcome.BettingOutcomeFactory;

class BettingOutcomeFromUserInput {
	private final UserInputResult userInputResult
	private final int raiseAmount
	
	BettingOutcomeFromUserInput(UserInputResult userInputResult, int raiseAmount){
		this.userInputResult = userInputResult
		this.raiseAmount = raiseAmount
	}
	
	BettingOutcome createBettingOutcome(){
		if(userInputResult == UserInputResult.FOLD){ 
			return BettingOutcomeFactory.createFoldOutcome()
		}
		if(userInputResult == UserInputResult.CALL){
			return BettingOutcomeFactory.createCallOutcome()
		}
		if(userInputResult == UserInputResult.BET){
			return BettingOutcomeFactory.createRaiseOutcome(raiseAmount)
		}
	}
	
}
