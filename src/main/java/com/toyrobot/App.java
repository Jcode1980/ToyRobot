package com.toyrobot;

import com.toyrobot.controller.BoardController;
import com.toyrobot.controller.BoardControllerIMPL;
import com.toyrobot.controller.InputControllerIMPL;
import com.toyrobot.controller.InputController;
import com.toyrobot.model.GridBoardGame;
import com.toyrobot.model.Robot;

public class App
{
    private static final int FOUR_INT = 4;

    public static void main( String[] args ) {
        InputController inputController = intializeBoardGame(args);
        inputController.processCommandFile();

    }

    private static InputController intializeBoardGame(String[] args){
        BoardController boardController = new BoardControllerIMPL(new GridBoardGame(FOUR_INT,FOUR_INT), new Robot());
        return new InputControllerIMPL(boardController, args.length > 0 ? args[0] : null);
    }
}
