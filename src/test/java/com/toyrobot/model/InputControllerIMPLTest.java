package com.toyrobot.model;
import com.toyrobot.controller.InputControllerIMPL;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;



public class InputControllerIMPLTest {

    @Test
    public void InputControllerIMPL_CreateInstanceWithStandardInput_NoExceptionsExpected()
    {
        InputControllerIMPL inputControllerIMPL = new InputControllerIMPL(System.in);
        assertNotNull(inputControllerIMPL);
    }

}
