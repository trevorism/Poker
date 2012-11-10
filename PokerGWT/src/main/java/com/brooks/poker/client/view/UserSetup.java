package com.brooks.poker.client.view;

import com.brooks.common.client.util.SizeUtils;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Trevor
 * 
 */
public class UserSetup extends Composite{

    private VerticalPanel mainPanel;
    private Label enterNameLabel;
    private TextBox nameBox;

    private HorizontalPanel buttonBar;
    private Button submitButton;

    public UserSetup(){
        mainPanel = new VerticalPanel();
        enterNameLabel = new Label("Enter Name");
        nameBox = new TextBox();
        buttonBar = new HorizontalPanel();
        submitButton = new Button("Submit");

        buildWidget();
    }

    private void buildWidget(){
        initWidget(mainPanel);
        styleWidget();

        mainPanel.add(enterNameLabel);
        mainPanel.add(nameBox);
        mainPanel.add(buttonBar);
        addButonsToButtonBar();
    }

    private void styleWidget(){
        mainPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
        mainPanel.setSize(SizeUtils.toPx(PokerGameView.GRID_WIDTH), SizeUtils.toPx(PokerGameView.GRID_HEIGHT));
    }

    private void addButonsToButtonBar(){
        buttonBar.add(submitButton);
    }

    public Button getSubmitButton(){
        return submitButton;
    }

    public String getName(){
        return nameBox.getText();
    }

}
