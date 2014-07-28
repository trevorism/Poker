package com.brooks.poker.game.states;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.progress.BetState;
import com.brooks.poker.game.progress.FirstBetState;
import com.brooks.poker.player.Player;
import com.brooks.poker.util.PlayerTestSetups;
import com.brooks.poker.util.PokerTestUtils;

import static com.brooks.poker.util.PokerTestUtils.assertPlayerChipCount;

/**
 * @author Trevor
 *
 */
public class BetStateBlindsWereBetTest{

    private GameState gameState;
    private BetState firstBetState;

    private Player p1;
    private Player p2;
    private Player p3;

    @Before
    public void setUp(){
        List<Player> players = PlayerTestSetups.differentActions();
        gameState = PokerTestUtils.getDefaultGameState(players);

        p1 = PlayerTestSetups.getPlayer1();
        p2 = PlayerTestSetups.getPlayer2();
        p3 = PlayerTestSetups.getPlayer3();
        
        firstBetState = new FirstBetState(gameState);
    }
    
    @Test
    public void testBettingRound(){
        firstBetState.handleState();
        
        assertPlayerChipCount(p1, PlayerTestSetups.PLAYER_ONE_CHIPS - 70);
        assertPlayerChipCount(p2, PlayerTestSetups.PLAYER_TWO_CHIPS - 70);
        assertPlayerChipCount(p3, PlayerTestSetups.PLAYER_THREE_CHIPS - 35);

    }
}
