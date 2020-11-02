package com.brooks.poker.game.handler;

import java.util.ArrayList;
import java.util.List;

import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;

public class GameStateHandlerCollection {

    private final List<GameStateHandler> gameStateHandlers = new ArrayList<GameStateHandler>();
	private final GameState gameState;

    public GameStateHandlerCollection(GameState gameState){
    	this.gameState = gameState;
    }
    
	public void add(GameStateHandler handler) {
		gameStateHandlers.add(handler);		
	}

	public void handlePhase(GamePhase currentPhase) {
		switch(currentPhase){
			case BEGIN_HAND:
				gameStateHandlers.forEach(handler -> handler.handleBeginHandState(gameState));
				break;
			case FIRST_BET:
				gameStateHandlers.forEach(handler -> handler.handleFirstBetState(gameState));
				break;
			case FLOP:
				gameStateHandlers.forEach(handler -> handler.handleFlopState(gameState));
				break;
			case FLOP_BET:
				gameStateHandlers.forEach(handler -> handler.handleFlopBetState(gameState));
				break;
			case TURN:
				gameStateHandlers.forEach(handler -> handler.handleTurnState(gameState));
				break;
			case TURN_BET:
				gameStateHandlers.forEach(handler -> handler.handleTurnBetState(gameState));
				break;
			case RIVER:
				gameStateHandlers.forEach(handler -> handler.handleRiverState(gameState));
				break;
			case RIVER_BET:
				gameStateHandlers.forEach(handler -> handler.handleRiverBetState(gameState));
				break;
			case END_HAND:
				gameStateHandlers.forEach(handler -> handler.handleEndHandState(gameState));
				break;
			case END_GAME:
				gameStateHandlers.forEach(handler -> handler.handleEndGameState(gameState));
				break;				
		}
		
	}
	
}
