package com.brooks.poker.client.display;

import com.brooks.poker.client.presenter.SitDownPresenter;
import com.brooks.poker.client.presenter.TableGridPresenter;
import com.brooks.poker.client.view.SitDownWidget;

/**
 * @author Trevor
 *
 */
public class ViewFactory{

    public SitDownWidget createWidget(TableGridPresenter presenter, int index){
        SitDownWidget sitDownWidget = new SitDownWidget();
        new SitDownPresenter(sitDownWidget, presenter, index);
        return sitDownWidget;        
    }
}
