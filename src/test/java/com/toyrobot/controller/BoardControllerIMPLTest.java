package com.toyrobot.controller;

import com.toyrobot.enums.CardinalPoint;
import com.toyrobot.enums.RotationDirection;
import com.toyrobot.model.GridBoard;
import com.toyrobot.model.PlaceableItem;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.awt.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BoardControllerIMPLTest {
    @Mock
    private PlaceableItem placeableItemMock;
    @Mock
    private GridBoard gridBoardMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {  }

    @Test(expected = NullPointerException.class)
    public void constructor_shouldThrowExceptionWhenPassedNullGridBoard(){
        new BoardControllerIMPL(null, placeableItemMock);
    }

    @Test(expected = NullPointerException.class)
    public void constructor_shouldThrowExceptionWhenPassedNullPlaceableItem(){
        new BoardControllerIMPL(gridBoardMock, null);
    }

    @Test
    public void move_shouldReturnTrueAndMoveMethodShouldBeCalledOnItem() {
        BoardControllerIMPL boardControllerIMPL = new BoardControllerIMPL(gridBoardMock, placeableItemMock);
        when(placeableItemMock.nextMoveCoordinates()).thenReturn(new Point(1,2));
        when(placeableItemMock.placedOnBoard()).thenReturn(true);
        when(gridBoardMock.getWidth()).thenReturn(4);
        when(gridBoardMock.getHeight()).thenReturn(4);

        assertTrue(boardControllerIMPL.move());
        verify(placeableItemMock).move(1,2);
    }

    @Test
    public void move_shouldReturnFalseWhenGivenOutOfBoundsCoordinates() {
        BoardControllerIMPL boardControllerIMPL = new BoardControllerIMPL(gridBoardMock, placeableItemMock);
        when(placeableItemMock.nextMoveCoordinates()).thenReturn(new Point(3,2));
        when(placeableItemMock.placedOnBoard()).thenReturn(true);

        when(gridBoardMock.getWidth()).thenReturn(2);
        when(gridBoardMock.getHeight()).thenReturn(2);

        assertFalse(boardControllerIMPL.move());
    }

    @Test
    public void rotate_shouldCallRotateOnItem() {
        BoardControllerIMPL boardControllerIMPL = new BoardControllerIMPL(gridBoardMock, placeableItemMock);
        boardControllerIMPL.rotate(RotationDirection.LEFT);
        verify(placeableItemMock).rotate(RotationDirection.LEFT);
    }

    @Test(expected = NullPointerException.class)
    public void rotate_shouldReturnExceptionWhenGivenNullDirection() {
        BoardControllerIMPL boardControllerIMPL = new BoardControllerIMPL(gridBoardMock, placeableItemMock);
        boardControllerIMPL.rotate(null);

    }

    @Test
    public void place_shouldReturnFalseWhenGivenOutOfBoundsCoordinates() {
        BoardControllerIMPL boardControllerIMPL = new BoardControllerIMPL(gridBoardMock, placeableItemMock);
        when(gridBoardMock.getWidth()).thenReturn(4);
        when(gridBoardMock.getHeight()).thenReturn(4);

        assertFalse(boardControllerIMPL.place(5,5,CardinalPoint.NORTH));
    }

    @Test
    public void place_shouldReturnTrueWhenGivenValidValues() {
        BoardControllerIMPL boardControllerIMPL = new BoardControllerIMPL(gridBoardMock, placeableItemMock);
        when(gridBoardMock.getWidth()).thenReturn(4);
        when(gridBoardMock.getHeight()).thenReturn(4);

        assertTrue(boardControllerIMPL.place(2,2,CardinalPoint.NORTH));
    }

    @Test
    public void place_shouldReturnFalseWhenGivenNegativeXValue() {
        BoardControllerIMPL boardControllerIMPL = new BoardControllerIMPL(gridBoardMock, placeableItemMock);
        assertFalse(boardControllerIMPL.place(-1, 10,CardinalPoint.NORTH));
    }

    @Test
    public void place_shouldReturnFalseWhenGivenNegativeYValue() {
        BoardControllerIMPL boardControllerIMPL = new BoardControllerIMPL(gridBoardMock, placeableItemMock);
        assertFalse(boardControllerIMPL.place(1, -1,CardinalPoint.NORTH));
    }

    @Test(expected = NullPointerException.class)
    public void place_shouldReturnExceptionWhenGivenNullCardinalPoint() {
        BoardControllerIMPL boardControllerIMPL = new BoardControllerIMPL(gridBoardMock, placeableItemMock);
        assertFalse(boardControllerIMPL.place(1, 10,null));
    }


    @Test
    public void place_shouldReturnFalsewhenGivenOutOfBoundsCoordinates(){
        BoardControllerIMPL boardControllerIMPL = new BoardControllerIMPL(gridBoardMock, placeableItemMock);
        assertFalse(boardControllerIMPL.place(10, 10,CardinalPoint.NORTH));
    }

    @Test
    public void report_shouldReturnCorrectStringData() {
        BoardControllerIMPL boardControllerIMPL = new BoardControllerIMPL(gridBoardMock, placeableItemMock);
        when(placeableItemMock.getX()).thenReturn(1);
        when(placeableItemMock.getY()).thenReturn(1);
        when(placeableItemMock.cardinalPoint()).thenReturn(CardinalPoint.NORTH);

        String reportOptional = boardControllerIMPL.report();
        assertThat(reportOptional,is("1,1,NORTH"));
    }
}