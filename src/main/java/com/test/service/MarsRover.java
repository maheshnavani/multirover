package com.test.service;



import com.test.rover.Rover;
import com.test.rover.RoverCommand;
import com.test.controller.RoverController;
import lombok.extern.slf4j.Slf4j;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;


@Slf4j
public class MarsRover {
    private final RoverController roverController;
    private final BlockingQueue<RoverCommand> commandQueue  ;


    public MarsRover() {
        this.commandQueue = new LinkedBlockingQueue<RoverCommand>() ;
        this.roverController = new RoverController();
    }

    public void initRovers() {
        List<String> dataList = getRoverData("src/main/resources/data.txt");
        for (String line: dataList) {
            String[] data = line.split(":");
            if ( data.length != 2)
                continue;
            try {
                Rover rover = RoverFactory.createRover(data[0]);
                this.roverController.addRover(rover);
                this.commandQueue.add(new RoverCommand(rover.getLabel(),data[1]));
            }
            catch( Exception e) {
                log.warn ("ERROR Rover addition" + e);
            }
        }
        log.info("Rovers Post Init");
        printRoverPosition();
    }

    public void start() {
        Thread t = new Thread( () -> {
            this.moveRovers(); }
        );
        t.start();
    }

    public void moveRovers() {
        while ( true) {
            try {
                RoverCommand roverCommand = this.commandQueue.take();
                this.roverController.move(roverCommand);
            }
            catch( Exception e) {
                log.warn("ERROR reading from command queue" + e);
            }
        }
    }

    public List<String> getRoverData(String fileName) {
        try {
            Path dataPath = Paths.get(fileName);
            List<String> dataList = Files.readAllLines(dataPath);
            return dataList;
        } catch (Exception e) {
            log.warn("Error Reading file " + e);
        }
        return new ArrayList<String>();
    }

    public void printRoverPosition() {
        for(Rover rover : roverController.getRoverList()) {
            log.info(rover.toString());
        }
    }
}
