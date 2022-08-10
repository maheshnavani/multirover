package com.test.rover;

public class RoverCommand {
    private final String label;
    private final String command;

    public RoverCommand(String label, String command) {
        this.label = label;
        this.command = command;
    }

    public String getLabel() {
        return label;
    }

    public String getCommand() {
        return command;
    }
}
