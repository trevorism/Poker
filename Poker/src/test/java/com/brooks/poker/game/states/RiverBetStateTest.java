package com.brooks.poker.game.states;

import junit.framework.Assert;

import org.junit.Test;

import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.util.PlayerTestSetups;
import com.brooks.poker.util.PokerTestUtils;

public class RiverBetStateTest{

    @Test
    public void testGetNextPhase(){
        RiverBetState rbs = new RiverBetState(PokerTestUtils.getDefaultGameState(PlayerTestSetups.allCallingPlayers()));        
        Assert.assertEquals(GamePhase.END_HAND, rbs.getNextPhase());
    }

}
