package com.brooks.poker.client.widget.player;

import com.brooks.common.client.util.SizeUtils;
import com.brooks.poker.client.model.User;
import com.brooks.poker.client.view.TableGrid;
import com.brooks.poker.client.widget.CardWidget;
import com.brooks.poker.client.widget.KeyValueWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Trevor
 * 
 */
public class ShowingCardsWidget extends Composite{

    private VerticalPanel mainPanel;
    private HorizontalPanel cardPanel;
    
    private Label nameLabel;
    private KeyValueWidget chipsLabel;
    private CardWidget card1;
    private CardWidget card2;
    private KeyValueWidget pendingBet;

    public ShowingCardsWidget(){
        mainPanel = new VerticalPanel();
        cardPanel = new HorizontalPanel();
        
        nameLabel = new Label();
        chipsLabel = new KeyValueWidget("Chips");
        card1 = new CardWidget();
        card2 = new CardWidget();
        pendingBet = new KeyValueWidget("Current Bet");

        buildWidget();

    }

    private void buildWidget(){
        initWidget(mainPanel);
        styleWidget();
        
        mainPanel.add(nameLabel);
        mainPanel.add(chipsLabel);
        
        cardPanel.add(card1);
        cardPanel.add(card2);
        
        mainPanel.add(cardPanel);
        mainPanel.add(pendingBet);
        
    }

    private void styleWidget(){
        mainPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
        mainPanel.setSize(SizeUtils.toPx(TableGrid.GRID_WIDTH), SizeUtils.toPx(TableGrid.GRID_HEIGHT));
    }

    public void applyUser(User user){
        nameLabel.setText(user.getName());
        chipsLabel.setValue(user.getChips());
        card1.setCard(user.getCard1());
        card2.setCard(user.getCard2());
        pendingBet.setValue(user.getCurrentBet());
    }

}
