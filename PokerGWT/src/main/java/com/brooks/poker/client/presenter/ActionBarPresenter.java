package com.brooks.poker.client.presenter;

import com.brooks.common.client.callback.NoActionCallback;
import com.brooks.common.client.event.EventBus;
import com.brooks.common.client.event.EventHandler;
import com.brooks.poker.client.PokerApplication;
import com.brooks.poker.client.model.Action;
import com.brooks.poker.client.model.Action.UserAction;
import com.brooks.poker.client.push.UpdateActionsEvent;
import com.brooks.poker.client.view.ActionBar;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * @author Trevor
 *
 */
public class ActionBarPresenter{
    
    private ActionBar view;
    private PokerGamePresenter presenter;
    
    public ActionBarPresenter(ActionBar view, PokerGamePresenter presenter){
        this.view = view;
        this.presenter = presenter;
        addClickHandlers();
        addEventListeners();
    }

    private void addEventListeners(){
        EventBus.getInstance().registerHandler(new EventHandler<UpdateActionsEvent>(){
            @Override
            public Class<UpdateActionsEvent> getEventClass(){
                return UpdateActionsEvent.class;
            }

            @Override
            public void handle(UpdateActionsEvent event){
                if(event.isGameStarted())
                    view.clear();
                if(event.getUser().isNull()){
                    return;
                }
                view.action(event.getUser());
            }        
        });
    }

    private void addClickHandlers(){       
        createStartGameListener();
        createFoldListener();
        createCallListener();
        createRaiseListener();
        createHowMuchListener();
    }

    private void createStartGameListener(){
        view.getStartGame().addClickHandler(new ClickHandler(){      
            @Override
            public void onClick(ClickEvent event){
                PokerApplication.getService().startGame(new NoActionCallback());
                presenter.blankNonExtantPlayers();
            }
        });       
    }

    private void createHowMuchListener(){
        final Action action = new Action();
        action.setAction(UserAction.RAISE);        
        
        view.getHowMuch().addClickHandler(new ClickHandler(){            
            @Override
            public void onClick(ClickEvent event){
                view.clear();
                action.setGameId(presenter.getId());
                action.setBetAmount(view.getHowMuch().getAmount());
                PokerApplication.getService().placeBet(view.getUser(), action,  new NoActionCallback());
            }
        });
    }

    private void createRaiseListener(){
        view.getRaise().addClickHandler(new ClickHandler(){            
            @Override
            public void onClick(ClickEvent event){
                view.howMuch(view.getUser());
            }
        });
    }
    
    private void createCallListener(){
        final Action action = new Action();
        action.setAction(UserAction.CALL);
        
        view.getCall().addClickHandler(new ClickHandler(){
            
            @Override
            public void onClick(ClickEvent event){
                view.clear();
                action.setGameId(presenter.getId());
                PokerApplication.getService().placeBet(view.getUser(), action,  new NoActionCallback());
            }
        });
    }

    private void createFoldListener(){
        final Action action = new Action();
        action.setAction(UserAction.FOLD);

        view.getFold().addClickHandler(new ClickHandler(){            
            @Override
            public void onClick(ClickEvent event){
                view.clear();
                action.setGameId(presenter.getId());
                PokerApplication.getService().placeBet(view.getUser(), action,  new NoActionCallback());
            }
        });
    }
    
}
