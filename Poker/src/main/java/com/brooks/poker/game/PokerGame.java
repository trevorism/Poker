/**
 * 
 */
package com.brooks.poker.game;

import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.states.GameStateFactory;
import com.brooks.poker.game.states.GameStateHandler;

/**
 * @author Trevor
 * Play poker, this runs until one player remains.
 */
public class PokerGame{

    public static void playGame(GameState gameState){
        GameStateFactory factory = new GameStateFactory(gameState);
        GamePhase currentPhase = GamePhase.BEGIN_HAND;

        while (!currentPhase.equals(GamePhase.END_GAME)){
            GameStateHandler handler = factory.getStateHandler(currentPhase);
            handler.handleState();
            currentPhase = handler.getNextPhase();
        }
    }

}
