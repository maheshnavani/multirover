package com.test.rover;

import com.test.constant.Direction;
import lombok.Data;

@Data
public class Location {

    private long x;
    private long y;
    private Direction direction;

    public Location(long x, long y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    public void setXY(long x , long y) {
        this.x = x;
        this.y = y;
    }
}
