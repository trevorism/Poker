package com.brooks.poker.client.widget.player;

import com.brooks.common.client.util.SizeUtils;
import com.brooks.poker.client.model.CardCM;
import com.brooks.poker.client.model.PotState;
import com.brooks.poker.client.view.PokerGameView;
import com.brooks.poker.client.widget.CardWidget;
import com.brooks.poker.client.widget.KeyValueWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PotWidget extends Composite{

    private VerticalPanel mainPanel;
    private KeyValueWidget potLabel;
    private CardWidget [] cards;
    private KeyValueWidget amountOwedLabel;
    
    public PotWidget(){
        mainPanel = new VerticalPanel();
   
        potLabel = new KeyValueWidget("Pot");
        cards = new CardWidget[5];
        amountOwedLabel = new KeyValueWidget("Amount Owed");
        
        buildWidget();
    }

    private void buildWidget(){
        initWidget(mainPanel);
        styleWidget();
        
        mainPanel.add(potLabel);
        mainPanel.add(createCardsContainer());               
        mainPanel.add(amountOwedLabel);       
    }

    private Widget createCardsContainer(){
        HorizontalPanel hp = new HorizontalPanel();
        hp.setSpacing(3);
        for(int i = 0; i < 5; i++)
        {
            CardWidget cardWidget = new CardWidget();
            cards[i] = cardWidget;
            hp.add(cardWidget);
        }
        
        return hp;
    }

    private void styleWidget(){
        mainPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
        mainPanel.setSize(SizeUtils.toPx(PokerGameView.GRID_WIDTH), SizeUtils.toPx(PokerGameView.GRID_HEIGHT));
    }

    public void setCard(int index, CardCM card){
        cards[index].setCard(card);
    }
    
    public void applyPotState(PotState state){
        potLabel.setValue(state.getPot(0).getPot());
        amountOwedLabel.setValue(state.getPot(0).getAmountOwed());
    }
}
