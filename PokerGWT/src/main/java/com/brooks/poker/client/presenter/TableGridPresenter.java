package com.brooks.poker.client.presenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brooks.common.client.event.EventBus;
import com.brooks.common.client.event.EventHandler;
import com.brooks.poker.client.PokerApplication;
import com.brooks.poker.client.event.UpdateActionsEvent;
import com.brooks.poker.client.model.GameStateCM;
import com.brooks.poker.client.model.User;
import com.brooks.poker.client.push.ChannelCreator;
import com.brooks.poker.client.push.GameStateMessage;
import com.brooks.poker.client.push.UserMessage;
import com.brooks.poker.client.util.GridLocation;
import com.brooks.poker.client.util.GridLocationUtil;
import com.brooks.poker.client.view.SitDownWidget;
import com.brooks.poker.client.view.TableGrid;
import com.brooks.poker.client.widget.player.BlankWidget;
import com.brooks.poker.client.widget.player.InHandHidingCardsWidget;
import com.brooks.poker.client.widget.player.PlayerShowingWidget;
import com.brooks.poker.client.widget.player.PlayerShowingWidgetFactory;
import com.brooks.poker.client.widget.player.PotWidget;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author Trevor
 * 
 */
public class TableGridPresenter{

    private static final int MAX_PLAYERS = 8;

    private TableGrid view;
    private User[] usersInPosition;
    private boolean[] localIndex;
    private Map<GridLocation, IsWidget> gridWidgets;
    private ChannelCreator creator;

    public TableGridPresenter(TableGrid view){
        this.view = view;
        this.usersInPosition = new User[MAX_PLAYERS];
        this.localIndex = new boolean[MAX_PLAYERS];
        this.gridWidgets = new HashMap<GridLocation, IsWidget>();
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

        EventBus.getInstance().registerHandler(new EventHandler<GameStateMessage>(){
            @Override
            public Class<GameStateMessage> getEventClass(){
                return GameStateMessage.class;
            }

            @Override
            public void handle(GameStateMessage event){
                update(event.getGameState());
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

    private void addWidgetToView(GridLocation location, IsWidget widget){
        gridWidgets.put(location, widget);
        view.addWidget(location.getY(), location.getX(), widget);
    }

    public void setGameToken(String gameToken){
        creator.setChannelToken(gameToken);
    }

    public void setIndexAsLocal(int index){
        localIndex[index] = true;
    }

    private void update(GameStateCM gameState){
        updatePlayers(gameState.getAllUsers());
        updatePot(gameState);
        updateActions(gameState);

    }

    private void updatePlayers(List<User> allUsers){
        Map<String, Integer> userMap = blankNonExtantPlayers();
        for (User user : allUsers){
            int index = userMap.get(user.getName());
            boolean local = localIndex[index];
            PlayerShowingWidget widget = PlayerShowingWidgetFactory.create(user, local);
            GridLocation location = GridLocationUtil.indexToGridLocation(index);
            addWidgetToView(location, widget);
            widget.applyUser(user);
        }
    }

    private Map<String, Integer> blankNonExtantPlayers(){
        Map<String, Integer> userMap = new HashMap<String, Integer>();
        for (int i = 0; i < MAX_PLAYERS; i++){
            if (usersInPosition[i] == null){
                GridLocation location = GridLocationUtil.indexToGridLocation(i);
                BlankWidget widget = new BlankWidget();
                addWidgetToView(location, widget);
            }
            else{
                userMap.put(usersInPosition[i].getName(), i);
            }
        }
        return userMap;
    }

    private void updatePot(GameStateCM gameState){
        PotWidget potWidget = new PotWidget();
        GridLocation location = new GridLocation(1, 1);
        potWidget.applyPotState(gameState.getPotState());
        for (int i = 0; i < gameState.getCommunityCards().size(); i++){
            potWidget.setCard(i, gameState.getCommunityCards().get(i));
        }
        addWidgetToView(location, potWidget);
    }

    private void updateActions(GameStateCM gameState){
        int minBet = gameState.getMinRaiseAmount();
        boolean started = gameState.isStarted();
        int userIndex = getUserIndex(gameState.getActionOnUserName());
        
        User user = User.NULL_USER;
        if(userIndex != -1 && localIndex[userIndex])
            user = usersInPosition[userIndex];

        EventBus.getInstance().fireEvent(new UpdateActionsEvent(user, minBet, started));
    }

    private int getUserIndex(String actionOnUserName){
        for(int i = 0; i < MAX_PLAYERS; i++){
            User user = usersInPosition[i];
            if(user != null && user.getName().equals(actionOnUserName))
                return i;
        }
        return -1;
    }

}
