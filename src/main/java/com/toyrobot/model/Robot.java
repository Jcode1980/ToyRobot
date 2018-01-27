package com.toyrobot.model;

import com.toyrobot.enums.CardinalDirection;
import com.toyrobot.enums.RotationDirection;

import java.awt.*;
import java.util.Optional;

public class Robot implements PlaceableItem {
    private int x;
    private int y;
    private int cDirection;

    //todo
    public boolean placedOnBoard() {return false; }

    public int getX() {return x; }

    public int getY() { return y; }

    public int cDirection(){ return cDirection;}

    //TODO
    public Point nextMoveCoordinates(CardinalDirection direction){
        //Point()
        return null;
    }

    public void rotate(int rotationDirection) {
        Optional<CardinalDirection> optional = CardinalDirection.cardinalDirectionForInt(cDirection());
        Optional<RotationDirection> rotationOptional = RotationDirection.rotationDirectionForInt(rotationDirection);
        if(optional.isPresent() && rotationOptional.isPresent()){
            CardinalDirection newDirection = CardinalDirection.rotate(optional.get(), rotationOptional.get());
            cDirection = newDirection.value();
        }
    }

    public void place(int x, int y, int cDirection){
        this.x = x;
        this.y = y;
        this.cDirection = cDirection;
    }
}
