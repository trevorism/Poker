package com.brooks.poker.client.view;

import com.brooks.poker.client.model.PotState;
import com.brooks.poker.client.model.User;
import com.brooks.poker.client.presenter.ActionBarPresenter;
import com.brooks.poker.client.widget.player.InHandHidingCardsWidget;
import com.brooks.poker.client.widget.player.PotWidget;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Trevor
 *
 */
public class MainView{

    private VerticalPanel mainView;
    
    public MainView(){
        InHandHidingCardsWidget player1 = new InHandHidingCardsWidget();
        player1.applyUser(createUser("Trevor"));
        InHandHidingCardsWidget player2 = new InHandHidingCardsWidget();
        player2.applyUser(createUser("Vaughn"));
        InHandHidingCardsWidget player3 = new InHandHidingCardsWidget();
        player3.applyUser(createUser("Brooks"));
               
        PotWidget potWidget = new PotWidget();
        PotState potState = new PotState();
        potWidget.applyPotState(potState);
        
        TableGrid grid = new TableGrid();
        grid.addWidget(0, 0, player1);
        grid.addWidget(0, 1, player2);
        grid.addWidget(1, 0, player3);
        grid.addWidget(1, 1, potWidget);
    
        ActionBar actionBar = new ActionBar();
        ActionBarPresenter presenter = new ActionBarPresenter(actionBar);
        mainView = new VerticalPanel();
        mainView.add(actionBar);
        mainView.add(grid);
        
        actionBar.startGame();
    }
    
    public VerticalPanel getMainView(){
        return mainView;
    }

    private User createUser(String name){
        User user = new User();
        user.setName(name);
        user.setChips(1000);
        user.setPendingBet(0);
        return user;
    }
}
