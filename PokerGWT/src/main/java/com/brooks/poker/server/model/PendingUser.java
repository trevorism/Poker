package com.brooks.poker.server.model;

/**
 * @author Trevor
 * 
 */
public class PendingUser{

    private final String name;
    private final int index;

    public PendingUser(String name, int index){
        super();
        this.name = name;
        this.index = index;
    }

    public String getName(){
        return name;
    }

    public int getIndex(){
        return index;
    }

}
