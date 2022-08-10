package com.test;


import com.test.service.MarsRover;


public class MarsRoverApp {

    public static void main(String[] args) {
        MarsRover marsRover = new MarsRover();
        marsRover.initRovers();
        marsRover.start();
    }
}
