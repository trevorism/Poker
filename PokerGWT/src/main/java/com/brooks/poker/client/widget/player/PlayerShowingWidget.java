package com.brooks.poker.client.widget.player;

import com.brooks.poker.client.model.User;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author Trevor
 *
 */
public interface PlayerShowingWidget extends IsWidget
{
    public void applyUser(User user);
}
