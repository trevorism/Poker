# Poker
![Jenkins](https://img.shields.io/jenkins/build/http/trevorism-build.eastus.cloudapp.azure.com/poker)
![Jenkins Coverage](https://img.shields.io/jenkins/coverage/jacoco/http/trevorism-build.eastus.cloudapp.azure.com/poker)
![GitHub last commit](https://img.shields.io/github/last-commit/trevorism/poker)
![GitHub language count](https://img.shields.io/github/languages/count/trevorism/poker)
![GitHub top language](https://img.shields.io/github/languages/top/trevorism/poker)

No limit texas hold'em implementation.

Latest version is: 1.8.0

## To Build

`gradlew clean build`

## To Play

Run `main()` in (https://github.com/trevorism/poker-ui/blob/master/src/main/groovy/com/brooks/poker/ui/PlayPoker.groovy)
To get user input, replace `new AlwaysCallPlayerAction()` with `new UserPromptedAction()`

## Code Organization

`Poker` contains the engine for running the game. It's pure java, with no external dependencies! Java version >= 8 will work.

#How to use

See https://github.com/trevorism/Poker-bots for creating your own poker agents.

See https://github.com/trevorism/Poker-ui for a command line game. 

## Future work

Contributions welcome! Submit pull requests.

I built this to test machine learning algorithms on a non-trivial problem.

One day, I may create a web app shows the tournament.

## Use

[MIT License](https://github.com/trevorism/Poker/blob/master/LICENSE)

## About me

[trevorism.com](https://trevorism.com)