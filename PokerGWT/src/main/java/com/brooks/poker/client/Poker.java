package com.brooks.poker.client;

import com.brooks.common.client.BaseEntryPoint;
import com.brooks.poker.client.widget.CardWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Trevor
 *
 */
public class Poker extends BaseEntryPoint{

    @Override
    public IsWidget createMainView(){
        VerticalPanel panel = new VerticalPanel();
        panel.add(new CardWidget("TWO_CLUBS"));
        return panel;
    }
}
