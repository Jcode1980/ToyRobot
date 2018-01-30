package com.toyrobot.enums;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

public class ActionTypeTest {

    @Test
    public void pattern() {
        assertEquals("(?i)LEFT(?-i)", ActionType.LEFT.pattern());
        assertThat(ActionType.LEFT.pattern(), is("(?i)LEFT(?-i)"));
    }

    @Test
    public void actionTypes() {
        List<ActionType> list = ActionType.actionTypes();
        assertThat(list, contains(ActionType.PLACE, ActionType.LEFT, ActionType.RIGHT,
                ActionType.MOVE, ActionType.REPORT));
    }
}