package com.toyrobot.model;

import com.toyrobot.enums.CardinalPoint;
import com.toyrobot.enums.RotationDirection;

import java.awt.*;

public interface PlaceableItem {
    /**
     * Check to see if the <code>PlaceableItem</code> is placed on a board.
     * @return <code>boolean</code> true if it's placed on board and false if it isn't.
     */
    boolean placedOnBoard();

    /**
     * Retrieve the <code>x</code> value for the placeableItem
     * @return the <code>int</code>
     */
    int getX();

    /**
     * Retrieve the <code>y</code> value for the placeableItem
     * @return the <code>int</code>
     */
    int getY();

    /**
     * Retrieve the <code>CardinalPoint</code> for the placeableItem
     * @return the <code>CardinalPoint</code>
     */
    CardinalPoint cardinalPoint();

    /**
     * Rotate the placeable item in the direction specified in the <code>RotationDirection</code>
     * @param rotationDirection the direction (left or right) to spin the item towards
     */
    void rotate(RotationDirection rotationDirection);


    /**
     * Set the code x, y and Cardinal Point for <code>PlaceableItem</code>
     * @param x the x coordinate for the items to be placed at
     * @param y the y coordinate for the item to be placed at
     */
    void place(int x, int y, CardinalPoint cp);
    Point nextMoveCoordinates();
    void move(int x, int y);


}
