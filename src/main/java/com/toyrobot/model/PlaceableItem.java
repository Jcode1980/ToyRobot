package com.toyrobot.model;

public interface PlaceableItem {
    public boolean placedOnBoard();
    public int getX();
    public int getY();
    public int getOrientation();
    public void rotate(int direction);

}
