package com.toyrobot.enums;

import java.util.List;
import java.util.Optional;

public enum CardinalDirection {
    NORTH(1, "NORTH"),EAST(2, "SOUTH"),SOUTH(3, "EAST"),WEST(4, "WEST");

    public static List<CardinalDirection> cardinalDirections = List.of(NORTH,EAST,SOUTH,WEST);

    CardinalDirection(int value, String name){this.value = value; this.name = name;}

    private int value;
    private String name;
    public int value(){return value;}

    public static CardinalDirection rotate(CardinalDirection currentDirection, RotationDirection rotationDirection){
        int indexOf = cardinalDirections.indexOf(currentDirection);
        int indexOfNextDirection = 0;

        if(RotationDirection.LEFT.equals(rotationDirection)){
            indexOfNextDirection = (indexOf != 0 ? (indexOf -1) : 3);
        }

        if(RotationDirection.RIGHT.equals(rotationDirection)){
            indexOfNextDirection = (indexOf != 3 ? (indexOf + 1) : 0);
        }

        return cardinalDirections.get(indexOfNextDirection);
    }

    public static Optional<CardinalDirection> cardinalDirectionForInt(int value){
        return cardinalDirections.stream().filter(currentDirection -> currentDirection.value == value).findFirst();
    }

    public static Optional<CardinalDirection> cardinalDirectionForString(String value){
        return cardinalDirections.stream().filter(currentDirection -> currentDirection.name().equalsIgnoreCase(value)).findFirst();
    }
}
