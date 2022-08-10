package com.test.controller;

import com.test.constant.Direction;
import com.test.rover.Rover;
import com.test.rover.RoverCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoverControllerTest {
    public static final String ROVER1= "Rover1";

    @Test
    public void testMoveForward() throws Exception{
        Rover rover = new Rover(ROVER1, 3,4, Direction.NORTH);
        RoverController roverController = new RoverController();
        roverController.addRover(rover);
        RoverCommand roverCommand = new RoverCommand(ROVER1,"f");
        roverController.move(roverCommand);
        assertEquals(rover.getLocation().getDirection(),Direction.NORTH);
        assertEquals(rover.getLocation().getX(),4);
        assertEquals(rover.getLocation().getY(),4);
    }

    @Test
    public void testMoveBack() throws Exception{
        Rover rover = new Rover(ROVER1,3,4,Direction.NORTH);
        RoverController roverController = new RoverController();
        roverController.addRover(rover);
        RoverCommand roverCommand = new RoverCommand(ROVER1,"b");
        roverController.move(roverCommand);
        assertEquals(rover.getLocation().getDirection(),Direction.NORTH);
        assertEquals(rover.getLocation().getX(),2);
        assertEquals(rover.getLocation().getY(),4);
    }

    @Test
    public void testRotateRight() throws Exception{
        Rover rover = new Rover(ROVER1,3,4,Direction.NORTH);
        RoverController roverController = new RoverController();
        roverController.addRover(rover);
        RoverCommand roverCommand = new RoverCommand(ROVER1,"r");
        roverController.move(roverCommand);
        assertEquals(rover.getLocation().getDirection(),Direction.EAST);
        assertEquals(rover.getLocation().getX(),3);
        assertEquals(rover.getLocation().getY(),4);
    }

    @Test
    public void testRotateLeft() throws Exception {
        Rover rover = new Rover(ROVER1,3,4,Direction.NORTH);
        RoverController roverController = new RoverController();
        roverController.addRover(rover);
        RoverCommand roverCommand = new RoverCommand(ROVER1,"l");
        roverController.move(roverCommand);
        assertEquals(rover.getLocation().getDirection(),Direction.WEST);
        assertEquals(rover.getLocation().getX(),3);
        assertEquals(rover.getLocation().getY(),4);
    }


}