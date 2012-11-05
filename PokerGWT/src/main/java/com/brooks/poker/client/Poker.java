package com.brooks.poker.client;

import com.brooks.common.client.BaseEntryPoint;
import com.brooks.poker.client.presenter.ActionBarPresenter;
import com.brooks.poker.client.presenter.TableGridPresenter;
import com.brooks.poker.client.view.ActionBar;
import com.brooks.poker.client.view.TableGrid;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Trevor
 * 
 */
public class Poker extends BaseEntryPoint{

    @Override
    public IsWidget createMainView(){
        TableGrid grid = new TableGrid();
        TableGridPresenter presenter = new TableGridPresenter(grid);

        ActionBar actionBar = new ActionBar();
        new ActionBarPresenter(actionBar, presenter);

        VerticalPanel mainView = new VerticalPanel();
        mainView.add(actionBar);
        mainView.add(grid);

        actionBar.startGame();
        
        return mainView;
    }

}
