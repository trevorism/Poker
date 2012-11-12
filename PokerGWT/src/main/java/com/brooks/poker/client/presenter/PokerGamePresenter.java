package com.brooks.poker.client.presenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brooks.common.client.event.EventBus;
import com.brooks.common.client.event.EventHandler;
import com.brooks.poker.client.PokerApplication;
import com.brooks.poker.client.model.GameStateCM;
import com.brooks.poker.client.model.User;
import com.brooks.poker.client.push.ChannelCreator;
import com.brooks.poker.client.push.GameStateMessage;
import com.brooks.poker.client.push.UpdateActionsEvent;
import com.brooks.poker.client.util.GridLocation;
import com.brooks.poker.client.util.GridLocationUtil;
import com.brooks.poker.client.view.PokerGameView;
import com.brooks.poker.client.view.UserSetup;
import com.brooks.poker.client.widget.player.BlankWidget;
import com.brooks.poker.client.widget.player.PlayerShowingWidget;
import com.brooks.poker.client.widget.player.PlayerShowingWidgetFactory;
import com.brooks.poker.client.widget.player.PotWidget;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author Trevor
 * 
 */
public class PokerGamePresenter{

    private static final int MAX_PLAYERS = 8;

    private long id;
    private PokerGameView view;
    private User[] usersInPosition;
    private boolean[] localIndex;
    private Map<GridLocation, IsWidget> gridWidgets;

    public PokerGamePresenter(PokerGameView view){
        this.view = view;
        this.usersInPosition = new User[MAX_PLAYERS];
        this.localIndex = new boolean[MAX_PLAYERS];
        this.gridWidgets = new HashMap<GridLocation, IsWidget>();
        new ChannelCreator().connect();

        initTableGrid();
        addEventListener();
    }

    private void addEventListener(){
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
            UserSetup widget = PokerApplication.getViewFactory().createWidget(this, i);
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

    public void addUser(User user){
        int index = user.getIndex();
        usersInPosition[index] = user;
        localIndex[index] = true;
        PlayerShowingWidget widget = PlayerShowingWidgetFactory.create(user, true);
        GridLocation location = GridLocationUtil.indexToGridLocation(index);
        addWidgetToView(location, widget);
        widget.applyUser(user);
    }

    private void update(GameStateCM gameState){
        this.id = gameState.getId();
        updatePlayers(gameState.getAllUsers());
        updatePot(gameState);
        updateActions(gameState);

    }

    private void updatePlayers(List<User> allUsers){
        for (User user : allUsers){
            int index = getUserIndex(user.getName());
            if(index == -1)
            {
                continue;
            }
            boolean local = localIndex[index];
            PlayerShowingWidget widget = PlayerShowingWidgetFactory.create(user, local);
            GridLocation location = GridLocationUtil.indexToGridLocation(index);
            addWidgetToView(location, widget);
            widget.applyUser(user);
        }
    }

    public void blankNonExtantPlayers(){
        for (int i = 0; i < MAX_PLAYERS; i++){
            if (usersInPosition[i] == null){
                GridLocation location = GridLocationUtil.indexToGridLocation(i);
                BlankWidget widget = new BlankWidget();
                addWidgetToView(location, widget);
            }
        }
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
        int userIndex = getUserIndex(gameState.getActionOnUserName());
        
        User user = User.NULL_USER;
        if(userIndex != -1 && localIndex[userIndex])
            user = usersInPosition[userIndex];

        EventBus.getInstance().fireEvent(new UpdateActionsEvent(user, minBet, id != -1));
    }

    private int getUserIndex(String actionOnUserName){
        for(int i = 0; i < MAX_PLAYERS; i++){
            User user = usersInPosition[i];
            if(user != null && user.getName().equals(actionOnUserName))
                return i;
        }
        return -1;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

}
