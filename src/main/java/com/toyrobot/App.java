package com.toyrobot;

import com.toyrobot.controller.BoardController;
import com.toyrobot.controller.BoardControllerIMPL;
import com.toyrobot.controller.InputControllerIMPL;
import com.toyrobot.controller.InputController;
import com.toyrobot.model.*;

import java.io.InputStream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        InputController inputController = intializeBoardGame(System.in);
        inputController.readCommandStream();
    }

    public static InputController intializeBoardGame(InputStream in){
        BoardController boardController = new BoardControllerIMPL(new GridBoardGame(4,4));
        InputControllerIMPL inputController = new InputControllerIMPL(boardController, in);
        return inputController;
    }
}
