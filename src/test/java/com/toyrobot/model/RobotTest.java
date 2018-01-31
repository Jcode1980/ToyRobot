package com.toyrobot.model;

import com.toyrobot.enums.CardinalPoint;
import com.toyrobot.enums.RotationDirection;
import org.junit.Before;
import org.junit.Test;


import java.awt.Point;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.is;

public class RobotTest {
    private Robot robot;

    @Before
    public void setUp()  {
        robot = new Robot();
        robot.place(1,4, CardinalPoint.NORTH);
    }

    @Test
    public void placedOnBoard() {
        assertTrue(robot.placedOnBoard());
    }

    @Test
    public void getX() {
        assertThat(robot.getX(),is(1));
    }

    @Test
    public void getY() {
        assertThat(robot.getY(), is(4));
    }

    @Test
    public void cardinalPoint() {
        assertThat(robot.cardinalPoint(), is(CardinalPoint.NORTH));
    }

    @Test
    public void nextMoveCoordinates() {
        Point point = robot.nextMoveCoordinates();
        assertTrue(point.equals(new Point(1, 5)));
    }

    @Test
    public void rotate() {
        robot.rotate(RotationDirection.LEFT);
        assertThat(robot.cardinalPoint(),is(CardinalPoint.WEST));
    }

    @Test
    public void move() {
        robot.move(4,4);
        assertThat(robot.getX(), is(4));
        assertThat(robot.getY(), is(4));
    }

    @Test
    public void place() {
        robot.place(3,4,CardinalPoint.WEST);
        assertThat(robot.getX(), is(3));
        assertThat(robot.getY(), is(4));
        assertTrue(CardinalPoint.WEST.equals( robot.cardinalPoint()));
    }
}