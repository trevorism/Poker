package com.brooks.poker.game.data.pot;

import com.brooks.poker.player.Player;

/**
 * @author Trevor
 *
 */

public class PotWinner{
    public Player player = Player.NOBODY;
    public int amount = 0;
    
    public PotWinner(Player player, int amount){
        this.player = player;
        this.amount = amount;
    }    
    
}