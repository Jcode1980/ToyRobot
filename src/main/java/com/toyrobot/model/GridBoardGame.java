package com.toyrobot.model;

import com.toyrobot.controller.BoardController;

public class GridBoardGame implements GridBoard {
    private BoardController boardController;
    private int width;
    private int length;


    public GridBoardGame(int width, int length){
        this.width=width;
        this.length=length;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }
}
