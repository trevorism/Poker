package com.brooks.poker.client;

import com.brooks.poker.client.presenter.ActionBarPresenter;
import com.brooks.poker.client.presenter.TableGridPresenter;
import com.brooks.poker.client.view.ActionBar;
import com.brooks.poker.client.view.TableGrid;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Trevor
 * 
 */
public class DependencyInjector{

    private VerticalPanel mainView;

    public DependencyInjector(){

        TableGrid grid = new TableGrid();
        new TableGridPresenter(grid);

        ActionBar actionBar = new ActionBar();
        new ActionBarPresenter(actionBar);

        mainView = new VerticalPanel();
        mainView.add(actionBar);
        mainView.add(grid);

        actionBar.startGame();
    }

    public VerticalPanel getMainView(){
        return mainView;
    }

}
