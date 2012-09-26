package com.brooks.poker.client.presenter;

import java.util.LinkedList;
import java.util.List;

import com.brooks.common.client.callback.Callback;
import com.brooks.common.client.event.EventBus;
import com.brooks.common.client.event.EventHandler;
import com.brooks.poker.client.PokerApplication;
import com.brooks.poker.client.event.StartGameEvent;
import com.brooks.poker.client.model.GameStateCM;
import com.brooks.poker.client.model.PotState;
import com.brooks.poker.client.model.User;
import com.brooks.poker.client.view.TableGrid;
import com.brooks.poker.client.widget.player.InHandHidingCardsWidget;
import com.brooks.poker.client.widget.player.PlayerShowingWidget;
import com.brooks.poker.client.widget.player.PotWidget;
import com.brooks.poker.client.widget.player.ShowingCardsWidget;

/**
 * @author Trevor
 * 
 */
public class TableGridPresenter{

    private TableGrid view;
    private List<User> users;
    private PotState potState;
    private PlayerShowingWidget widget00;
    private PlayerShowingWidget widget01;
    private PlayerShowingWidget widget10;
    
    public TableGridPresenter(TableGrid view){
        this.view = view;
        this.users = new LinkedList<User>();
        this.potState = new PotState();

        initTableGrid();
        addEventListeners();
    }

    private void addEventListeners(){
        EventBus.getInstance().registerHandler(new EventHandler<StartGameEvent>(){

            @Override
            public Class<StartGameEvent> getEventClass(){
                return StartGameEvent.class;
            }

            @Override
            public void handle(StartGameEvent event){
                PokerApplication.getService().startHand(new Callback<GameStateCM>(){
                    @Override
                    public void onSuccess(GameStateCM result){

                    }
                });
            }
        });
    }

    private void initTableGrid(){
        widget00 = new InHandHidingCardsWidget();
        widget00.applyUser(createUser("Trevor"));
        widget01 = new InHandHidingCardsWidget();
        widget01.applyUser(createUser("Vaughn"));
        widget10 = new InHandHidingCardsWidget();
        widget10.applyUser(createUser("Brooks"));

        PotWidget potWidget = new PotWidget();
        potWidget.applyPotState(potState);

        view.addWidget(0, 0, widget00);
        view.addWidget(0, 1, widget01);
        view.addWidget(1, 0, widget10);
        view.addWidget(1, 1, potWidget);
    }

    private User createUser(String name){
        User user = new User();
        user.setName(name);
        user.setChips(0);
        user.setPendingBet(0);
        users.add(user);
        return user;
    }



}
