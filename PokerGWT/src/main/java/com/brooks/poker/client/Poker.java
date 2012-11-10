package com.brooks.poker.client;

import com.brooks.common.client.BaseEntryPoint;
import com.brooks.poker.client.presenter.ActionBarPresenter;
import com.brooks.poker.client.presenter.PokerGamePresenter;
import com.brooks.poker.client.view.ActionBar;
import com.brooks.poker.client.view.PokerGameView;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Trevor
 * 
 */
public class Poker extends BaseEntryPoint{

    @Override
    public IsWidget createMainView(){
        PokerGameView grid = new PokerGameView();
        PokerGamePresenter presenter = new PokerGamePresenter(grid);

        ActionBar actionBar = new ActionBar();
        new ActionBarPresenter(actionBar, presenter);

        VerticalPanel mainView = new VerticalPanel();
        mainView.add(actionBar);
        mainView.add(grid);

        actionBar.startGame();
        
        return mainView;
    }

}
