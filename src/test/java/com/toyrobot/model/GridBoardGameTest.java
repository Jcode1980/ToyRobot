package com.toyrobot.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GridBoardGameTest {
    private GridBoardGame gridBoardGame;

    @Before
    public void setUp() {
        gridBoardGame = new GridBoardGame(4,4);
    }

    @Test
    public void getWidth() { assertThat(gridBoardGame.getWidth(),is(4) ); }

    @Test
    public void getHeight() {assertThat(gridBoardGame.getHeight(),is(4)); }
}