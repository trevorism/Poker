package com.brooks.poker.game.data;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.brooks.poker.command.PlayerCommand;
import com.brooks.poker.player.Player;
import com.brooks.poker.util.PlayerTestSetups;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TableTest{

    private List<Player> playersInOrder;
    private Table table;

    private Player p1;
    private Player p2;
    private Player p3;
    
    @Before
    public void setUp(){
        playersInOrder = PlayerTestSetups.allCallingPlayers();
        table = createTable(playersInOrder);
    
        p1 = PlayerTestSetups.getPlayer1();
        p2 = PlayerTestSetups.getPlayer2();
        p3 = PlayerTestSetups.getPlayer3();
    }
    
    @Test
    public void testGetNextActivePlayer(){
        assertFirstPlayerIsBeforeSecondPlayer(Player.NOBODY, Player.NOBODY);
        assertFirstPlayerIsBeforeSecondPlayer(p1, p2);
        assertFirstPlayerIsBeforeSecondPlayer(p3, p1);

        table.makeInactive(p1);
        assertFirstPlayerIsBeforeSecondPlayer(p3, p2);
    }

    @Test
    public void testMakeNextPlayerDealer(){
        table.setDealer(p1);
        assertPlayerIsDealer(p1);

        table.makeNextPlayerDealer();
        assertPlayerIsDealer(p2);

        table.makeNextPlayerDealer();
        assertPlayerIsDealer(p3);

        table.makeNextPlayerDealer();
        assertPlayerIsDealer(p1);
    }

    @Test
    public void testExecuteOnEachPlayer(){
        assertEachActivePlayerIsTouched();        
    }

    @Test
    public void testExecuteOnActivePlayers(){
        table.makeInactive(p3);
        assertPlayersInOrderContainsInactives();
    }
    
    @Test
    public void testRandomizeDealer(){
        table.randomizeDealer();
        assertNotNull(table.getDealer());
        table.removePlayer(table.getDealer());
        table.randomizeDealer();
        assertNotNull(table.getDealer());
        table.removePlayer(table.getDealer());
        table.randomizeDealer();        
        assertNotNull(table.getDealer());
    }

    private Table createTable(List<Player> players){
        Table table = new Table();
        for (Player player : players){
            table.joinTable(player);
        }
        return table;
    }
    
    private void assertFirstPlayerIsBeforeSecondPlayer(Player beforePlayer, Player afterPlayer){
        assertEquals(afterPlayer, table.getNextActivePlayer(beforePlayer));
    }

    private void assertPlayerIsDealer(Player player){
        assertEquals(player, table.getDealer());
    }
    
    private void assertEachActivePlayerIsTouched(){
        table.executeOnEachActivePlayer(p1, player -> {
            assertEquals(playersInOrder.get(0), player);
            playersInOrder.remove(0);
        });
        assertPlayersInOrderIsEmpty();
    }

    private void assertPlayersInOrderIsEmpty(){
        assertEquals(0, playersInOrder.size());
    }

    private void assertPlayersInOrderContainsInactives(){
        assertTrue(table.getAllPlayers().contains(p3));
    }

}
