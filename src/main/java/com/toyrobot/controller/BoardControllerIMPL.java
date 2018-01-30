package com.toyrobot.controller;

import com.toyrobot.enums.CardinalPoint;
import com.toyrobot.enums.RotationDirection;
import com.toyrobot.model.GridBoard;
import com.toyrobot.model.PlaceableItem;
import java.awt.*;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class holds holds a GridBoard object and a PlaceableItem
 * Methods in this class is associated with the state of a PlaceableItem
 * eg move() method moves the placeableItem, rotate() method, rotates
 * the PlaceableItem.
 *
 * Future development would be to allow the board controller to
 * contain more than one PlaceableItem.
 */
public class BoardControllerIMPL implements BoardController{
    private GridBoard gridBoard;
    private PlaceableItem item;

    public BoardControllerIMPL(GridBoard gridBoard, PlaceableItem item){
        checkNotNull(gridBoard, "Grid Board must not be null", gridBoard);
        checkNotNull(item, "PlaceableItem must not be null", item);

        this.gridBoard = gridBoard;
        this.item = item;
    }

    public boolean move() {
        //grab new point for object
        Point point = item.nextMoveCoordinates();
        int newX = (int)point.getX();
        int newY = (int)point.getY();

        //compare point to grid to see if it's a valid move
        if(coordinatesAreValid((int)point.getX(), (int)point.getY())){
            item.move(newX, newY);
            return true;
        }
        else{
            return false;
        }
    }

    public void rotate(RotationDirection direction) {
        checkNotNull(direction, "Direction passed in must not be null", direction);
        item.rotate(direction);
    }

    public boolean place(int x, int y, CardinalPoint cp) {
        checkArgument(x >= 0, "x coordinate must be a positive non zero integer: %s", x);
        checkArgument(y >= 0, "y coordinate must be a positive non zero integer: %s", y);
        checkNotNull(cp, "cp Cardinal Poinst must not be null", cp);

        //check if coordinates are valid
        if(coordinatesAreValid(x,y)){
            item.place(x, y, cp);
            return true;
        }
        else {
            return false;
        }
    }

    public String report() {

        return item.getX() + "," + item.getY() + "," + item.cardinalPoint().getName();
    }

    private boolean coordinatesAreValid(int x , int y){

        return x >=0 && y >=0 &&gridBoard.getWidth() >= x && gridBoard.getHeight() >= y;
    }


}
