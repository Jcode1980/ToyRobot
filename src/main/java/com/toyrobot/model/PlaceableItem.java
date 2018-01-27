package com.toyrobot.model;

import com.toyrobot.enums.RotationDirection;

public interface PlaceableItem {
    boolean placedOnBoard();
    int getX();
    int getY();
    int cDirection();
    void rotate(int rotationDirection);
    void place(int x, int y, int cDirection);


}
