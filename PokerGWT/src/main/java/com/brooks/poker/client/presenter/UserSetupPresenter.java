package com.brooks.poker.client.presenter;

import com.brooks.common.client.callback.Callback;
import com.brooks.poker.client.PokerApplication;
import com.brooks.poker.client.model.User;
import com.brooks.poker.client.view.UserSetup;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * @author Trevor
 * 
 */
public class UserSetupPresenter{

    private final UserSetup sitDownWidget;
    private final PokerGamePresenter tablePresenter;
    private final int index;

    public UserSetupPresenter(UserSetup widget, PokerGamePresenter tablePresenter, int index){
        this.sitDownWidget = widget;
        this.tablePresenter = tablePresenter;
        this.index = index;
        addClickHandlers();
    }

    private void addClickHandlers(){
        sitDownWidget.getSubmitButton().addClickHandler(new ClickHandler(){
            @Override
            public void onClick(ClickEvent event){
                String name = sitDownWidget.getName();
                final User user = createUser(name);
                PokerApplication.getService().addUser(user, new Callback<Void>(){
                    @Override
                    public void onSuccess(Void result){
                        tablePresenter.addUser(user);
                    }
                
                });
            }
        });
    }

    protected User createUser(String name){
        User user = new User();
        user.setName(name);
        user.setChips(0);
        user.setPendingBet(0);
        user.setIndex(index);
        return user;
    }
}
