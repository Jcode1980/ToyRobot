package com.toyrobot.controller;

import com.toyrobot.enums.ActionType;
import com.toyrobot.enums.CardinalPoint;
import com.toyrobot.enums.RotationDirection;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.toyrobot.enums.ActionType.PLACE;

/**
 * This class binds the inputstream to the BoardController.
 * Input stream that is passed in within the constructor starts
 * being read when readCommandStream() is called.
 * Valid lines from input stream are then mapped to method called
 * on the BoardController
 *
 */
public class InputControllerIMPL implements InputController {
    private static final Logger log = Logger.getLogger(InputControllerIMPL.class);
    private static final String DEFAULT_COMMANDS_FILE = "src/main/resources/CommandsExample.txt";

    private String filePath;
    private BoardController boardController;

    public InputControllerIMPL(BoardController bc, String filePathParam){
        checkNotNull(bc, "Board controller must not be null", bc);
        boardController = bc;
        filePath = filePathParam;
    }

    public void processCommandFile(){
        String filePathToUse = filePath != null ? filePath : DEFAULT_COMMANDS_FILE;
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(filePathToUse))) {

            stream.forEach(line->readCommandLine(line));

        } catch (IOException e) {
            log.error("Error reading file : " + filePathToUse);
            e.printStackTrace();
        }
    }



    private void readCommandLine(String commandLine){
        List<ActionType> actionTypes = ActionType.actionTypes();
        actionTypes.stream().filter(
                    currentAction -> commandLine.matches(currentAction.pattern())).findFirst()
                    //.ifPresentOrElse(s->System.out.println(processCommand(s, commandLine)),() -> System.out.print("unknown command: " + commandLine));
                .ifPresentOrElse(actionType->processCommand(actionType, commandLine),() -> log.error("Unknown Command: " + commandLine));
    }


    private void processCommand(ActionType actionType, String command){
        String resultingInfo = null;

        switch (actionType) {
            case PLACE:
                String parametersString = command.substring(command.indexOf(" "), command.length());
                //System.out.println("these are the parameters: " + parametersString);
                List<String> commandList =  List.of(parametersString.split(","));
                Integer x = Integer.valueOf(commandList.get(0).trim());
                Integer y = Integer.valueOf(commandList.get(1).trim());
                String cDirection  = commandList.get(2);
                CardinalPoint cardinalPoint = CardinalPoint.cardinalDirectionForString(cDirection);
                boardController.place(x, y, cardinalPoint);
                break;

            case REPORT:
                resultingInfo = boardController.report();
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

        if(resultingInfo != null) log.info(resultingInfo);


    }





}
