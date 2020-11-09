package com.brooks.poker.game.states;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.progress.FirstBetState;
import com.brooks.poker.game.progress.FlopBetState;
import com.brooks.poker.outcome.BettingOutcome;
import com.brooks.poker.outcome.BettingOutcomeFactory;
import com.brooks.poker.player.Player;
import com.brooks.poker.player.action.AlwaysFoldPlayerAction;
import com.brooks.poker.player.action.AlwaysRaisePlayerAction;
import com.brooks.poker.player.action.PlayerAction;
import com.brooks.poker.player.action.ProgrammaticPlayerAction;
import com.brooks.poker.util.PlayerTestSetups;
import com.brooks.poker.util.PokerTestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.brooks.poker.util.PokerTestUtils.assertPlayerChipCount;

public class BetStateTwoPlayersAllInAndFold {

    private Player p1;
    private Player p2;
    private Player p3;

    private GameState gameState;
    private FirstBetState firstBetState;

    @Before
    public void setUp() {
        List<Player> players = Arrays.asList(new Player("p1", 20, new AlwaysFoldPlayerAction()),
                new Player("p2", 20, new AlwaysFoldPlayerAction()),
                new Player("p3", 20, new AlwaysFoldPlayerAction()));
        gameState = PokerTestUtils.getDefaultGameState(players);

        p1 = players.get(0);
        p2 = players.get(1);
        p3 = players.get(2);

        firstBetState = new FirstBetState(gameState);
    }

    @Test
    public void testBettingRound() {
        firstBetState.handleState();

        assertPlayerChipCount(p1, 20);
        assertPlayerChipCount(p2, 0);
        assertPlayerChipCount(p3, 0);

        gameState.getPots().awardWinners();

        assertPlayerChipCount(p1, 20);
        assertPlayerChipCount(p2, 20);
        assertPlayerChipCount(p3, 20);

    }

}
