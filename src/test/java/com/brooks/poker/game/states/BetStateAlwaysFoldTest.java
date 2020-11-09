package com.brooks.poker.game.states;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.progress.FlopBetState;
import com.brooks.poker.game.progress.TurnBetState;
import com.brooks.poker.outcome.BettingOutcomeFactory;
import com.brooks.poker.outcome.RaiseOutcome;
import com.brooks.poker.player.Player;
import com.brooks.poker.util.PlayerTestSetups;
import com.brooks.poker.util.PokerTestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.brooks.poker.util.PokerTestUtils.assertPlayerChipCount;

public class BetStateAlwaysFoldTest {

    private Player p1;
    private Player p2;
    private Player p3;

    private GameState gameState;
    private FlopBetState flopBetState;
    private TurnBetState turnBetState;

    @Before
    public void setUp(){
        List<Player> players = PlayerTestSetups.allFoldingPlayers();
        gameState = PokerTestUtils.getDefaultGameState(players);

        p1 = PlayerTestSetups.getPlayer1();
        p2 = PlayerTestSetups.getPlayer2();
        p3 = PlayerTestSetups.getPlayer3();

        flopBetState = new FlopBetState(gameState);
        turnBetState = new TurnBetState(gameState);
    }

    @Test
    public void testBettingRound(){
        RaiseOutcome fixedBet40 = BettingOutcomeFactory.createRaiseOutcome(40);
        RaiseOutcome fixedBet50 = BettingOutcomeFactory.createRaiseOutcome(50);

        fixedBet40.modifyGameState(gameState, p3);
        fixedBet50.modifyGameState(gameState, p1);
        flopBetState.bettingRound(p2);

        assertPlayerChipCount(p1, 50);
        assertPlayerChipCount(p2, 120);
        assertPlayerChipCount(p3, 40);

        gameState.getPots().awardWinners();

        assertPlayerChipCount(p1, 140);
        assertPlayerChipCount(p2, 120);
        assertPlayerChipCount(p3, 40);

    }


}
