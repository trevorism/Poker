package com.brooks.poker.client.widget.player;

import com.brooks.poker.client.model.User;

/**
 * @author Trevor
 *
 */
public class PlayerShowingWidgetFactory{

    public static PlayerShowingWidget create(User user, boolean local){
        if(!user.isInHand())
            return new OutOfHandWidget();
        if(!local)
            return new InHandHidingCardsWidget();
        return new ShowingCardsWidget();
    }
}
