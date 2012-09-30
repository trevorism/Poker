package com.brooks.poker.client.util;

/**
 * @author Trevor
 *
 */
public class GridLocationUtil{

    public static GridLocation indexToGridLocation(int index){
        switch(index){
            case 0:
                return new GridLocation(0, 0);
            case 1:
                return new GridLocation(1, 0);
            case 2:
                return new GridLocation(2, 0);
            case 3:
                return new GridLocation(0, 1);
            case 4:
                return new GridLocation(2, 1);
            case 5:
                return new GridLocation(0, 2);
            case 6:
                return new GridLocation(1, 2);
            case 7:
                return new GridLocation(2, 2);                
        }
        return GridLocation.NULL_GRID_LOCATION;
    }
}
