package com.test.service;

import com.test.constant.Direction;
import com.test.constant.DirectionXY;
import com.test.constant.MoveCommand;
import com.test.rover.Location;
import com.test.rover.Rover;

import java.util.List;

public class NextMove {
    private int[][] move  = new int [5][5];

    public NextMove() {
        initMove();
    }

    public void initMove()  {
        move[DirectionXY.X_FORWARD.ordinal()][Direction.NORTH.ordinal()] = 1;
        move[DirectionXY.X_FORWARD.ordinal()][Direction.SOUTH.ordinal()] = -1;
        move[DirectionXY.X_BACK.ordinal()][Direction.NORTH.ordinal()] = -1;
        move[DirectionXY.X_BACK.ordinal()][Direction.SOUTH.ordinal()] = 1;
        move[DirectionXY.Y_FORWARD.ordinal()][Direction.EAST.ordinal()] = 1;
        move[DirectionXY.Y_FORWARD.ordinal()][Direction.WEST.ordinal()] = -1;
        move[DirectionXY.Y_BACK.ordinal()][Direction.EAST.ordinal()] = -1;
        move[DirectionXY.Y_BACK.ordinal()][Direction.WEST.ordinal()] = 1;
    }

    public long nextX (Location location, MoveCommand command) {
        Direction direction = location.getDirection();
        if (MoveCommand.FORWARD == command ) {
            return location.getX() + this.move[DirectionXY.X_FORWARD.ordinal()][direction.ordinal()];
        }
        else if (MoveCommand.BACK == command) {
            return location.getX() + this.move[DirectionXY.X_BACK.ordinal()][direction.ordinal()];
        }
        else {
            return location.getX();
        }

    }
    public long nextY(Location location, MoveCommand command) {
        Direction direction = location.getDirection();
        if (MoveCommand.FORWARD == command ) {
            return location.getY() + this.move[DirectionXY.Y_FORWARD.ordinal()][direction.ordinal()];
        }
        else if (MoveCommand.BACK == command) {
            return location.getY() + this.move[DirectionXY.Y_BACK.ordinal()][direction.ordinal()];
        }
        else {
            return location.getX();
        }
    }

    public Direction rotate(Location location, MoveCommand command) {
        if (MoveCommand.ROTATE_LEFT == command)
            return rotateLeft(location);
        else if (MoveCommand.ROTATE_RIGHT == command)
            return rotateRight(location);
        else
            return location.getDirection();
    }
    public Direction rotateRight(Location location) {
        int newDirection = (location.getDirection().ordinal() + 1) % 4 ;
        return  Direction.values()[newDirection];
    }

    public Direction rotateLeft(Location location) {
        int newDirection = (location.getDirection().ordinal() + 3) % 4 ;
        return  Direction.values()[newDirection];
    }

    public boolean checkCollisionAndSetXY(Rover rover, MoveCommand command, List<Rover> roverList) {
        long x = nextX(rover.getLocation(),command);
        long y = nextY(rover.getLocation(),command);
        if (roverList != null) {
            for (Rover rover1 : roverList) {
                if (rover1.getLocation().getX() == x
                        && rover1.getLocation().getY() == y) {
                    return false;
                }
            }
        }
        rover.getLocation().setXY(x,y);
        return true;
    }

    public boolean setXY(Rover rover , MoveCommand command) {
        rover.getLocation().setXY(nextX(rover.getLocation(),command),
        nextY(rover.getLocation(),command));
        return true;
    }
    public boolean setRotate (Rover rover , MoveCommand command) {
        rover.getLocation().setDirection(rotate(rover.getLocation(),command));
        return true;
    }


}

