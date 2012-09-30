package com.brooks.poker.client.presenter;

import java.util.HashMap;
import java.util.Map;

import com.brooks.common.client.event.EventBus;
import com.brooks.common.client.event.EventHandler;
import com.brooks.poker.client.PokerApplication;
import com.brooks.poker.client.model.GameStateCM;
import com.brooks.poker.client.model.User;
import com.brooks.poker.client.push.ChannelCreator;
import com.brooks.poker.client.push.UserMessage;
import com.brooks.poker.client.util.GridLocation;
import com.brooks.poker.client.util.GridLocationUtil;
import com.brooks.poker.client.view.SitDownWidget;
import com.brooks.poker.client.view.TableGrid;
import com.brooks.poker.client.widget.player.InHandHidingCardsWidget;
import com.brooks.poker.client.widget.player.PotWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Trevor
 * 
 */
public class TableGridPresenter{

    private static final int MAX_PLAYERS = 8;

    private TableGrid view;
    private GameStateCM gameStateCM;
    private User[] usersInPosition;
    private Map<GridLocation, Widget> gridWidgets;
    private ChannelCreator creator;

    public TableGridPresenter(TableGrid view){
        this.view = view;
        this.gameStateCM = new GameStateCM();
        this.usersInPosition = new User[MAX_PLAYERS];
        this.gridWidgets = new HashMap<GridLocation, Widget>();
        this.creator = new ChannelCreator();

        initTableGrid();
        addEventListeners();
    }

    private void addEventListeners(){
        EventBus.getInstance().registerHandler(new EventHandler<UserMessage>(){
            @Override
            public Class<UserMessage> getEventClass(){
                return UserMessage.class;
            }

            @Override
            public void handle(UserMessage event){
                User user = event.getUser();
                int index = event.getIndex();
                usersInPosition[index] = user;
                GridLocation location = GridLocationUtil.indexToGridLocation(index);
                InHandHidingCardsWidget inHandWidget = new InHandHidingCardsWidget();
                addWidgetToView(location, inHandWidget);
                inHandWidget.applyUser(user);
            }
        });
    }

    private void initTableGrid(){
        for (int i = 0; i < MAX_PLAYERS; i++){
            GridLocation location = GridLocationUtil.indexToGridLocation(i);
            SitDownWidget widget = PokerApplication.getViewFactory().createWidget(this, i);
            addWidgetToView(location, widget);
        }
        PotWidget potWidget = new PotWidget();
        GridLocation location = new GridLocation(1, 1);
        addWidgetToView(location, potWidget);
    }

    private void addWidgetToView(GridLocation location, Widget widget){
        gridWidgets.put(location, widget);
        view.addWidget(location.getY(), location.getX(), widget);
    }

    public void setGameToken(String gameToken){
        creator.setChannelToken(gameToken);
    }

}
