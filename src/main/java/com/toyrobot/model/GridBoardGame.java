package com.toyrobot.model;

import com.toyrobot.controller.BoardController;

public class GridBoardGame implements GridBoard {
    private BoardController boardController;
    private int width;
    private int height;


    public GridBoardGame(int width, int height){
        this.width=width;
        this.height=height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
