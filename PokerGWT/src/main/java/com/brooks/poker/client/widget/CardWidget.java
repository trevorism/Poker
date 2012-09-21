package com.brooks.poker.client.widget;

import com.brooks.poker.client.model.Card;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * @author Trevor
 *
 */
public class CardWidget extends Composite{

    private static final String CARD_IMAGE_FOLDER = "../cardImages";
    
    private Image image;
    
    public CardWidget(){
        SimplePanel simplePanel = new SimplePanel();
        
        image = new Image();
        simplePanel.add(image);                
        initWidget(simplePanel);
    }
    
    public void setCard(Card card){
        image.setUrl(CARD_IMAGE_FOLDER + "/" + card.toString() + ".png");
    }
}
