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
    
    public CardWidget(Card card){
        SimplePanel simplePanel = new SimplePanel();
        
        Image image = new Image(CARD_IMAGE_FOLDER + "/" + card.toString() + ".png");
        simplePanel.add(image);                
        initWidget(simplePanel);
    }
}
