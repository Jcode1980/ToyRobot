package com.toyrobot.controller;

import com.toyrobot.model.PlaceableItem;

import java.util.Optional;

public interface BoardController {

    public boolean move();
    public void rotate(int direction);
    public boolean place(int x, int y, int cardinalDirection);
    public Optional<String> report();
}
