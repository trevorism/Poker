package com.brooks.poker.client.widget.player;

import com.brooks.common.client.util.SizeUtils;
import com.brooks.poker.client.view.TableGrid;
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
public class SitDownWidget extends Composite{

    private VerticalPanel mainPanel;
    private Label enterNameLabel;
    private TextBox nameBox;

    private HorizontalPanel buttonBar;
    private Button submitButton;
    private Button cancelButton;

    public SitDownWidget(){
        mainPanel = new VerticalPanel();
        enterNameLabel = new Label("Enter Name");
        nameBox = new TextBox();
        buttonBar = new HorizontalPanel();
        submitButton = new Button("Submit");
        cancelButton = new Button("Cancel");

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
        mainPanel.setSize(SizeUtils.toPx(TableGrid.GRID_WIDTH), SizeUtils.toPx(TableGrid.GRID_HEIGHT));
    }

    private void addButonsToButtonBar(){
        buttonBar.add(submitButton);
        buttonBar.add(cancelButton);
    }

}
