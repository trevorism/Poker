package com.brooks.poker.ui.input

import org.junit.Test
import static com.brooks.poker.ui.input.UserInputResult.*

class UserInputTest {

	@Test
	void "convert bet amount is valid or zero"(){
		UserInput userInput = new UserInput()
		
		assert 0 == userInput.convertToBetAmount(null, 0)
		assert 0 == userInput.convertToBetAmount("", 0)
		assert 0 == userInput.convertToBetAmount("C", 0)

		assert 0 == userInput.convertToBetAmount("10", 20)
		assert 20 == userInput.convertToBetAmount("20", 20)
		assert 25 == userInput.convertToBetAmount("25", 20)			
	}
	
	@Test
	void "convert to user input result is valid"(){
		UserInput userInput = new UserInput()
		
		assert ERROR == userInput.convertToUserInputResult("F", [])
		assert ERROR == userInput.convertToUserInputResult("C", ["F", "B"])
		assert ERROR == userInput.convertToUserInputResult("B", ["F", "C"])
		assert ERROR == userInput.convertToUserInputResult("R", ["C", "B"])
		
		assert CALL == userInput.convertToUserInputResult("C", ["C", "B"])
		assert BET == userInput.convertToUserInputResult("B", ["C", "B"])
		assert FOLD == userInput.convertToUserInputResult("F", ["F", "C", "B"])
		assert CALL == userInput.convertToUserInputResult("C", ["F", "C", "B"])
		assert BET == userInput.convertToUserInputResult("R", ["F", "C", "R"])
	}
}
