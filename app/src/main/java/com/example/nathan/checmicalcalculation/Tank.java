package com.example.nathan.checmicalcalculation;

/**
 * Created by Nathan on 10/30/2017.
 */

public class Tank {
    private double size;
    private String name;
    private Log log;

    Tank() {
        size = 0;
        name = "null";
        log = null;
    }

    Tank(double size, String name, Log log) {
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

    Log getLog() {
        return log;
    }
}
