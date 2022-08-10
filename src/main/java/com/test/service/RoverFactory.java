package com.test.service;

import com.test.constant.Direction;
import com.test.rover.Rover;

public class RoverFactory {

    public static Rover createRover (String intialPosiiton)  {
        String args[] = intialPosiiton.split(",");
        if ( args.length < 4)
            throw new  IllegalArgumentException("Invalid Initial Posiiton");
        String label = args[0];
        int x = Integer.valueOf(args[1]);
        int y = Integer.valueOf(args[2]);

        Direction direction = Direction.getDirection(args[3]);
        if ( direction == null)
            throw new  IllegalArgumentException("Invalid Initial Posiiton");

        Rover rover = new Rover(label,x,y,direction);
        return rover;
    }
}
