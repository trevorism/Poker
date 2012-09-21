 package com.brooks.poker.client.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * @author Trevor
 *
 */
public class KeyValueWidget extends Composite{
    
    private HorizontalPanel mainPanel;
    private Label keyLabel;
    private Label valueLabel;
    
    public KeyValueWidget(String key){
        this.mainPanel = new HorizontalPanel();
        this.keyLabel = new Label(key + ":");
        this.valueLabel = new Label();
        
        buildWidget();
    }

    private void buildWidget(){
        initWidget(mainPanel);

        mainPanel.add(keyLabel);
        mainPanel.add(valueLabel);
        mainPanel.setCellWidth(keyLabel, "100px");
    }

    public void setValue(int value){
        valueLabel.setText(String.valueOf(value));
    }
}
