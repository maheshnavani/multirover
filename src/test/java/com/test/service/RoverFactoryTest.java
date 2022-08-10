package com.test.service;

import com.test.constant.Direction;
import com.test.rover.Rover;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoverFactoryTest {

    @Test
    public void testGoodData() throws  Exception{
        RoverFactory roverFactory = new RoverFactory();
        Rover rover = roverFactory.createRover("3,4,N");
        assertEquals(rover.getLocation().getX(), 3);
        assertEquals(rover.getLocation().getY(), 4);
        assertEquals(rover.getLocation().getDirection(), Direction.NORTH);
    }

    @Test
    public void testIncompleteData() {
        RoverFactory roverFactory = new RoverFactory();
        assertThrows(IllegalArgumentException.class,() -> {
                    Rover rover = roverFactory.createRover("3,4");
                });
    }

    @Test
    public void testNaN() {
        RoverFactory roverFactory = new RoverFactory();
        assertThrows(NumberFormatException.class,() -> {
            Rover rover = roverFactory.createRover("3,S,N");
        });
        assertThrows(NumberFormatException.class,() -> {
            Rover rover = roverFactory.createRover("J,4,N");
        });

    }

}