package com.test.constant;

public enum MoveCommand {
    FORWARD("f"),
    BACK("b"),
    ROTATE_RIGHT("r"),
    ROTATE_LEFT ("l"),
    INVALID("invalid");

    private String move;
    MoveCommand(String  move) {
        this.move = move;
    }
    public String getMove() {
        return this.move;
    }

    public static MoveCommand getCommand(String str) {
        if ( "f".equals(str.toLowerCase()) ) return FORWARD;
        else if ( "b".equals(str.toLowerCase())) return BACK;
        else if ( "r".equals(str.toLowerCase())) return ROTATE_RIGHT;
        else if ( "l".equals(str.toLowerCase())) return ROTATE_LEFT;
        else return INVALID;
    }
}
