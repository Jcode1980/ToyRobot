package com.toyrobot.controller;

import com.toyrobot.enums.CardinalDirection;
import com.toyrobot.enums.RotationDirection;
import com.toyrobot.model.GridBoard;
import com.toyrobot.model.PlaceableItem;

import java.util.Optional;

public class BoardControllerIMPL implements BoardController{
    public static final int ROTATE_LEFT = 1;
    public static final int ROTATE_RIGHT = 2;

    private GridBoard gridBoard;
    private PlaceableItem item;

    public BoardControllerIMPL(GridBoard gridBoard, PlaceableItem item){
        this.gridBoard = gridBoard;
        this.item = item;
    }

    @Override
    public boolean move() {
        return true;
    }

    @Override
    public void rotate(int direction) {
        item.rotate(direction);
    }

    @Override
    public boolean place(int x, int y, int cDirection) {
        //check if coordinates are valid
        if(coordinatesAreValid(x,y)){
            item.place(x, y, cDirection);
            return true;
        }
        else {
            return false;
        }
    }


    public Optional<String> report() {
        return Optional.of(item.getX() + " " + item.getY() + " " + CardinalDirection.cardinalDirectionForInt(item.cDirection()));
    }

    private boolean coordinatesAreValid(int x , int y){
        return gridBoard.getWidth() >= x && gridBoard.getHeight() >= y;
    }


}
