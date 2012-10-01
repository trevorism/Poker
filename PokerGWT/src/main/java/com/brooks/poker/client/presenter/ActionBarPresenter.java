package com.brooks.poker.client.presenter;

import com.brooks.common.client.callback.Callback;
import com.brooks.common.client.callback.NoActionCallback;
import com.brooks.common.client.event.EventBus;
import com.brooks.poker.client.PokerApplication;
import com.brooks.poker.client.event.CallEvent;
import com.brooks.poker.client.event.FoldEvent;
import com.brooks.poker.client.event.StartGameEvent;
import com.brooks.poker.client.model.Action;
import com.brooks.poker.client.model.Action.UserAction;
import com.brooks.poker.client.model.GameStateCM;
import com.brooks.poker.client.view.ActionBar;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * @author Trevor
 *
 */
public class ActionBarPresenter{
    
    private ActionBar view;
    
    public ActionBarPresenter(ActionBar view){
        this.view = view;
        addClickHandlers();
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
            }
        });       
    }

    private void createHowMuchListener(){
        final Action action = new Action();
        action.setAction(UserAction.RAISE);        
        action.setBetAmount(view.getHowMuch().getAmount());
        
        view.getCall().addClickHandler(new ClickHandler(){            
            @Override
            public void onClick(ClickEvent event){
                PokerApplication.getService().placeBet(view.getUser(), action,  new Callback<GameStateCM>(){
                    @Override
                    public void onSuccess(GameStateCM result){
                        EventBus.getInstance().fireEvent(new CallEvent(view.getUser(), result));
                    }                   
                });
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
                PokerApplication.getService().placeBet(view.getUser(), action,  new Callback<GameStateCM>(){
                    @Override
                    public void onSuccess(GameStateCM result){
                        EventBus.getInstance().fireEvent(new CallEvent(view.getUser(), result));
                    }                   
                });
            }
        });
    }

    private void createFoldListener(){
        final Action action = new Action();
        action.setAction(UserAction.FOLD);

        view.getFold().addClickHandler(new ClickHandler(){            
            @Override
            public void onClick(ClickEvent event){
                PokerApplication.getService().placeBet(view.getUser(), action,  new Callback<GameStateCM>(){
                    @Override
                    public void onSuccess(GameStateCM result){
                        EventBus.getInstance().fireEvent(new FoldEvent(view.getUser(), result));
                    }                   
                });
            }
        });
    }
    
    
}
