package com.brooks.poker.client.widget;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

/**
 * @author Trevor
 *
 */
public class HowMuchDialog extends Composite implements HasClickHandlers{

    private HorizontalPanel mainPanel;
    private Label howMuchLabel;
    private TextBox textBox;
    private Button submit;
    
    public HowMuchDialog(){
        mainPanel = new HorizontalPanel();
        howMuchLabel = new Label("How much?");
        textBox = new TextBox();
        submit = new Button("Submit");
    
        buildWidget();
    }

    private void buildWidget(){
        initWidget(mainPanel);
        mainPanel.add(howMuchLabel);
        mainPanel.add(textBox);
        mainPanel.add(submit);
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler){        
        return submit.addClickHandler(handler);
    }

    public int getAmount(){
        try{
            return Integer.valueOf(textBox.getText());
        }catch(Exception e){
            return -1;
        }
    }
    
}
