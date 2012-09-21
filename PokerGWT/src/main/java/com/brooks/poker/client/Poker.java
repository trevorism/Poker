package com.brooks.poker.client;

import com.brooks.common.client.BaseEntryPoint;
import com.brooks.poker.client.model.Card;
import com.brooks.poker.client.model.PotState;
import com.brooks.poker.client.model.User;
import com.brooks.poker.client.view.TableGrid;
import com.brooks.poker.client.widget.player.InHandHidingCardsWidget;
import com.brooks.poker.client.widget.player.OutOfHandWidget;
import com.brooks.poker.client.widget.player.PotWidget;
import com.brooks.poker.client.widget.player.ShowingCardsWidget;
import com.brooks.poker.client.widget.player.SitDownWidget;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author Trevor
 *
 */
public class Poker extends BaseEntryPoint{

    @Override
    public IsWidget createMainView(){
        User user = createUser();
        
        ShowingCardsWidget scw = new ShowingCardsWidget();
        scw.applyUser(user);
        
        InHandHidingCardsWidget ihhcw = new InHandHidingCardsWidget();
        ihhcw.applyUser(user);

        OutOfHandWidget oohw = new OutOfHandWidget();
        oohw.applyUser(user);
        
        PotWidget potWidget = new PotWidget();
        potWidget.setCard(0, new Card("FOUR","SPADES"));
        potWidget.setCard(1, new Card("FOUR","HEARTS"));
        potWidget.setCard(2, new Card("FOUR","DIAMONDS"));
        potWidget.setCard(3, new Card("FOUR","CLUBS"));
        potWidget.setCard(4, new Card("ACE","CLUBS"));
        
        PotState potState = new PotState();
        potState.setAmountOwed(40);
        potState.setPot(45);
        potWidget.applyPotState(potState);
        
        TableGrid grid = new TableGrid();
        grid.addWidget(0, 0, new SitDownWidget());
        grid.addWidget(0, 1, scw);
        grid.addWidget(0, 2, ihhcw);
        grid.addWidget(1, 0, oohw);
        grid.addWidget(1, 1, potWidget);

        return grid;
    }

    private User createUser(){
        User user = new User();
        user.setName("Trevor");
        user.setCard1(new Card("THREE","CLUBS"));
        //user.setCard2(new Card("THREE","CLUBS"));
        user.setChips(1000);
        user.setPendingBet(20);
        return user;
    }
}
