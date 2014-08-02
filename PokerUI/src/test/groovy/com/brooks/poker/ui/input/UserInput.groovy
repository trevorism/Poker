package com.brooks.poker.ui.input

class UserInput {

	private static final String NO_ACTION_PROMPT = "Would you like to Check (C), or Bet (B)?"
	private static final String ACTION_PROMPT = "Would you like to Fold (F), Call (C), or Raise (R)?"

	BettingOutcomeFromUserInput promptWithNoAction(int minBet){
		UserInputResult inputResult = UserInputResult.ERROR
		while(inputResult == UserInputResult.ERROR){
			inputResult = requestUserInput(NO_ACTION_PROMPT, ["C", "B"])
		}
		
		return promptForBetAmount(minBet, inputResult)		
	}

	BettingOutcomeFromUserInput promptWithAction(int minBet){
		UserInputResult inputResult = UserInputResult.ERROR
		while(inputResult == UserInputResult.ERROR){
			inputResult = requestUserInput(ACTION_PROMPT, ["F", "C", "R"])
		}

		return promptForBetAmount(minBet, inputResult)		
	}

	private UserInputResult requestUserInput(String message, def validValues) {
		prompt(message)
		String input = readInput()
		return convertToUserInputResult(input, validValues)
	}
		
	private prompt(String message) {
		println()
		println(message)
	}
	
	private String readInput(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
		return br.readLine()
	}

	private UserInputResult convertToUserInputResult(String input, List validValues){
		String value = input?.toUpperCase()
		if(validValues.find{ it == value }){
			if("F" == value)
				return UserInputResult.FOLD
			if("C" == value)
				return UserInputResult.CALL
			else
				return UserInputResult.BET
		}
		return UserInputResult.ERROR
	}
	
	private int convertToBetAmount(String input, int minBet){
		try{
			int value = Integer.parseInt(input)
			if(value < minBet){
				return 0
			}
			return value
		}catch(Exception e){
			return 0
		}
	}
	
	private BettingOutcomeFromUserInput promptForBetAmount(int minBet, UserInputResult inputResult) {
		if(inputResult == UserInputResult.BET){
			int betAmount = 0;
			while(betAmount == 0){
				betAmount = requestBetAmount(betAmount, minBet)
			}
			return new BettingOutcomeFromUserInput(inputResult, betAmount)
		}
		return new BettingOutcomeFromUserInput(inputResult, 0)
	}

	private int requestBetAmount(int betAmount, int minBet) {
		prompt("How much would you like to bet?")
		String input = readInput()
		betAmount = convertToBetAmount(input, minBet)
		return betAmount
	}
}
