package com.brooks.poker.client.util;

/**
 * @author Trevor
 *
 */
public class GridLocation{
    public static final GridLocation NULL_GRID_LOCATION = new GridLocation(-1, -1);
    
    private final int x;
    private final int y;
    
    public GridLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
    
    
}
