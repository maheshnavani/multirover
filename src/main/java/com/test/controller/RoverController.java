package com.test.controller;

import com.test.constant.MoveCommand;
import com.test.exception.DuplicateRover;
import com.test.rover.Rover;
import com.test.rover.RoverCommand;
import com.test.service.NextMove;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class RoverController {
    private Map<String, Rover> roverMap;
    private List<Rover> roverList;
    private NextMove nextMove;
    public RoverController() {
        this.roverMap = new HashMap<>();
        this.roverList = new ArrayList<>();
        this.nextMove = new NextMove();
    }

    public void addRover(Rover rover) throws Exception {
        if (this.roverMap.containsKey(rover.getLabel()))
            throw new DuplicateRover("Duplicate Rover" + rover.getLabel());
        this.roverMap.put(rover.getLabel(), rover);
        this.roverList.add(rover);
    }

    public List<Rover> getRoverList() {
        return this.roverList;
    }

    public boolean move(RoverCommand roverCommand) {
        boolean success = true;
        Rover rover = this.roverMap.get(roverCommand.getLabel());
        String[] commands = roverCommand.getCommand().split(",");
        for (String command: commands) {
            MoveCommand moveCommand = MoveCommand.getCommand(command);
            if (MoveCommand.FORWARD == moveCommand || MoveCommand.BACK == moveCommand) {
                synchronized (roverMap) {
                    success = nextMove.checkCollisionAndSetXY(rover, moveCommand, roverList);
                }
            }
            else if (MoveCommand.ROTATE_RIGHT == moveCommand || MoveCommand.ROTATE_LEFT == moveCommand) {
                success = nextMove.setRotate(rover,moveCommand);
            }
            // Ignore rest of commands if any of them fails
            if (!success ) break;
        }
        log.info("Post Move:" + rover);

        return success;

    }
}
