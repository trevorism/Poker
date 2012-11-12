package com.brooks.poker.client.view;

import com.brooks.poker.client.model.User;
import com.brooks.poker.client.widget.HowMuchDialog;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * @author Trevor
 * 
 */
public class ActionBar extends Composite{

    private HorizontalPanel mainPanel;
    private Label promptLabel;
    private HorizontalPanel actionBar;

    private Button startGame;
    private Button fold;
    private Button call;
    private Button raise;
    private HowMuchDialog howMuch;
    
    private User user;
    
    public ActionBar(){
        this.mainPanel = new HorizontalPanel();
        this.promptLabel = new Label();
        this.actionBar = new HorizontalPanel();
        
        this.startGame = new Button("Start Game");
        this.fold = new Button("Fold");
        this.call = new Button("Call");
        this.raise = new Button("Raise");
        this.howMuch = new HowMuchDialog();
        this.user = User.NULL_USER;
        
        buildWidget();
        
    }

    private void buildWidget(){
        mainPanel.add(promptLabel);
        mainPanel.add(actionBar);
        initWidget(mainPanel);
        mainPanel.setSpacing(8);
    }
    
    public void setPrompt(String text){
        promptLabel.setText(text);
    }
    
    public void startGame(){
        this.user = User.NULL_USER;
        actionBar.clear();
        promptLabel.setText("");
        actionBar.add(startGame);
    }
    
    public void action(User user){
        this.user = user;
        actionBar.clear();
        promptLabel.setText(user.getName() + ":");
        actionBar.add(fold);
        actionBar.add(call);
        actionBar.add(raise);
    }
    
    public void howMuch(User user){
        this.user = user;
        actionBar.clear();
        promptLabel.setText(user.getName() + ":");
        actionBar.add(howMuch);
    }

    public Button getFold(){
        return fold;
    }

    public Button getCall(){
        return call;
    }

    public Button getRaise(){
        return raise;
    }
    
    public void clear(){
        actionBar.clear();
    }
    
    public HowMuchDialog getHowMuch(){
        return howMuch;        
    }
 
    public Button getStartGame(){
        return startGame;
    }

    public User getUser(){
        return user;
    }
    
}
