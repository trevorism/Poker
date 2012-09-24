package com.brooks.poker.server;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.brooks.poker.client.PokerService;
import com.brooks.poker.client.model.Action;
import com.brooks.poker.client.model.Card;
import com.brooks.poker.client.model.GameStateCM;
import com.brooks.poker.client.model.User;
import com.brooks.poker.game.data.BlindsAnte;
import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.data.Table;
import com.brooks.poker.game.states.GameStateFactory;
import com.brooks.poker.game.states.GameStateHandler;
import com.brooks.poker.player.Player;
import com.brooks.poker.player.action.ProgrammaticPlayerAction;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author Trevor
 * 
 */
public class PokerServiceImpl extends RemoteServiceServlet implements PokerService{

    private static final long serialVersionUID = 1L;

    private final static GameState gameState;
    private final static GameStateFactory factory;
    private static GamePhase currentPhase = GamePhase.BEGIN_HAND;

    static{
        BlindsAnte blinds = new BlindsAnte();
        blinds.smallBlind = 10;
        blinds.bigBlind = 25;
        blinds.ante = 0;

        Player player1 = new Player("Trevor", 1000, new ProgrammaticPlayerAction());
        Player player2 = new Player("Vaughn", 1000, new ProgrammaticPlayerAction());
        Player player3 = new Player("Brooks", 1000, new ProgrammaticPlayerAction());

        List<Player> players = new LinkedList<Player>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        gameState = GameState.configureGameState(blinds, players);
        factory = new GameStateFactory(gameState);

    }

    @Override
    public void addUser(User user){
    }

    @Override
    public List<User> startHand(List<User> usersToPlay){
        GameStateHandler handler = factory.getStateHandler(currentPhase);
        handler.handleState();
        currentPhase = handler.getNextPhase();

        return setUsersFromGameState(usersToPlay);
    }

    private List<User> setUsersFromGameState(List<User> usersToPlay){
        Table table = gameState.getTable();
        Set<Player> players = table.getAllPlayers();
        for (User user : usersToPlay){
            Player player = findPlayerByName(players, user.getName());
            setUserValuesFromPlayer(user, player);
        }

        return usersToPlay;
    }

    private void setUserValuesFromPlayer(User user, Player player){
        user.setChips(player.getChipCount());
        user.setCard1(clientCard(player.getHand().getCards().get(0)));
        user.setCard2(clientCard(player.getHand().getCards().get(1)));
        user.setInHand(true);
        user.setPendingBet(player.getPendingBet());
        user.setSitting(true);
    }

    private Card clientCard(com.brooks.poker.cards.Card card){

        return new Card(card.getValue().name(), card.getSuit().name());
    }

    private Player findPlayerByName(Set<Player> players, String name){
        for (Player player : players){
            if (player.getName().equals(name))
                return player;
        }

        return Player.NOBODY;
    }

    @Override
    public User actionOn(){

        return null;
    }

    @Override
    public GameStateCM placeBet(User user, Action action){

        return null;
    }

}