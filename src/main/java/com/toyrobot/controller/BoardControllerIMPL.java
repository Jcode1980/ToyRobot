package com.toyrobot.controller;

import com.toyrobot.enums.CardinalPoint;
import com.toyrobot.enums.RotationDirection;
import com.toyrobot.model.GridBoard;
import com.toyrobot.model.PlaceableItem;

import java.awt.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class holds holds a GridBoard object and a PlaceableItem
 * Methods in this class is associated with the state of a PlaceableItem
 * eg move() method moves the placeableItem, rotate() method, rotates
 * the PlaceableItem.
 * <p>
 * Future development would be to allow the board controller to
 * contain more than one PlaceableItem.
 *
 * Exception will be thrown if by some of the methods if incorrect
 * arguments are passed in. These exceptions should be caught the
 * the calling methods.
 */
public class BoardControllerIMPL implements BoardController {
    private GridBoard gridBoard;
    private PlaceableItem item;
    private static final String COMMA = ",";

    /**
     * Constructor of BoardControllerIMPL
     *
     * @param gridBoard the gridBoard which will have the size details
     * @param item the placeableItem will is to be placed on the board
     * @throws NullPointerException if any of the two parameters are null
     *
     */
    public BoardControllerIMPL(GridBoard gridBoard, PlaceableItem item) {
        checkNotNull(gridBoard, "Grid Board must not be null", gridBoard);
        checkNotNull(item, "PlaceableItem must not be null", item);

        this.gridBoard = gridBoard;
        this.item = item;
    }

    public boolean move() {
        if (!item.placedOnBoard()) return false;

        //grab new point for object
        Point point = item.nextMoveCoordinates();
        int newX = (int) point.getX();
        int newY = (int) point.getY();

        //compare point to grid to see if it's a valid move
        if (coordinatesAreValid(newX, newY)) {
            item.move(newX, newY);
            return true;
        } else {
            return false;
        }
    }

    public void rotate(RotationDirection direction) {
        checkNotNull(direction, "Direction passed in must not be null", direction);
        item.rotate(direction);
    }

    public boolean place(int x, int y, CardinalPoint cp) {
        checkNotNull(cp, "cp Cardinal Poinst must not be null", cp);

        if (coordinatesAreValid(x, y)) {
            item.place(x, y, cp);
            return true;
        } else {
            return false;
        }
    }

    public String report() {

        return item.getX() + COMMA + item.getY() + COMMA + item.cardinalPoint().name();
    }

    private boolean coordinatesAreValid(int x, int y) {

        return x >= 0 && y >= 0 && gridBoard.getWidth() >= x && gridBoard.getHeight() >= y;
    }


}
