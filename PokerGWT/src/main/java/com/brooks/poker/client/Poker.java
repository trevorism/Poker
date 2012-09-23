package com.brooks.poker.client;

import com.brooks.common.client.BaseEntryPoint;
import com.brooks.poker.client.view.MainView;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author Trevor
 *
 */
public class Poker extends BaseEntryPoint{

    @Override
    public IsWidget createMainView(){
               
        MainView mainView = new MainView();

        return mainView.getMainView();
    }


}
