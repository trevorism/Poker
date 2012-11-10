package com.brooks.poker.client.widget.player;

import com.brooks.common.client.util.SizeUtils;
import com.brooks.poker.client.model.User;
import com.brooks.poker.client.view.PokerGameView;
import com.brooks.poker.client.widget.BigText;
import com.brooks.poker.client.widget.KeyValueWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Trevor
 * 
 */
public class InHandHidingCardsWidget extends Composite implements PlayerShowingWidget{

    private VerticalPanel mainPanel;

    private Label nameLabel;
    private KeyValueWidget chipsLabel;
    private BigText bigText;
    private KeyValueWidget pendingBet;

    public InHandHidingCardsWidget(){
        mainPanel = new VerticalPanel();

        nameLabel = new Label();
        chipsLabel = new KeyValueWidget("Chips");
        bigText = new BigText("IN");
        pendingBet = new KeyValueWidget("Current Bet");

        buildWidget();
    }

    private void buildWidget(){
        initWidget(mainPanel);
        styleWidget();

        mainPanel.add(nameLabel);
        mainPanel.add(chipsLabel);
        mainPanel.add(bigText);
        mainPanel.add(pendingBet);
    }

    private void styleWidget(){
        mainPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
        mainPanel.setSize(SizeUtils.toPx(PokerGameView.GRID_WIDTH), SizeUtils.toPx(PokerGameView.GRID_HEIGHT));
    }

    public void applyUser(User user){
        nameLabel.setText(user.getName());
        chipsLabel.setValue(user.getChips());
        pendingBet.setValue(user.getPendingBet());
    }

}
