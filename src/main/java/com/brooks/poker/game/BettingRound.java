package com.brooks.poker.game;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.data.Table;
import com.brooks.poker.game.data.pot.Pot;
import com.brooks.poker.player.Player;

import java.util.HashMap;
import java.util.Map;

public class BettingRound {

    private final GameState gameState;
    private final Player startPlayer;
    private final Map<String, Boolean> hasPlayerHadATurn;

    public BettingRound(GameState gameState, Player startPlayer, boolean hasStartPlayerBet) {
        this.gameState = gameState;
        this.startPlayer = startPlayer;
        this.hasPlayerHadATurn = createActionMap();
        if (hasStartPlayerBet) {
            actionComplete(startPlayer);
        }
    }

    public boolean isComplete() {
        if(hasPlayerHadATurn.size() <= 1 && gameState.getPots().getCurrentBet() == startPlayer.getPendingBet()){
            return true;
        }

        if(isOnlyOneEligiblePlayerLeft(gameState)){
            return true;
        }

        for (Boolean playerFinished : hasPlayerHadATurn.values()) {
            if (!playerFinished)
                return false;
        }
        return true;
    }

    public static boolean isOnlyOneEligiblePlayerLeft(GameState gameState) {
        for(Pot pot : gameState.getPots().getPots()){
            if(pot.getEligiblePlayerCount() > 1)
                return false;
        }
        return true;
    }

    public void actionComplete(Player player) {
        hasPlayerHadATurn.put(player.getName(), true);
    }

    private Map<String, Boolean> createActionMap() {
        Table table = gameState.getTable();
        HashMap<String, Boolean> map = new HashMap<>();
        for (Player player : table.getSortedActivePlayers()) {
            if(!player.isAllIn()) {
                map.put(player.getName(), false);
            }
        }
        return map;
    }

}
