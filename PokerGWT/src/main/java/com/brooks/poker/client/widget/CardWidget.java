package com.brooks.poker.client.widget;

import com.brooks.poker.client.model.CardCM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * @author Trevor
 * 
 */
public class CardWidget extends Composite{

    private static final String CARD_IMAGE_FOLDER = "../cardImages";

    private SimplePanel simplePanel;
    private Image image;

    public CardWidget(){
        simplePanel = new SimplePanel();
        image = new Image();
        simplePanel.add(image);
        initWidget(simplePanel);
        setCard(CardCM.NULL_CARD);
    }

    public void setCard(CardCM card){
        if(!card.isNull())
            image.setUrl(CARD_IMAGE_FOLDER + "/" + card.toString() + ".png");
        setWidgetVisibility(card);
    }

    private void setWidgetVisibility(CardCM card){
        if (card.isNull())
            simplePanel.setVisible(false);
        else
            simplePanel.setVisible(true);
    }
}
