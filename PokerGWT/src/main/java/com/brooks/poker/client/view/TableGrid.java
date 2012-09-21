package com.brooks.poker.client.view;

import com.brooks.common.client.util.SizeUtils;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Trevor
 * 
 */
public class TableGrid extends Composite{

    public static final int GRID_WIDTH = 350;
    public static final int GRID_HEIGHT = 200;
    private Grid grid;

    public TableGrid(){
        grid = new Grid(3, 3);
        initWidget(grid);
        styleGrid();
    }

    private void styleGrid(){
        grid.setSize(SizeUtils.toPx(GRID_WIDTH * 3), SizeUtils.toPx(GRID_HEIGHT * 2));
        grid.setBorderWidth(2);

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                grid.getCellFormatter().setWidth(i, j, "300px");
                grid.getCellFormatter().setHeight(i, j, "300px");
            }
        }
    }

    public void addWidget(int row, int column, Widget widget){
        grid.setWidget(row, column, widget);
    }
}
