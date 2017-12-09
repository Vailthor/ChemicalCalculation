package com.example.nathan.checmicalcalculation;

/**
 * Created by Nathan on 10/30/2017.
 * Field class holds all the infomation relating to the Fields.
 * Currently this class is mostly unused. It was key for a strech challenge that would log what
 * Chemicals had been sprayed on what field.
 */

public class Field {
    private String name;
    private String crop;
    private double acres;
    private int yield;
    private double price;
    private Record log;

    /**
     * Default Constructor for field
     */
    Field() {
        name = "null";
        crop = "null";
        acres = 0;
        yield = 0;
        price = 0.00;
        log = null;
    }

    Field(String name, String crop, double acres, int yield, double price) {
        this.name = name;
        this.crop = crop;
        this.acres = acres;
        this.yield = yield;
        this.price = price;
        log = null;
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
