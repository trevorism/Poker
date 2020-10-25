# Poker

No limit texas hold'em implementation.

## To Build

`gradlew clean build`

## To Play

Run `main()` in PokerUI/src/main/groovy/com.brooks.poker.ui.PlayPoker.groovy
To get user input, replace `new AlwaysCallPlayerAction()` with `new UserPromptedAction()`

## Organization

`Poker` contains the engine for running the game. 

`PokerUI` is a simple print out for the game. Supports a tournament style game with up to 20 players at a table.

##Future work

One day, I may create a web app that displays the GameState. Contributions here are welcome.

## Use

Free to use; MIT license.