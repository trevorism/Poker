package com.brooks.poker.game;

import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.progress.GameProgressHandler;
import com.brooks.poker.game.progress.GameStateFactory;

/**
 * @author Trevor
 * Play poker, this runs until one player remains.
 */
public class PokerGame {

    public static void playGame(GameState gameState) {

        GameStateFactory factory = new GameStateFactory(gameState);
        GamePhase currentPhase = GamePhase.BEGIN_HAND;

        while (!currentPhase.equals(GamePhase.END_GAME)) {
            gameState.setGamePhase(currentPhase);
            GameProgressHandler handler = factory.getStateHandler(currentPhase);

            handler.handleState();
            gameState.invokeGameStateHandlerFor(currentPhase);

            if (GameActions.invalid(gameState)) {
                currentPhase = GamePhase.END_GAME;
            } else {
                currentPhase = handler.getNextPhase();
            }

        }
        gameState.invokeGameStateHandlerFor(currentPhase);
    }

}
