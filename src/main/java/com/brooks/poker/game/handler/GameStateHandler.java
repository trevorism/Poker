package com.brooks.poker.game.handler;

import com.brooks.poker.game.data.GameState;

public interface GameStateHandler {
	
	void handleBeginHandState(GameState gameState);
	
	void handleFirstBetState(GameState gameState);
	
	void handleFlopState(GameState gameState);
	
	void handleFlopBetState(GameState gameState);
	
	void handleTurnState(GameState gameState);
	
	void handleTurnBetState(GameState gameState);
	
	void handleRiverState(GameState gameState);
	
	void handleRiverBetState(GameState gameState);
	
	void handleEndHandState(GameState gameState);
	
	void handleEndGameState(GameState gameState);
}
