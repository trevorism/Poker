package com.brooks.poker.game.states;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.data.pot.Pot;
import com.brooks.poker.game.data.pot.Pots;
import com.brooks.poker.game.progress.FlopBetState;
import com.brooks.poker.outcome.CallOutcome;
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
public class BetStateCallThenAllInTest{

    private GameState gameState;
    private FlopBetState flopBetState;

    private Player p1;
    private Player p2;
    private Player p3;

    private static final int INITIAL_BET = 30;
    private static final int BET_AMOUNT = 90;

    @Before
    public void setUp(){
        List<Player> players = PlayerTestSetups.allCallingPlayers();
        gameState = PokerTestUtils.getDefaultGameState(players);
        
        p1 = PlayerTestSetups.getPlayer1();
        p2 = PlayerTestSetups.getPlayer2();
        p3 = PlayerTestSetups.getPlayer3();

        flopBetState = new FlopBetState(gameState);
    }
    
    @Test
    public void testCallThenAllIn(){
        gameState.getTable().setDealer(p2);
        RaiseOutcome fixedBet40 = BettingOutcomeFactory.createRaiseOutcome(INITIAL_BET);
        CallOutcome call = BettingOutcomeFactory.createCallOutcome();
        RaiseOutcome fixedBet50 = BettingOutcomeFactory.createRaiseOutcome(BET_AMOUNT);
  
        fixedBet40.modifyGameState(gameState, p3);
        call.modifyGameState(gameState, p1);
        fixedBet50.modifyGameState(gameState, p2);
        flopBetState.bettingRound(p3);

        assertPlayerBets();
        assertTwoPotsExist();
        assertMainPot();
        assertSubPot();
    }
    
    private void assertPlayerBets(){
        assertPlayerChipCount(p1, PlayerTestSetups.PLAYER_ONE_CHIPS - BET_AMOUNT);
        assertPlayerChipCount(p2, PlayerTestSetups.PLAYER_TWO_CHIPS - BET_AMOUNT);
        assertPlayerChipCount(p3, 0);
    }

    private void assertTwoPotsExist(){
        Pots pots = gameState.getPots();
        assertEquals(2, pots.getPots().size());
    }

    private void assertMainPot(){
        Pots pots = gameState.getPots();
        Pot mainPot = pots.getPots().get(0);

        assertEquals(0, mainPot.getAmountOwed());
        assertEquals(PlayerTestSetups.PLAYER_THREE_CHIPS * 3, mainPot.getPotAmount());

    }

    private void assertSubPot(){
        Pots pots = gameState.getPots();
        Pot subPot = pots.getPots().get(1);

        assertEquals(0, subPot.getAmountOwed());
        assertEquals(20, subPot.getPotAmount());
    }

}
