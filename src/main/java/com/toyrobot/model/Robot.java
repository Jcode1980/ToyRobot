package com.toyrobot.model;

import com.toyrobot.enums.CardinalPoint;
import com.toyrobot.enums.RotationDirection;

import java.awt.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;


public class Robot implements PlaceableItem {
    private int x = -1;
    private int y = -1;
    private int cDirection;

    @Override
    public boolean placedOnBoard() { return getX() >= 0 && getY() >=0; }

    @Override
    public int getX() {return x; }

    @Override
    public int getY() { return y; }

    @Override
    public CardinalPoint cardinalPoint(){ return CardinalPoint.cardinalPointForInt(cDirection); }

    @Override
    public Point nextMoveCoordinates(){
        CardinalPoint direction = cardinalPoint();
        int newX = getX();
        int newY = getY();

        switch(direction) {
            case NORTH:
                newY=newY+ 1;
                break;
            case EAST:
                newX =newX + 1;
                break;
            case SOUTH:
                newY = newY - 1;
                break;
            case WEST:
                newX = newX - 1;
                break;
        }
        return new Point(newX, newY);
    }

    @Override
    public void rotate(RotationDirection rotationDirection) {
        checkNotNull(rotationDirection, "Rotation Direction must not be null");

        CardinalPoint cPoint = cardinalPoint();
        CardinalPoint newDirection = CardinalPoint.cardinalPointForRotation(cPoint, rotationDirection);
        cDirection = newDirection.getValue();

    }

    @Override
    public void move(int x , int y ){
        checkArgument(x >= 0, "x coordinate must be a positive non zero integer: %s", x);
        checkArgument(y >= 0, "y coordinate must be a positive non zero integer: %s", y);
        this.x = x;
        this.y = y;
    }

    @Override
    public void place(int x, int y, CardinalPoint cp){
        checkArgument(x >= 0, "x coordinate must be a positive non zero integer: %s", x);
        checkArgument(y >= 0, "y coordinate must be a positive non zero integer: %s", y);
        checkNotNull(cp, "cp Cardinal Poinst must not be null");

        this.x = x;
        this.y = y;
        this.cDirection = cp.getValue();
    }
}
