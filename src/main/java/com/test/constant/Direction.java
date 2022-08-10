package com.test.constant;

public enum Direction {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W"),
    INVALID("INVALID");

    private String direction;
    Direction(String  direction) {
        this.direction = direction;
    }
    public String getDirection() {
        return this.direction;
    }

    public static Direction getDirection(String str) {
        if ( "n".equals(str.toLowerCase()) ) return NORTH;
        else if ( "s".equals(str.toLowerCase())) return SOUTH;
        else if ( "e".equals(str.toLowerCase())) return EAST;
        else if ( "w".equals(str.toLowerCase())) return WEST;
        else return INVALID;
    }


}
