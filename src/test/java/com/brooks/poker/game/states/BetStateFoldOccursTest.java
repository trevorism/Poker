package com.brooks.poker.game.states;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.progress.BetState;
import com.brooks.poker.game.progress.FlopBetState;
import com.brooks.poker.player.Player;
import com.brooks.poker.player.action.AlwaysRaisePlayerAction;
import com.brooks.poker.util.PlayerTestSetups;
import com.brooks.poker.util.PokerTestUtils;

import static com.brooks.poker.util.PokerTestUtils.assertPlayerChipCount;

/**
 * @author Trevor
 * 
 */
public class BetStateFoldOccursTest{

    private GameState gameState;
    private BetState flopBetState;

    private Player p1;
    private Player p2;
    private Player p3;

    private int p1TotalChips;
    private int p2TotalChips;
    private int p3TotalChips;

    @Before
    public void setUp(){
        List<Player> players = PlayerTestSetups.differentActions();
        gameState = PokerTestUtils.getDefaultGameState(players);

        p1 = PlayerTestSetups.getPlayer1();
        p2 = PlayerTestSetups.getPlayer2();
        p3 = PlayerTestSetups.getPlayer3();

        p1TotalChips = p1.getChipCount();
        p2TotalChips = p2.getChipCount();
        p3TotalChips = p3.getChipCount();

        flopBetState = new FlopBetState(gameState);
    }

    @Test
    public void testBettingRound(){
        flopBetState.handleState();

        assertPlayerChipCount(p1, p1TotalChips - AlwaysRaisePlayerAction.RAISE_AMOUNT);
        assertPlayerChipCount(p2, p2TotalChips - AlwaysRaisePlayerAction.RAISE_AMOUNT);
        assertPlayerChipCount(p3, p3TotalChips);

    }

    @Test
    public void testBettingRoundWithP2Dealer(){
        gameState.getTable().setDealer(p2);
        flopBetState.handleState();

        assertPlayerChipCount(p1, p1TotalChips - AlwaysRaisePlayerAction.RAISE_AMOUNT);
        assertPlayerChipCount(p2, p2TotalChips - AlwaysRaisePlayerAction.RAISE_AMOUNT);
        assertPlayerChipCount(p3, p3TotalChips);

    }

    @Test
    public void testBettingRoundWithP3Dealer() {
        gameState.getTable().setDealer(p3);
        flopBetState.handleState();

        assertPlayerChipCount(p1, p1TotalChips - AlwaysRaisePlayerAction.RAISE_AMOUNT);
        assertPlayerChipCount(p2, p2TotalChips - AlwaysRaisePlayerAction.RAISE_AMOUNT);
        assertPlayerChipCount(p3, p3TotalChips);

    }
}
