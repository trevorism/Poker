# Poker
![Jenkins](https://img.shields.io/jenkins/build/http/trevorism-build.eastus.cloudapp.azure.com/poker)
![Jenkins Coverage](https://img.shields.io/jenkins/coverage/jacoco/http/trevorism-build.eastus.cloudapp.azure.com/poker)
![GitHub last commit](https://img.shields.io/github/last-commit/trevorism/poker)
![GitHub language count](https://img.shields.io/github/languages/count/trevorism/poker)
![GitHub top language](https://img.shields.io/github/languages/top/trevorism/poker)

No limit texas hold'em implementation.

## To Build

`gradlew clean build`

## To Play

Run `main()` in PokerUI/src/main/groovy/com.brooks.poker.ui.PlayPoker.groovy
To get user input, replace `new AlwaysCallPlayerAction()` with `new UserPromptedAction()`

## Code Organization

`Poker` contains the engine for running the game. It's pure java, version >= 8 will work.

`PokerUI` is a simple print out for the game. Supports a tournament style game with up to 20 players at a table. This is written in groovy 2.5.8

## Future work

Contributions welcome! Submit pull requests.

I built this to test machine learning algorithms on a non-trivial problem.

One day, I may create a web app shows the tournament.

## Use

[MIT License](https://github.com/trevorism/Poker/blob/master/LICENSE)

## About me

[trevorism.com](https://trevorism.com)