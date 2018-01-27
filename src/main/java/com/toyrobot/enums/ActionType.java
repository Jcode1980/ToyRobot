package com.toyrobot.enums;

import java.util.List;

import static java.util.List.of;

public enum ActionType{
    PLACE("(?i)PLACE\\s[0-9]+\\s[0-9]+\\s(NORTH|SOUTH|EAST|WEST)(?-i)"),MOVE("(?i)MOVE(?-i)"),RIGHT("(?i)RIGHT(?-i)"),LEFT("(?i)LEFT(?-i)"),
    REPORT("(?i)REPORT(?-i)");

    ActionType(String pattern){this.pattern = pattern;}

    private final String pattern;

    public String pattern(){return pattern;}
    public static List<ActionType> actionTypes(){return of(ActionType.PLACE, ActionType.LEFT, ActionType.RIGHT,
            ActionType.MOVE, ActionType.REPORT);}
}