package com.toyrobot.model;

import com.toyrobot.enums.CardinalPoint;
import com.toyrobot.enums.RotationDirection;

import java.awt.*;


public class Robot implements PlaceableItem {
    private int x = -1;
    private int y = -1;
    private int cDirection;

    public boolean placedOnBoard() { return getX() >= 0 && getY() >=0; }

    public int getX() {return x; }

    public int getY() { return y; }

    public CardinalPoint cardinalPoint(){ return CardinalPoint.cardinalPointForInt(cDirection);}

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

    public void rotate(RotationDirection rotationDirection) {
        CardinalPoint cPoint = cardinalPoint();

        CardinalPoint newDirection = CardinalPoint.rotate(cPoint, rotationDirection);
        cDirection = newDirection.getValue();
        System.out.println("cDirection is now: " + cDirection);

    }
    public void move(int x , int y ){
        this.x = x;
        this.y = y;
    }

    public void place(int x, int y, CardinalPoint cp){
        this.x = x;
        this.y = y;
        this.cDirection = cp.getValue();
    }
}
