package com.brooks.poker.game.states;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.data.pot.Pot;
import com.brooks.poker.game.data.pot.Pots;
import com.brooks.poker.game.progress.FlopBetState;
import com.brooks.poker.outcome.RaiseOutcome;
import com.brooks.poker.outcome.BettingOutcomeFactory;
import com.brooks.poker.player.Player;
import com.brooks.poker.util.PlayerTestSetups;
import com.brooks.poker.util.PokerTestUtils;

import static com.brooks.poker.util.PokerTestUtils.assertPlayerChipCount;
import static org.junit.Assert.assertEquals;

/**
 * @author Trevor
 * 
 */
public class BetStateAllInTest{

    private GameState gameState;
    private FlopBetState flopBetState;

    private Player p1;
    private Player p2;
    private Player p3;

    private int p1TotalChips;
    private int p2TotalChips;
    private int p3TotalChips;

    private static final int BET_AMOUNT = 100;

    @Before
    public void setUp(){
        List<Player> players = PlayerTestSetups.allCallingPlayers();
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
    public void testAllInPlayer1(){
        gameState.getTable().setDealer(p3);
        RaiseOutcome fixedBet = BettingOutcomeFactory.createRaiseOutcome(BET_AMOUNT);
        fixedBet.modifyGameState(gameState, p1);
        flopBetState.bettingRound(p2);

        assertPlayerBets();
        assertPotsExist(3);
        assertMainPot();
        assertSubPot1();
        assertSubPot2();
    }

    @Test
    public void testAllInPlayer2(){
        gameState.getTable().setDealer(p1);
        RaiseOutcome fixedBet = BettingOutcomeFactory.createRaiseOutcome(BET_AMOUNT);
        fixedBet.modifyGameState(gameState, p2);
        flopBetState.bettingRound(p2);

        assertPlayerBets();
        assertPotsExist(3);
        assertMainPot();
        assertSubPot1();
        assertSubPot2();

    }

    @Test
    public void testAllInPlayer3(){
        gameState.getTable().setDealer(p2);
        RaiseOutcome fixedBet = BettingOutcomeFactory.createRaiseOutcome(BET_AMOUNT);
        fixedBet.modifyGameState(gameState, p3);
        fixedBet.modifyGameState(gameState, p1);
        flopBetState.bettingRound(p2);

        assertPlayerBets();
        assertPotsExist(3);
        assertMainPot();
        assertSubPot1();
        assertSubPot2();

    }

    
    private void assertPlayerBets(){
        assertPlayerChipCount(p1, p1TotalChips - BET_AMOUNT);
        assertPlayerChipCount(p2, p2TotalChips - BET_AMOUNT);
        assertPlayerChipCount(p3, 0);
    }

    private void assertPotsExist(int amount){
        Pots pots = gameState.getPots();
        assertEquals(amount, pots.getPots().size());
    }

    private void assertMainPot(){
        Pots pots = gameState.getPots();
        Pot mainPot = pots.getPots().get(0);

        assertEquals(0, mainPot.getAmountOwed());
        assertEquals(p3TotalChips * 3, mainPot.getPotAmount());

    }

    private void assertSubPot1(){
        Pots pots = gameState.getPots();
        Pot subPot = pots.getPots().get(1);

        assertEquals(0, subPot.getAmountOwed());
        assertEquals(40, subPot.getPotAmount());
    }

    private void assertSubPot2(){
        Pots pots = gameState.getPots();
        Pot subPot = pots.getPots().get(2);

        assertEquals(0, subPot.getAmountOwed());
        assertEquals(0, subPot.getPotAmount());
    }

}
