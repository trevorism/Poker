package com.brooks.poker.game.states;

import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;

/**
 * @author Trevor
 * 
 */
public class NullHandState extends GameStateHandler{

    public static final NullHandState NULL_HAND_STATE = new NullHandState(null);

    protected NullHandState(GameState gameState){
        super(gameState);
    }

    @Override
    public void handleState(){
    }

    @Override
    public GamePhase getNextPhase(){
        return GamePhase.END_GAME;
    }

}
