package com.brooks.poker.client.widget.player;

import com.brooks.common.client.util.SizeUtils;
import com.brooks.poker.client.model.User;
import com.brooks.poker.client.view.TableGrid;
import com.brooks.poker.client.widget.BigText;
import com.brooks.poker.client.widget.KeyValueWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Trevor
 * 
 */
public class OutOfHandWidget extends Composite{

    private VerticalPanel mainPanel;

    private Label nameLabel;
    private KeyValueWidget chipsLabel;
    private BigText bigText;

    public OutOfHandWidget(){
        mainPanel = new VerticalPanel();

        nameLabel = new Label();
        chipsLabel = new KeyValueWidget("Chips");
        bigText = new BigText("OUT");

        buildWidget();
    }

    private void buildWidget(){
        initWidget(mainPanel);
        styleWidget();

        mainPanel.add(nameLabel);
        mainPanel.add(chipsLabel);
        mainPanel.add(bigText);
    }

    private void styleWidget(){
        mainPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
        mainPanel.setSize(SizeUtils.toPx(TableGrid.GRID_WIDTH), SizeUtils.toPx(TableGrid.GRID_HEIGHT));
    }

    public void applyUser(User user){
        nameLabel.setText(user.getName());
        chipsLabel.setValue(user.getChips());
    }

}