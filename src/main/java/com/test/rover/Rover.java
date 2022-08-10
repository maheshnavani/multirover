package com.test.rover;

import com.test.constant.Direction;
import lombok.Data;

@Data
public class Rover {
    private String label;
    Location location;

    public Rover(String label, long x, long y , Direction direction) {
        location = new Location(x,y,direction);
        this.label = label;
    }
}
