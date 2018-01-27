package com.toyrobot.enums;

import java.util.List;
import java.util.Optional;

public enum RotationDirection {
    LEFT(1),RIGHT(2);

    public static List<RotationDirection> rotationDirections = List.of(LEFT,RIGHT);

    RotationDirection(int value){this.value = value;}
    private int value;

    public int value(){return value;}

    public static Optional<RotationDirection> rotationDirectionForInt(int value){
        return rotationDirections.stream().filter(currentDirection -> currentDirection.value == value).findFirst();
    }
}
