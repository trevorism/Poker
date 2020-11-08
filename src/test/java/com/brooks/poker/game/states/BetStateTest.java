package com.brooks.poker.game.states;

import static com.brooks.poker.util.PlayerTestSetups.getPlayer1;
import static com.brooks.poker.util.PlayerTestSetups.getPlayer2;
import static com.brooks.poker.util.PlayerTestSetups.getPlayer3;
import static com.brooks.poker.util.PlayerTestSetups.programmaticPlayers;
import static com.brooks.poker.util.PokerTestUtils.assertPlayerChipCount;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.brooks.poker.game.GameActions;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.data.pot.Pot;
import com.brooks.poker.game.data.pot.Pots;
import com.brooks.poker.game.progress.BetState;
import com.brooks.poker.game.progress.FlopBetState;
import com.brooks.poker.outcome.BettingOutcomeFactory;
import com.brooks.poker.outcome.RaiseOutcome;
import com.brooks.poker.player.Player;
import com.brooks.poker.util.PokerTestUtils;

/**
 * @author Trevor
 *
 */
public class BetStateTest{

    private GameState gameState;
    private BetState betState;

    private Player p1;
    private Player p2;
    private Player p3;

    private int p3TotalChips;

    @Before
    public void setUp(){
        List<Player> players = programmaticPlayers();
        gameState = PokerTestUtils.getDefaultGameState(players);

        p1 = getPlayer1();
        p2 = getPlayer2();
        p3 = getPlayer3();

        p3TotalChips = p3.getChipCount();

        betState = new FlopBetState(gameState);
    }
    
    @Test
    public void testBettingRoundWithAllInPlayers(){
        gameState.getTable().setDealer(p2);
        RaiseOutcome fixedBet = BettingOutcomeFactory.createRaiseOutcome(300);

        fixedBet.modifyGameState(gameState, p3);
        fixedBet.modifyGameState(gameState, p1);
        fixedBet.modifyGameState(gameState, p2);
        GameActions.endBettingRound(gameState);
        
        assertPlayerBets();
        assertPotsCount(3);
        assertMainPot();
        assertSubPot1();
        assertSubPot2();

        betState.bettingRound(p3);
    }

    private void assertPlayerBets(){
        assertPlayerChipCount(p1, 0);
        assertPlayerChipCount(p2, 20);
        assertPlayerChipCount(p3, 0);
        
    }

    private void assertPotsCount(int count){
        Pots pots = gameState.getPots();
        assertEquals(count, pots.getPots().size());
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
