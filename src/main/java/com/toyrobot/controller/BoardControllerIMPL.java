package com.toyrobot.controller;

import com.toyrobot.model.GridBoard;
import com.toyrobot.model.PlaceableItem;

public class BoardControllerIMPL implements BoardController{
    private GridBoard gridBoard;

    public BoardControllerIMPL(GridBoard gridBoard){ this.gridBoard = gridBoard;}

    @Override
    public boolean move(PlaceableItem item) {
        return false;
    }
}
