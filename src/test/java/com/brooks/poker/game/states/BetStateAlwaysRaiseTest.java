package com.brooks.poker.game.states;

import com.brooks.poker.game.BettingRound;
import com.brooks.poker.game.GameActions;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.progress.FlopBetState;
import com.brooks.poker.game.progress.TurnBetState;
import com.brooks.poker.player.Player;
import com.brooks.poker.util.PlayerTestSetups;
import com.brooks.poker.util.PokerTestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.brooks.poker.util.PokerTestUtils.assertPlayerChipCount;

public class BetStateAlwaysRaiseTest {

    private Player p1;
    private Player p2;
    private Player p3;

    private int p1TotalChips;
    private int p2TotalChips;
    private int p3TotalChips;

    private GameState gameState;
    private FlopBetState flopBetState;
    private TurnBetState turnBetState;

    @Before
    public void setUp(){
        List<Player> players = PlayerTestSetups.allRaisingPlayers();
        gameState = PokerTestUtils.getDefaultGameState(players);

        p1 = PlayerTestSetups.getPlayer1();
        p2 = PlayerTestSetups.getPlayer2();
        p3 = PlayerTestSetups.getPlayer3();

        p1TotalChips = p1.getChipCount();
        p2TotalChips = p2.getChipCount();
        p3TotalChips = p3.getChipCount();

        flopBetState = new FlopBetState(gameState);
        turnBetState = new TurnBetState(gameState);
    }

    @Test
    public void testBettingRound(){
        gameState.getTable().setDealer(p3);
        flopBetState.bettingRound(p1);

        assertPlayerChipCount(p1, 0);
        assertPlayerChipCount(p2, 20);
        assertPlayerChipCount(p3, 0);

        Player firstToAct = GameActions.getPlayerAfterDealer(gameState);
        turnBetState.bettingRound(firstToAct);

        assertPlayerChipCount(p1, 0);
        assertPlayerChipCount(p2, 20);
        assertPlayerChipCount(p3, 0);

    }
}
