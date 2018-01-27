package com.toyrobot.model;

public class Robot implements PlaceableItem {

    @Override
    public boolean placedOnBoard() {
        return false;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getOrientation() {
        return 0;
    }

    @Override
    public void rotate(int direction) {

    }
}
