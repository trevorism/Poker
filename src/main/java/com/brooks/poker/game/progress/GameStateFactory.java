/**
 * 
 */
package com.brooks.poker.game.progress;

import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;

/**
 * @author Trevor
 * 
 */
public class GameStateFactory{

    private BeginHandState beginHandState;
    private FirstBetState firstBetState;
    private FlopState flopState;
    private FlopBetState flopBetState;
    private TurnState turnState;
    private TurnBetState turnBetState;
    private RiverState riverState;
    private RiverBetState riverBetState;
    private EndHandState endHandState;

    public GameStateFactory(GameState gameState){
        beginHandState = new BeginHandState(gameState);
        firstBetState = new FirstBetState(gameState);
        flopState = new FlopState(gameState);
        flopBetState = new FlopBetState(gameState);
        turnState = new TurnState(gameState);
        turnBetState = new TurnBetState(gameState);
        riverState = new RiverState(gameState);
        riverBetState = new RiverBetState(gameState);
        endHandState = new EndHandState(gameState);
    }

    public GameProgressHandler getStateHandler(GamePhase cs){
        switch (cs) {
            case BEGIN_HAND:
                return beginHandState;
            case FIRST_BET:
                return firstBetState;
            case FLOP:
                return flopState;
            case FLOP_BET:
                return flopBetState;
            case TURN:
                return turnState;
            case TURN_BET:
                return turnBetState;
            case RIVER:
                return riverState;
            case RIVER_BET:
                return riverBetState;
            case END_HAND:
                return endHandState;
            case END_GAME:
                return NullHandState.NULL_HAND_STATE;
        }
        return NullHandState.NULL_HAND_STATE;
    }

}
