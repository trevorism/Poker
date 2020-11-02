package com.brooks.poker.game.data.pot;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.brooks.poker.player.Player;
import com.brooks.poker.player.action.NullPlayerAction;
import com.brooks.poker.util.PlayerTestSetups;

import static com.brooks.poker.util.PokerTestUtils.assertPlayerChipCount;
import static org.junit.Assert.*;
/**
 * @author Trevor
 *
 */
public class PotResultTest{
    public static final int POT_AMOUNT = 60;

    private PotResult potResult;
    
    private Player p1;
    private Player p2;
    private Player p3;
    private Player p4;

    private int p1TotalChips;
    private int p2TotalChips;
    private int p3TotalChips;
    private int p4TotalChips;
    
    private List<Player> players;
    
    @Before()
    public void setUp(){         
        players = PlayerTestSetups.allCallingPlayers();

        p1 = PlayerTestSetups.getPlayer1();
        p2 = PlayerTestSetups.getPlayer2();
        p3 = PlayerTestSetups.getPlayer3();
        
        p1TotalChips = p1.getChipCount();
        p2TotalChips = p2.getChipCount();
        p3TotalChips = p3.getChipCount();
        p4TotalChips = POT_AMOUNT;
        
        p4 = new Player("p4", p4TotalChips, NullPlayerAction.INSTANCE);
        PlayerTestSetups.givePlayerTrips(p4);
        
        potResult = new PotResult();        
    }
    
    @Test
    public void testResolvePot(){
        Pot pot = setupThreePlayerPot(POT_AMOUNT);
        potResult.resolvePot(pot);
        
        assertWinnerCount(1);
        
        assertPlayerChipCount(p1, p1TotalChips);
        assertPlayerChipCount(p2, p2TotalChips);
        assertPlayerChipCount(p3, p3TotalChips + POT_AMOUNT);
        
    }  

    @Test
    public void testSplitEvenPot(){
        Pot pot = setupFourPlayerPot(POT_AMOUNT);
        potResult.resolvePot(pot);

        assertWinnerCount(2);
        
        assertPlayerChipCount(p1, p1TotalChips);
        assertPlayerChipCount(p2, p2TotalChips);
        assertPlayerChipCount(p3, p3TotalChips + POT_AMOUNT/2);
        assertPlayerChipCount(p4, p4TotalChips + POT_AMOUNT/2);

        
    }

    @Test
    public void testSplitUnevenPot(){
        int potAmount = POT_AMOUNT + 1;        
        Pot pot = setupFourPlayerPot(potAmount);
        potResult.resolvePot(pot);

        assertWinnerCount(2);

        assertPlayerChipCount(p1, p1TotalChips);
        assertPlayerChipCount(p2, p2TotalChips);
        assertTrue(p3.getChipCount() == p3TotalChips + potAmount/2 || p3.getChipCount() == p3TotalChips + potAmount/2 + 1);
        assertTrue(p4.getChipCount() == p4TotalChips + potAmount/2 || p4.getChipCount() == p4TotalChips + potAmount/2 + 1);
    }
    
    private Pot setupThreePlayerPot(int potAmount){
        Pot pot = new Pot(players);        
        pot.placeBet(potAmount);
        return pot;
    }
    
    private Pot setupFourPlayerPot(int potAmount){
        players.add(p4);
        Pot pot = new Pot(players);        
        pot.placeBet(potAmount);
        return pot;
    }

    private void assertWinnerCount(int count){
        List<PotWinner> potWinners = potResult.getPotWinners();
        assertEquals(count, potWinners.size());
      
    }
    
}
