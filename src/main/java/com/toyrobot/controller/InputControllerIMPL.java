package com.toyrobot.controller;

import com.toyrobot.enums.ActionType;
import com.toyrobot.enums.CardinalPoint;
import com.toyrobot.enums.RotationDirection;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * This class binds the inputstream to the BoardController.
 * Input stream that is passed in within the constructor starts
 * being read when readCommandStream() is called.
 * Valid lines from input stream are then mapped to method called
 * on the BoardController
 *
 */
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

            actionTypes.stream().filter(
                    currentAction -> commandInput.matches(currentAction.pattern())).findFirst()
                    .ifPresentOrElse(s->System.out.println(processCommand(s, commandInput)),() -> System.out.print("unknown command: " + commandInput));



//            if(optionalActionType.isPresent()){
//                System.out.println(processCommand(optionalActionType.get(), commandInput));
//            }
//            else{
//                System.out.print("unknown command: " + commandInput);
//                break;
//            }
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
                CardinalPoint cardinalPoint = CardinalPoint.cardinalDirectionForString(cDirection);
                boardController.place(x, y, cardinalPoint);
                break;

            case REPORT:
                returnObject = boardController.report();
                break;
            case MOVE:
                boardController.move();
                break;
            case LEFT:
                boardController.rotate(RotationDirection.LEFT);
                break;
            case RIGHT:
                boardController.rotate(RotationDirection.RIGHT);
                break;
        }

        return returnObject;


    }





}
