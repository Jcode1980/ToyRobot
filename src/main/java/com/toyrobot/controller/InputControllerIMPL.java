package com.toyrobot.controller;

import com.toyrobot.enums.ActionType;
import com.toyrobot.enums.CardinalPoint;
import com.toyrobot.enums.RotationDirection;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class reads and interprets the file passed in from command line
 * or the default file.
 * Processing of the file starts when processCommandFile() is called.
 * Valid lines from input stream are then mapped to method called
 * on the BoardController.
 * <p>
 * Invalid / Incorrectly formatted commands are ignored and an error message
 * is logged to log.error.
 * <p>
 * Exceptions that occur during the processing of the actions will also be
 * logged to log.error
 */
public class InputControllerIMPL implements InputController {
    private static final Logger log = Logger.getLogger(InputControllerIMPL.class);
    private static final String DEFAULT_COMMANDS_FILE = "src/main/resources/CommandsExample.txt";
    private static final String COMMA = ",";
    private static final String SPACE = " ";
    private String filePath;
    private BoardController boardController;

    /**
     * Constructor of InputControllerIMPL
     *
     * @param bc the boardController which will bind the actions to the board and item
     * @param filePathParam the file which contains the commands.
     *                      File passed in can be null.
     * @throws NullPointerException  if BoardController passed in is null
     *
     */
    public InputControllerIMPL(BoardController bc, String filePathParam) {
        checkNotNull(bc, "Board controller must not be null", bc);
        boardController = bc;
        filePath = filePathParam != null ? filePathParam :  DEFAULT_COMMANDS_FILE;
    }

    public void processCommandFile() {
        String filePathToUse = filePath != null ? filePath : DEFAULT_COMMANDS_FILE;

        try (Stream<String> stream = Files.lines(Paths.get(filePathToUse))) {
            stream.forEach(this::readCommandLine);
        } catch (IOException e) {
            log.error("Error reading file : " + filePathToUse);
            e.printStackTrace();
        }
    }

    private void readCommandLine(String commandLine) {
        List<ActionType> actionTypes = ActionType.actionTypes();
        actionTypes.stream().filter(
                currentAction -> commandLine.matches(currentAction.pattern())).findFirst()
                .ifPresentOrElse(actionType -> processCommand(actionType, commandLine), () -> log.error("Unknown Command: " + commandLine));
    }

    private void processCommand(ActionType actionType, String command) {

        String resultingInfo = null;
        try {
            switch (actionType) {
                case PLACE:
                    placeAction(command);
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
        } catch (NullPointerException | IllegalArgumentException e) {
            log.error("Exception occurred when processing Action" + command, e);
        }

        if (resultingInfo != null) log.info(resultingInfo);
    }

    private void placeAction(String command) {
        String parametersString = command.substring(command.indexOf(SPACE), command.length());
        List<String> commandList = List.of(parametersString.split(COMMA));
        Integer x = Integer.valueOf(commandList.get(0).trim());
        Integer y = Integer.valueOf(commandList.get(1).trim());
        String cDirection = commandList.get(2);
        CardinalPoint cardinalPoint = CardinalPoint.cardinalDirectionForString(cDirection);
        boardController.place(x, y, cardinalPoint);
    }


}
