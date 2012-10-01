package com.brooks.poker.server.playerAction;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import com.brooks.common.client.event.EventBus;
import com.brooks.common.client.event.EventHandler;
import com.brooks.poker.client.model.Action;
import com.brooks.poker.client.model.Action.UserAction;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.outcome.BettingOutcome;
import com.brooks.poker.outcome.BettingOutcomeFactory;
import com.brooks.poker.player.Player;
import com.brooks.poker.player.action.PlayerAction;

/**
 * @author Trevor
 * 
 */
public class EventDrivenPlayerAction implements PlayerAction{

    private static final int TIMEOUT_SECONDS = 120;

    private Semaphore semaphore;
    private BettingOutcome outcome;

    public EventDrivenPlayerAction(){
        semaphore = new Semaphore(0);
        outcome = BettingOutcomeFactory.createFoldOutcome();
        EventBus.getInstance().registerHandler(new PlayerActionHandler());
    }

    @Override
    public BettingOutcome getBettingOutcome(GameState gameState, Player player){
        try{
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
            if (invalidEvent(event)){
                return;
            }

            Action action = event.getAction();
            if (action.getAction() == UserAction.FOLD)
                outcome = BettingOutcomeFactory.createFoldOutcome();
            if (action.getAction() == UserAction.CALL)
                outcome = BettingOutcomeFactory.createCallOutcome();
            if (action.getAction() == UserAction.RAISE)
                outcome = BettingOutcomeFactory.createRaiseOutcome(action.getBetAmount());

            semaphore.release();
        }

        private boolean invalidEvent(PlayerActionEvent event){
            return false;
        }

    };
}
