package com.brooks.poker.util;

import java.util.LinkedList;
import java.util.List;

import com.brooks.poker.cards.Card;
import com.brooks.poker.cards.Card.Suit;
import com.brooks.poker.cards.Card.Value;
import com.brooks.poker.player.Player;
import com.brooks.poker.player.action.AlwaysCallPlayerAction;
import com.brooks.poker.player.action.AlwaysFoldPlayerAction;
import com.brooks.poker.player.action.AlwaysRaisePlayerAction;
import com.brooks.poker.player.action.PlayerAction;
import com.brooks.poker.player.action.ProgrammaticPlayerAction;

/**
 * @author Trevor
 *
 */
public final class PlayerTestSetups{
    public static final int PLAYER_ONE_CHIPS = 100;
    public static final int PLAYER_TWO_CHIPS = 120;
    public static final int PLAYER_THREE_CHIPS = 80;

    private static Player player1;
    private static Player player2;
    private static Player player3;
    
    private PlayerTestSetups(){}

    public static List<Player> programmaticPlayers(){
        player1 = new Player("p1", PLAYER_ONE_CHIPS, new ProgrammaticPlayerAction());
        player2 = new Player("p2", PLAYER_TWO_CHIPS, new ProgrammaticPlayerAction());
        player3 = new Player("p3", PLAYER_THREE_CHIPS, new ProgrammaticPlayerAction());

        givePlayerTwoPair(player1);
        givePlayerHighCard(player2);
        givePlayerTrips(player3);
        
        List<Player> players = new LinkedList<Player>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        
        return players;
    }
    
    public static List<Player> differentActions(){
        player1 = new Player("p1", PLAYER_ONE_CHIPS, new AlwaysRaisePlayerAction());
        player2 = new Player("p2", PLAYER_TWO_CHIPS, new AlwaysCallPlayerAction());
        player3 = new Player("p3", PLAYER_THREE_CHIPS, new AlwaysFoldPlayerAction());

        givePlayerTwoPair(player1);
        givePlayerHighCard(player2);
        givePlayerTrips(player3);
        
        List<Player> players = new LinkedList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        
        return players;
    }

    public static List<Player> allCallingPlayers(){
        player1 = new Player("p1", PLAYER_ONE_CHIPS, new AlwaysCallPlayerAction());
        player2 = new Player("p2", PLAYER_TWO_CHIPS, new AlwaysCallPlayerAction());
        player3 = new Player("p3", PLAYER_THREE_CHIPS, new AlwaysCallPlayerAction());

        givePlayerTwoPair(player1);
        givePlayerHighCard(player2);
        givePlayerTrips(player3);
        
        List<Player> players = new LinkedList<Player>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        
        return players;
    }

    public static List<Player> allRaisingPlayers(){
        player1 = new Player("p1", PLAYER_ONE_CHIPS, new AlwaysRaisePlayerAction());
        player2 = new Player("p2", PLAYER_TWO_CHIPS, new AlwaysRaisePlayerAction());
        player3 = new Player("p3", PLAYER_THREE_CHIPS, new AlwaysRaisePlayerAction());

        givePlayerTwoPair(player1);
        givePlayerHighCard(player2);
        givePlayerTrips(player3);

        List<Player> players = new LinkedList<Player>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        return players;
    }

    public static List<Player> allFoldingPlayers(){
        player1 = new Player("p1", PLAYER_ONE_CHIPS, new AlwaysFoldPlayerAction());
        player2 = new Player("p2", PLAYER_TWO_CHIPS, new AlwaysFoldPlayerAction());
        player3 = new Player("p3", PLAYER_THREE_CHIPS, new AlwaysFoldPlayerAction());

        givePlayerTwoPair(player1);
        givePlayerHighCard(player2);
        givePlayerTrips(player3);

        List<Player> players = new LinkedList<Player>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        return players;
    }

    public static Player getPlayer1(){
        return player1;
    }

    public static Player getPlayer2(){
        return player2;
    }

    public static Player getPlayer3(){
        return player3;
    }
    
    public static void programPlayerAction(Player player, PlayerAction playerAction){
        PlayerAction action = player.getAction();
        
        if(!(action instanceof ProgrammaticPlayerAction))
            throw new RuntimeException("Player Action cannot be programmed");
        
        ProgrammaticPlayerAction ppa = (ProgrammaticPlayerAction)action;
        ppa.setPlayerAction(playerAction);
    }
    
    public static void givePlayerTrips(Player player){
        player.getHand().reset();
        player.addCard(new Card(Suit.CLUBS, Value.ACE));
        player.addCard(new Card(Suit.DIAMONDS, Value.ACE));
        player.addCard(new Card(Suit.HEARTS, Value.ACE));
        player.addCard(new Card(Suit.CLUBS, Value.KING));
        player.addCard(new Card(Suit.CLUBS, Value.QUEEN));
    }

    public static void givePlayerHighCard(Player player){
        player.getHand().reset();
        player.addCard(new Card(Suit.CLUBS, Value.FOUR));
        player.addCard(new Card(Suit.DIAMONDS, Value.ACE));
        player.addCard(new Card(Suit.HEARTS, Value.SIX));
        player.addCard(new Card(Suit.CLUBS, Value.KING));
        player.addCard(new Card(Suit.CLUBS, Value.QUEEN));
    }

    public static void givePlayerTwoPair(Player player){
        player.getHand().reset();
        player.addCard(new Card(Suit.CLUBS, Value.FOUR));
        player.addCard(new Card(Suit.DIAMONDS, Value.ACE));
        player.addCard(new Card(Suit.HEARTS, Value.SIX));
        player.addCard(new Card(Suit.CLUBS, Value.SIX));
        player.addCard(new Card(Suit.DIAMONDS, Value.FOUR));
    }
}
