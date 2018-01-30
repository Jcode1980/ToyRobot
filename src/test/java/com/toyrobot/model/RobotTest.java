package com.toyrobot.model;

import com.toyrobot.enums.CardinalPoint;
import com.toyrobot.enums.RotationDirection;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

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
        assertEquals(1,robot.getX());
    }

    @Test
    public void getY() {
        assertEquals(4, robot.getY());
    }

    @Test
    public void cardinalPoint() {
        assertEquals(CardinalPoint.NORTH, robot.cardinalPoint());
    }

    @Test
    public void nextMoveCoordinates() {
        Point point = robot.nextMoveCoordinates();
        assertTrue(point.equals(new Point(1, 5)));
    }

    @Test
    public void rotate() {
        robot.rotate(RotationDirection.LEFT);
        assertEquals(CardinalPoint.WEST,robot.cardinalPoint());
    }

    @Test
    public void move() {
        robot.move(4,4);
        assertEquals(4,robot.getX());
        assertEquals(4,robot.getY());
    }

    @Test
    public void place() {
        robot.place(3,4,CardinalPoint.WEST);
        assertEquals(3,robot.getX());
        assertEquals(4,robot.getY());
        assertTrue(CardinalPoint.WEST.equals( robot.cardinalPoint()));
    }
}