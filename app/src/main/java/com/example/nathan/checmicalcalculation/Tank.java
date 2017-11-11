package com.example.nathan.checmicalcalculation;

/**
 * Created by Nathan on 10/30/2017.
 */

public class Tank {
    private double size;
    private String name;
    private Record log;

    Tank() {
        size = 0;
        name = "null";
        log = null;
    }

    Tank(double size, String name, Record log) {
        this.size = size;
        this.name = name;
        this.log = log;
    }

    double getSize() {
        return size;
    }

    String getName() {
        return name;
    }

    Record getLog() {
        return log;
    }
}
