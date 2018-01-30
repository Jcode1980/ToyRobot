package com.toyrobot;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class AppTest {
    @Mock
    private Appender mockAppender;
    @Captor
    private ArgumentCaptor<LoggingEvent> captorLoggingEvent;

    @Before
    public void setup(){
        Logger root = (Logger) Logger.getRootLogger();
        root.addAppender(mockAppender);
        root.setLevel(Level.INFO);
    }


    @org.junit.Test
   public void main_shouldOutputCorrectValue(){
        App.main(new String[0]);

        verify(mockAppender, times(3)).doAppend(captorLoggingEvent.capture());
        LoggingEvent loggingEvent1 = captorLoggingEvent.getAllValues().get(0);
        LoggingEvent loggingEvent2 = captorLoggingEvent.getAllValues().get(1);
        LoggingEvent loggingEvent3 = captorLoggingEvent.getAllValues().get(2);

        assertThat(loggingEvent1.getMessage(),is("0,1,NORTH"));
        assertThat(loggingEvent2.getMessage(),is("0,0,WEST"));
        assertThat(loggingEvent3.getMessage(),is("3,3,NORTH"));
   }
}
