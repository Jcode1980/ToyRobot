package com.toyrobot.controller;


import com.toyrobot.model.GridBoardGame;
import com.toyrobot.model.Robot;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class InputControllerIMPLTest {
    private static final String DEFAULT_SAMPLES_FILE = "src/main/resources/CommandsExample.txt";
    private static final String DEFAULT_TEST_BOUNDARIES_FILE = "src/main/resources/CommandsExampleTestBoundaries.txt";
    private InputControllerIMPL inputControllerIMPL;
    private static final String DUMMY_FILEPATH = "sillyFilePath/xxx.txt";

    @Mock
    private Appender mockAppender;
    @Captor
    private ArgumentCaptor<LoggingEvent> captorLoggingEvent;

    @Test
    public void processCommandFile_shouldReturnExceptionWhenControllerCantReadFile(){
        Logger root =  Logger.getRootLogger();
        root.addAppender(mockAppender);
        root.setLevel(Level.ERROR);

        inputControllerIMPL = new InputControllerIMPL(new BoardControllerIMPL(
                new GridBoardGame(4,4), new Robot()), DUMMY_FILEPATH);
        inputControllerIMPL.processCommandFile();

        verify(mockAppender, times(1)).doAppend(captorLoggingEvent.capture());
        LoggingEvent loggingEvent = captorLoggingEvent.getAllValues().get(0);
        assertThat(loggingEvent.getMessage(),is("Error reading file : " + DUMMY_FILEPATH));
    }

    @Test
    public void IT_processCommandFile_shouldOutputCorrectValue() {
        Logger root = Logger.getRootLogger();
        root.addAppender(mockAppender);
        root.setLevel(Level.INFO);

        inputControllerIMPL = new InputControllerIMPL(new BoardControllerIMPL(
                new GridBoardGame(4,4), new Robot()), null);
        inputControllerIMPL.processCommandFile();
        verify(mockAppender, times(3)).doAppend(captorLoggingEvent.capture());
        LoggingEvent loggingEvent1 = captorLoggingEvent.getAllValues().get(0);
        LoggingEvent loggingEvent2 = captorLoggingEvent.getAllValues().get(1);
        LoggingEvent loggingEvent3 = captorLoggingEvent.getAllValues().get(2);

        assertThat(loggingEvent1.getMessage(),is("0,1,NORTH"));
        assertThat(loggingEvent2.getMessage(),is("0,0,WEST"));
        assertThat(loggingEvent3.getMessage(),is("3,3,NORTH"));
    }

    @Test
    public void IT_processCommandFile_shouldOutputCorrectValueWithMovesTestingBoundaries() {
        Logger root =  Logger.getRootLogger();
        root.addAppender(mockAppender);
        root.setLevel(Level.INFO);

        inputControllerIMPL = new InputControllerIMPL(new BoardControllerIMPL(
                new GridBoardGame(4,4), new Robot()), DEFAULT_TEST_BOUNDARIES_FILE);
        inputControllerIMPL.processCommandFile();
        verify(mockAppender, times(4)).doAppend(captorLoggingEvent.capture());
        LoggingEvent loggingEvent1 = captorLoggingEvent.getAllValues().get(0);
        LoggingEvent loggingEvent2 = captorLoggingEvent.getAllValues().get(1);
        LoggingEvent loggingEvent3 = captorLoggingEvent.getAllValues().get(2);
        LoggingEvent loggingEvent4 = captorLoggingEvent.getAllValues().get(3);

        assertThat(loggingEvent1.getMessage(),is("3,2,SOUTH"));
        assertThat(loggingEvent2.getMessage(),is("2,2,WEST"));
        assertThat(loggingEvent3.getMessage(),is("0,2,SOUTH"));
        assertThat(loggingEvent4.getMessage(),is("0,0,SOUTH"));
    }

    @Test(expected = NullPointerException.class)
    public void contructor_shouldThrowExceptionWhenPassedNullBoardController(){
        new InputControllerIMPL(null, DEFAULT_SAMPLES_FILE);
    }





}