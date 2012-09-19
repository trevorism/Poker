package com.brooks.poker.client.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * @author Trevor
 *
 */
public class BigText extends Composite{
    
    private SimplePanel mainPanel;
    
    public BigText(String text){
        this.mainPanel = new SimplePanel();
        mainPanel.add(createLabel(text));
        initWidget(mainPanel);
    }

    private Label createLabel(String text){
        Label label = new Label();
        label.setText(text);
        label.setStyleName("big-text");
        return label;
    }
}
