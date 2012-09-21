package com.brooks.poker.client;

import com.brooks.common.client.BaseEntryPoint;
import com.brooks.poker.client.model.Card;
import com.brooks.poker.client.model.User;
import com.brooks.poker.client.view.TableGrid;
import com.brooks.poker.client.widget.BigText;
import com.brooks.poker.client.widget.player.ShowingCardsWidget;
import com.brooks.poker.client.widget.player.SitDownWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Trevor
 *
 */
public class Poker extends BaseEntryPoint{

    @Override
    public IsWidget createMainView(){
        VerticalPanel panel = new VerticalPanel();
        panel.add(new BigText("OUT"));

        User user = createUser();
        ShowingCardsWidget scw = new ShowingCardsWidget();
        scw.applyUser(user);
        

        TableGrid grid = new TableGrid();
        panel.add(grid);
        grid.addWidget(0, 0, new SitDownWidget());
        grid.addWidget(0, 1, scw);
        panel.setSpacing(30);
        return panel;
    }

    private User createUser(){
        User user = new User();
        user.setName("Trevor");
        user.setCard1(new Card("THREE","CLUBS"));
        user.setCard2(new Card("THREE","CLUBS"));
        user.setChips(1000);
        return user;
    }
}
