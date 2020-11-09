package com.brooks.poker.game.states;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.progress.FirstBetState;
import com.brooks.poker.player.Player;
import com.brooks.poker.player.action.AlwaysCallPlayerAction;
import com.brooks.poker.player.action.AlwaysFoldPlayerAction;
import com.brooks.poker.util.PokerTestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.brooks.poker.util.PokerTestUtils.assertPlayerChipCount;

public class BetStateTwoPlayersAllInAndCall {

    private Player p1;
    private Player p2;

    private GameState gameState;
    private FirstBetState firstBetState;

    @Before
    public void setUp() {
        List<Player> players = Arrays.asList(new Player("p1", 50, new AlwaysFoldPlayerAction()),
                new Player("p2", 20, new AlwaysCallPlayerAction()));
        gameState = PokerTestUtils.getDefaultGameState(players);

        p1 = players.get(0);
        p2 = players.get(1);

        firstBetState = new FirstBetState(gameState);
    }

    @Test
    public void testBettingRoundWithPlayer1Dealer() {
        gameState.getTable().setDealer(p1);
        firstBetState.handleState();

        assertPlayerChipCount(p1, 15);
        assertPlayerChipCount(p2, 0);

        gameState.getPots().awardWinners();

        assertPlayerChipCount(p1, 50);
        assertPlayerChipCount(p2, 20);
    }


    @Test
    public void testBettingRoundWithPlayer2Dealer() {
        gameState.getTable().setDealer(p2);
        firstBetState.handleState();

        assertPlayerChipCount(p1, 25);
        assertPlayerChipCount(p2, 0);

        gameState.getPots().awardWinners();

        assertPlayerChipCount(p1, 50);
        assertPlayerChipCount(p2, 20);
    }
}
