package com.brooks.poker.client.presenter;

import com.brooks.common.client.callback.NoActionCallback;
import com.brooks.poker.client.PokerApplication;
import com.brooks.poker.client.model.User;
import com.brooks.poker.client.view.SitDownWidget;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * @author Trevor
 * 
 */
public class SitDownPresenter{

    private final SitDownWidget sitDownWidget;
    private final TableGridPresenter tablePresenter;
    private final int index;

    public SitDownPresenter(SitDownWidget widget, TableGridPresenter tablePresenter, int index){
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
                User user = createUser(name);
                PokerApplication.getService().addUser(user, index, new NoActionCallback());
            }
        });
    }

    protected User createUser(String name){
        User user = new User();
        user.setName(name);
        user.setChips(0);
        user.setPendingBet(0);
        return user;
    }
}
