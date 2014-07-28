package com.brooks.poker.game.handler;

import com.brooks.poker.game.data.GameState;

public interface GameStateHandler {
	
	public void handleBeginHandState(GameState gameState);
	
	public void handleFirstBetState(GameState gameState);
	
	public void handleFlopState(GameState gameState);
	
	public void handleFlopBetState(GameState gameState);
	
	public void handleTurnState(GameState gameState);
	
	public void handleTurnBetState(GameState gameState);
	
	public void handleRiverState(GameState gameState);
	
	public void handleRiverBetState(GameState gameState);
	
	public void handleEndHandState(GameState gameState);
	
	public void handleEndGameState(GameState gameState);
}
