package com.toyrobot.enums;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

public class CardinalPointTest {

    @Test
    public void getValue() {
        assertEquals(1, CardinalPoint.NORTH.getValue());
    }

    @Test
    public void getName_shouldReturnCorrectCardinalName() {
        assertThat(CardinalPoint.NORTH.name(),is("NORTH"));
        assertThat(CardinalPoint.WEST.name(),is("WEST"));
        assertThat(CardinalPoint.EAST.name(),is("EAST"));
        assertThat(CardinalPoint.SOUTH.name(),is("SOUTH"));
    }

    @Test
    public void rotate_shouldReturnCorrectCardinalPoints() {
        CardinalPoint cardinalPoint1 = CardinalPoint.cardinalPointForRotation(CardinalPoint.NORTH, RotationDirection.LEFT);
        assertEquals(CardinalPoint.WEST, cardinalPoint1);

        CardinalPoint cardinalPoint2 = CardinalPoint.cardinalPointForRotation(CardinalPoint.NORTH, RotationDirection.RIGHT);
        assertThat(cardinalPoint2, is(CardinalPoint.EAST));
    }

    @Test
    public void cardinalPointForInt_shouldReturnCorrectCardinalPoints() {
        CardinalPoint cpNorth = CardinalPoint.cardinalPointForInt(1);
        assertThat(cpNorth, is(CardinalPoint.NORTH));

        CardinalPoint cpEAST = CardinalPoint.cardinalPointForInt(2);
        assertThat(cpEAST, is(CardinalPoint.EAST));

        CardinalPoint cpSOUTH = CardinalPoint.cardinalPointForInt(3);
        assertThat(cpSOUTH, is(CardinalPoint.SOUTH));

        CardinalPoint cpWEST = CardinalPoint.cardinalPointForInt(4);
        assertThat(cpWEST, is(CardinalPoint.WEST));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cardinalPointForInt_shouldThrowExceptionWhenPassedIncorrectIntValue() {
        CardinalPoint.cardinalPointForInt(10);
    }
    @Test
    public void cardinalDirectionForString_shouldReturnCorrectCardinalPoints() {
        CardinalPoint cardinalPoint = CardinalPoint.cardinalDirectionForString("NORTH");
        assertThat(cardinalPoint, is(CardinalPoint.NORTH));

        CardinalPoint cpEAST = CardinalPoint.cardinalDirectionForString("EAST");
        assertThat(cpEAST, is(CardinalPoint.EAST));

        CardinalPoint cpSOUTH = CardinalPoint.cardinalDirectionForString("SOUTH");
        assertThat(cpSOUTH, is(CardinalPoint.SOUTH));

        CardinalPoint cpWEST = CardinalPoint.cardinalDirectionForString("WEST");
        assertThat(cpWEST, is(CardinalPoint.WEST));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cardinalDirectionForString_shouldThrowExceptionWhenPassedIncorrectIntValue() {
        CardinalPoint.cardinalDirectionForString("SILLYSTRING");
    }
}