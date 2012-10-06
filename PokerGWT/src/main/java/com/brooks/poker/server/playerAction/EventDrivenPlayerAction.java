package com.brooks.poker.server.playerAction;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import no.eirikb.gwtchannelapi.server.ChannelServer;

import com.brooks.common.client.event.EventBus;
import com.brooks.common.client.event.EventHandler;
import com.brooks.poker.client.model.Action;
import com.brooks.poker.client.model.GameStateCM;
import com.brooks.poker.client.model.Action.UserAction;
import com.brooks.poker.client.push.GameStateMessage;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.outcome.BettingOutcome;
import com.brooks.poker.outcome.BettingOutcomeFactory;
import com.brooks.poker.player.Player;
import com.brooks.poker.player.action.PlayerAction;
import com.brooks.poker.server.convert.GameStateCMConverter;

/**
 * @author Trevor
 * 
 */
public class EventDrivenPlayerAction implements PlayerAction{

    private static final int TIMEOUT_SECONDS = 30;

    private Semaphore semaphore;
    private BettingOutcome outcome;
    private GameStateCMConverter converter;
    private String playerName;
    
    public EventDrivenPlayerAction(String playerName){
        this.converter = new GameStateCMConverter();
        this.semaphore = new Semaphore(0);
        this.outcome = BettingOutcomeFactory.createFoldOutcome();
        this.playerName = playerName;
        EventBus.getInstance().registerHandler(new PlayerActionHandler());
    }

    @Override
    public BettingOutcome getBettingOutcome(GameState gameState, Player player){
        try{
            GameStateCM clientModel = converter.convert(gameState, player.getName());
            System.out.println("Sending game state for player " + player.getName() + " " + clientModel.getChannelKey());
            ChannelServer.send(clientModel.getChannelKey(), new GameStateMessage(clientModel));
            
            outcome = BettingOutcomeFactory.createFoldOutcome();
            semaphore.tryAcquire(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return outcome;
    }

    public class PlayerActionHandler implements EventHandler<PlayerActionEvent>{

        @Override
        public Class<PlayerActionEvent> getEventClass(){
            return PlayerActionEvent.class;
        }

        @Override
        public void handle(PlayerActionEvent event){
            System.out.println("Player " + playerName + " event fired " + event.getUser().getName());
            if (invalidEvent(event)){
                return;
            }
            
            Action action = event.getAction();
            System.out.println("Action class is " + action.getAction());
            if (action.getAction() == UserAction.FOLD)
                outcome = BettingOutcomeFactory.createFoldOutcome();
            if (action.getAction() == UserAction.CALL)
                outcome = BettingOutcomeFactory.createCallOutcome();
            if (action.getAction() == UserAction.RAISE)
                outcome = BettingOutcomeFactory.createRaiseOutcome(action.getBetAmount());

            System.out.println("outcome is " + outcome.getClass().getName());
            semaphore.release();
        }

        private boolean invalidEvent(PlayerActionEvent event){
            if(!event.getUser().getName().equals(playerName))
                return true;
            return false;
        }

    };
}
