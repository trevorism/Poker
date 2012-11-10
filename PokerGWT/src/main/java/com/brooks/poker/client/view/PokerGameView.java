package com.brooks.poker.client.view;

import com.brooks.common.client.util.SizeUtils;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author Trevor
 * 
 */
public class PokerGameView extends Composite{

    public static final int GRID_WIDTH = 500;
    public static final int GRID_HEIGHT = 200;
    
    private static final int GRID_SIZE = 3;
    private Grid grid;
    
    
    public PokerGameView(){
        grid = new Grid(GRID_SIZE, GRID_SIZE);
        initWidget(grid);
        styleGrid();
    }

    private void styleGrid(){
        grid.setSize(SizeUtils.toPx(GRID_WIDTH * GRID_SIZE), SizeUtils.toPx(GRID_HEIGHT * GRID_SIZE));
        grid.setBorderWidth(2);

        for (int i = 0; i < GRID_SIZE; i++){
            for (int j = 0; j < GRID_SIZE; j++){
                grid.getCellFormatter().setWidth(i, j, SizeUtils.toPx(GRID_WIDTH));
                grid.getCellFormatter().setHeight(i, j, SizeUtils.toPx(GRID_HEIGHT));
            }
        }
    }

    public void addWidget(int row, int column, IsWidget widget){
        grid.setWidget(row, column, widget);
    }
}
