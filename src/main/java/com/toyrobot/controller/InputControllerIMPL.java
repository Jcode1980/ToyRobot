package com.toyrobot.controller;

import com.toyrobot.controller.BoardController;
import com.toyrobot.controller.InputController;
import com.toyrobot.enums.ActionType;
import com.toyrobot.enums.CardinalDirection;
import com.toyrobot.enums.RotationDirection;
import com.toyrobot.model.GridBoard;

import javax.swing.text.html.Option;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static java.util.List.of;
import static java.util.Optional.empty;

public class InputControllerIMPL implements InputController {
    private InputStream inputStream;
    private BoardController boardController;

    public InputControllerIMPL(BoardController bc, InputStream is){
        inputStream = is;
        boardController = bc;

    }

    public void readCommandStream() {
        List<ActionType> actionTypes = ActionType.actionTypes();

//        try{
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            System.out.print("Enter your name: ");
//            String name = reader.readLine();
//            System.out.println("Your name is: " + name);
//        }catch (IOException e){
//            e.printStackTrace();
//        }

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.print("Please enter your selection:\t");
            String commandInput = scanner.nextLine();

            Optional<ActionType> optionalActionType = actionTypes.stream().filter(
                    currentAction -> commandInput.matches(currentAction.pattern())).findFirst();

            if(optionalActionType.isPresent()){
                System.out.println(processCommand(optionalActionType.get(), commandInput));
            }
            else{
                System.out.print("unknown command: " + commandInput);
                break;
            }
        }
    }

    private Optional<String> processCommand(ActionType actionType, String command){
        Optional<String> returnObject = Optional.empty();

        switch (actionType) {
            case PLACE:
                List<String> commandList =  List.of(command.split("\\s+"));
                Integer x = Integer.valueOf(commandList.get(1));
                Integer y = Integer.valueOf(commandList.get(2));
                String cDirection  = commandList.get(3);
                Optional<CardinalDirection> theDirection = CardinalDirection.cardinalDirectionForString(cDirection);
                if(theDirection.isPresent()) {
                    boardController.place(x, y, theDirection.get().value());
                }
                break;

            case REPORT:
                returnObject = boardController.report();
                break;
            case MOVE:
                boardController.move();
                break;
            case LEFT:
                boardController.rotate(RotationDirection.LEFT.value());
                break;
            case RIGHT:
                boardController.rotate(RotationDirection.RIGHT.value());
                break;
        }

        return returnObject;


    }





}
