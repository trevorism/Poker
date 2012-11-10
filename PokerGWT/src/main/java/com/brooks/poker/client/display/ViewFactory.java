package com.brooks.poker.client.display;

import com.brooks.poker.client.presenter.UserSetupPresenter;
import com.brooks.poker.client.presenter.PokerGamePresenter;
import com.brooks.poker.client.view.UserSetup;

/**
 * @author Trevor
 *
 */
public class ViewFactory{

    public UserSetup createWidget(PokerGamePresenter presenter, int index){
        UserSetup sitDownWidget = new UserSetup();
        new UserSetupPresenter(sitDownWidget, presenter, index);
        return sitDownWidget;        
    }
}
