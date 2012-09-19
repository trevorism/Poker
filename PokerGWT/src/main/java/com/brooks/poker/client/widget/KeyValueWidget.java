 package com.brooks.poker.client.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Trevor
 *
 */
public class KeyValueWidget extends Composite{
    
    private VerticalPanel mainPanel;
    private Label keyLabel;
    private Label valueLabel;
    
    public KeyValueWidget(String key, String value){
        this(key);
        setValue(value);
    }

    public KeyValueWidget(String key){
        this.mainPanel = new VerticalPanel();
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

    public void setValue(String value){
        valueLabel.setText(value);
    }
}
