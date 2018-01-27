package com.toyrobot.controller;

import com.toyrobot.controller.BoardController;
import com.toyrobot.controller.InputController;
import com.toyrobot.model.GridBoard;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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
                processCommand(optionalActionType.get(), commandInput);
            }
            else{
                System.out.print("unknown command: " + commandInput);
                break;
            }
        }
    }

    private void processCommand(ActionType actionType, String command){
        switch (actionType) {
            case PLACE:
        }
    }

    public enum ActionType{
        PLACE("PLACE"),MOVE("MOVE"),RIGHT("RIGHT"),LEFT("LEFT"),
        REPORT("REPORT");

        ActionType(String pattern){this.pattern = pattern;}

        private final String pattern;

        public String pattern(){return pattern;}
        public static List<ActionType> actionTypes(){return List.of(ActionType.PLACE, ActionType.LEFT, ActionType.RIGHT,
                ActionType.MOVE, ActionType.REPORT);}
    }


}
