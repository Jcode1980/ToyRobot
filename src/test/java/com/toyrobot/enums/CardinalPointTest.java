package com.toyrobot.enums;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CardinalPointTest {

    @Test
    public void getValue() {
        assertEquals(1, CardinalPoint.NORTH.getValue());
    }

    @Test
    public void getName_shouldReturnCorrectCardinalName() {
        CardinalPoint cardinalPoint = CardinalPoint.NORTH;
        assertThat(CardinalPoint.NORTH.getName(),is("NORTH"));
        assertThat(CardinalPoint.WEST.getName(),is("WEST"));
        assertThat(CardinalPoint.EAST.getName(),is("EAST"));
        assertThat(CardinalPoint.SOUTH.getName(),is("SOUTH"));
    }

    @Test
    public void rotate_shouldReturnCorrectCardinalPoints() {
        CardinalPoint cardinalPoint1 = CardinalPoint.cardinalPointForRotation(CardinalPoint.NORTH, RotationDirection.LEFT);
        assertEquals(CardinalPoint.WEST, cardinalPoint1);

        CardinalPoint cardinalPoint2 = CardinalPoint.cardinalPointForRotation(CardinalPoint.NORTH, RotationDirection.RIGHT);
        assertEquals(CardinalPoint.EAST, cardinalPoint2);
    }

    @Test
    public void cardinalPointForInt_shouldReturnCorrectCardinalPoints() {
        CardinalPoint cpNorth = CardinalPoint.cardinalPointForInt(1);
        assertEquals(CardinalPoint.NORTH,cpNorth);

        CardinalPoint cpEAST = CardinalPoint.cardinalPointForInt(2);
        assertEquals(CardinalPoint.EAST,cpEAST);

        CardinalPoint cpSOUTH = CardinalPoint.cardinalPointForInt(3);
        assertEquals(CardinalPoint.SOUTH,cpSOUTH);

        CardinalPoint cpWEST = CardinalPoint.cardinalPointForInt(4);
        assertEquals(CardinalPoint.WEST,cpWEST);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cardinalPointForInt_shouldThrowExceptionWhenPassedIncorrectIntValue() {
        CardinalPoint.cardinalPointForInt(10);
    }
    @Test
    public void cardinalDirectionForString_shouldReturnCorrectCardinalPoints() {
        CardinalPoint cardinalPoint = CardinalPoint.cardinalDirectionForString("NORTH");
        assertEquals(CardinalPoint.NORTH,cardinalPoint);

        CardinalPoint cpEAST = CardinalPoint.cardinalDirectionForString("EAST");
        assertEquals(CardinalPoint.EAST,cpEAST);

        CardinalPoint cpSOUTH = CardinalPoint.cardinalDirectionForString("SOUTH");
        assertEquals(CardinalPoint.SOUTH,cpSOUTH);

        CardinalPoint cpWEST = CardinalPoint.cardinalDirectionForString("WEST");
        assertEquals(CardinalPoint.WEST,cpWEST);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cardinalDirectionForString_shouldThrowExceptionWhenPassedIncorrectIntValue() {
        CardinalPoint.cardinalDirectionForString("SILLYSTRING");
    }
}