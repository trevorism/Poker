package com.brooks.poker.server.playerAction;

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

    private BettingOutcome outcome;
    private long gameId;

    public EventDrivenPlayerAction(long gameId){
        outcome = BettingOutcomeFactory.createFoldOutcome();
        this.gameId = gameId;
        EventBus.getInstance().registerHandler(new PlayerActionHandler());
    }

    @Override
    public BettingOutcome getBettingOutcome(GameState gameState, Player player){
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
        }

        private boolean invalidEvent(PlayerActionEvent event){
            return event.getAction().getGameId() != gameId;
        }

    };
}
