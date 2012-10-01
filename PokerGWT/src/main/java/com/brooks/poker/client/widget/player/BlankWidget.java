package com.brooks.poker.client.widget.player;

import com.brooks.common.client.ui.Spacer;
import com.brooks.poker.client.model.User;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Trevor
 *
 */
public class BlankWidget implements PlayerShowingWidget{

    @Override
    public Widget asWidget(){
        return new Spacer();
    }

    @Override
    public void applyUser(User user){
    }

}
