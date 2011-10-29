/**
 * 
 */
package com.brooks.poker.game;

import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.states.GameStateFactory;
import com.brooks.poker.game.states.GameStateHandler;

/**
 * @author Trevor This class launches and handles game level logic ( how many
 *         players etc.)
 */
public class PokerGame{

    public void playGame(GameState gameState){
        GameStateFactory factory = new GameStateFactory(gameState);
        GamePhase currentPhase = GamePhase.BEGIN_HAND;

        while (!currentPhase.equals(GamePhase.END_GAME)){
            GameStateHandler handler = factory.getStateHandler(currentPhase);
            handler.handleState();
            currentPhase = handler.getNextPhase();
        }
    }
}
