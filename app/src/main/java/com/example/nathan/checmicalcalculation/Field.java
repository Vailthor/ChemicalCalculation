package com.example.nathan.checmicalcalculation;

/**
 * Created by Nathan on 10/30/2017.
 */

public class Field {
    private double acres;
    private int yield;
    private String name;
    private String crop;
    private double price;
    private Record log;

    Field() {
        acres = 0;
        name = "null";
        log = null;
    }

    Field(String name, String crop, double acres, int yield, double price) {
        this.acres = acres;
        this.name = name;
        this.crop = crop;
        this.price = price;
        this.yield = yield;
    }

    double getAcres() {
        return acres;
    }

    String getName() {
        return name;
    }

    Record getLog() {
        return log;
    }
}
