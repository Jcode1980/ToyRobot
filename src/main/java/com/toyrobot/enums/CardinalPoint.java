package com.toyrobot.enums;

import java.util.List;

public enum CardinalPoint {
    NORTH(1, "NORTH"),EAST(2, "EAST"),SOUTH(3, "SOUTH"),WEST(4, "WEST");

    public static List<CardinalPoint> cardinalPoints = List.of(NORTH,EAST,SOUTH,WEST);

    CardinalPoint(int value, String name){this.value = value; this.name = name;}

    private int value;
    private String name;
    public int getValue(){return value;}
    public String getName(){return name;}

    public static CardinalPoint rotate(CardinalPoint currentDirection, RotationDirection rotationDirection){
        int indexOf = cardinalPoints.indexOf(currentDirection);
        int indexOfNextDirection = 0;

        if(RotationDirection.LEFT.equals(rotationDirection))indexOfNextDirection = (indexOf != 0 ? (indexOf -1) : 3);

        if(RotationDirection.RIGHT.equals(rotationDirection)) indexOfNextDirection = (indexOf != 3 ? (indexOf + 1) : 0);

        return cardinalPoints.get(indexOfNextDirection);
    }

    public static CardinalPoint cardinalPointForInt(int value){
        return cardinalPoints.stream().filter(currentDirection -> currentDirection.value == value).findFirst().orElseThrow(() ->
                new IllegalArgumentException("Cardinal Point with value: " + value + " not found"));
    }

    public static CardinalPoint cardinalDirectionForString(String value){
        return cardinalPoints.stream().filter(currentDirection -> currentDirection.name().equalsIgnoreCase(value)).findFirst().orElseThrow(() ->
                new IllegalArgumentException("Cardinal Point with name: " + value + " not found"));
    }
}
