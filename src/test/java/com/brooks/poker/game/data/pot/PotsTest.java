package com.brooks.poker.game.data.pot;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.data.Table;
import com.brooks.poker.player.Player;
import com.brooks.poker.util.PlayerTestSetups;
import com.brooks.poker.util.PokerTestUtils;

import static com.brooks.poker.util.PlayerTestSetups.PLAYER_ONE_CHIPS;
import static com.brooks.poker.util.PlayerTestSetups.PLAYER_THREE_CHIPS;
import static com.brooks.poker.util.PlayerTestSetups.PLAYER_TWO_CHIPS;
import static com.brooks.poker.util.PlayerTestSetups.getPlayer1;
import static com.brooks.poker.util.PlayerTestSetups.getPlayer2;
import static com.brooks.poker.util.PlayerTestSetups.getPlayer3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PotsTest{

    private Pots pots;
    private Table table;
    
    private Player p1;
    private Player p2;
    private Player p3;

    public static final int BET_AMOUNT = 50;
    public static final int LARGER_CURRENT_BET = 90;

    @Before()
    public void setUp(){
        List<Player> players = PlayerTestSetups.allCallingPlayers();
        GameState gameState = PokerTestUtils.getDefaultGameState(players);

        pots = gameState.getPots();
        table = gameState.getTable();

        p1 = getPlayer1();
        p2 = getPlayer2();
        p3 = getPlayer3();
    }

    @Test
    public void testPutPendingBetsIntoPots(){
        givenEachPlayerBets(BET_AMOUNT);
        whenBetsArePutIntoThePot();
        assertPotValuesAreAccurate();
    }

    @Test
    public void testAwardWinners(){
        givenEachPlayerBets(BET_AMOUNT);
        whenBetsArePutIntoThePot();
        whenWinnerIsAwarded();

        assertPlayersChipsAreUpdated();
    }

    @Test
    public void testInsertSubpot(){
        givenEachPlayerBets(LARGER_CURRENT_BET);
        whenSubpotIsInserted(p3);
        assertSplitSuccessful();
    }
    
    @Test
    public void testMultipleSubpots(){
        givenEachPlayerBets(1000);
        whenSubpotIsInserted(p2);
        whenSubpotIsInserted(p3);
        whenSubpotIsInserted(p1);        
        
        assertMultiSplitSuccessful();
    }

    private void givenEachPlayerBets(int betAmount){
        for (Player player : table.getSortedActivePlayers()){
            player.requestBet(betAmount);
            pots.updateAmountOwed(player.getPendingBet());
        }
    }

    private void whenBetsArePutIntoThePot(){
        pots.putPendingBetsIntoPots(table.getSortedActivePlayers());
    }

    private void assertPotValuesAreAccurate(){
        Pot pot = pots.getPots().get(0);
        assertEquals(0, pot.getAmountOwed());
        assertEquals(table.getSortedActivePlayers().size() * BET_AMOUNT, pot.getPotAmount());
    }
    
    private void whenWinnerIsAwarded(){
        pots.awardWinners();
    }

    private void assertPlayersChipsAreUpdated(){
        int totalWinnings = pots.getPots().get(0).getPotAmount();

        Player p1 = PlayerTestSetups.getPlayer1();
        Player p2 = PlayerTestSetups.getPlayer2();
        Player p3 = PlayerTestSetups.getPlayer3();

        assertEquals(PlayerTestSetups.PLAYER_ONE_CHIPS - BET_AMOUNT, p1.getChipCount());
        assertEquals(PlayerTestSetups.PLAYER_TWO_CHIPS - BET_AMOUNT, p2.getChipCount());
        assertEquals(PlayerTestSetups.PLAYER_THREE_CHIPS - BET_AMOUNT + totalWinnings, p3.getChipCount());
    }
    
    private void whenSubpotIsInserted(Player player){
        pots.insertSubpot(player);
    }

    private void assertSplitSuccessful(){
        assertPotCount(2);
        assertMainPotSplit();
        assertSubPotSplit();
    }

    private void assertMainPotSplit(){
        Pot mainPot = pots.getPots().get(0);
        assertEquals(PLAYER_THREE_CHIPS, mainPot.getAmountOwed());
    }

    private void assertSubPotSplit(){
        Pot subPot = pots.getPots().get(1);
        assertEquals(LARGER_CURRENT_BET - PLAYER_THREE_CHIPS, subPot.getAmountOwed());
    }

    private void assertPotCount(int count){
        assertTrue(pots.getPots().size() == count);
    }

    private void assertMultiSplitSuccessful(){
        assertPotCount(4);
        assertMainPotSplit();
        assertSubPot1Split();
        assertSubPot2Split();
        assertSubPot3Split();
    }

    private void assertSubPot1Split(){
        Pot subPot = pots.getPots().get(1);
        assertEquals(PLAYER_ONE_CHIPS - PLAYER_THREE_CHIPS, subPot.getAmountOwed());        
    }

    private void assertSubPot2Split(){
        Pot subPot = pots.getPots().get(2);
        assertEquals(20, subPot.getAmountOwed());
    }

    private void assertSubPot3Split(){
        Pot subPot = pots.getPots().get(3);
        assertEquals(0, subPot.getAmountOwed());
    }

}
