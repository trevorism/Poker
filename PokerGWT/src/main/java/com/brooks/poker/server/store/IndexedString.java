package com.brooks.poker.server.store;

/**
 * @author Trevor
 * 
 */
public class IndexedString{
    private final int index;
    private final String name;

    public IndexedString(int index, String name){
        this.index = index;
        this.name = name;
    }

    public int getIndex(){
        return index;
    }

    public String getName(){
        return name;
    }

}
