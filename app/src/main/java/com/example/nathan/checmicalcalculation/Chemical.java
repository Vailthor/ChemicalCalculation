package com.example.nathan.checmicalcalculation;

/**
 * Created by Nathan on 10/30/2017.
 */

public class Chemical {
    private String name;
    private double rate;
    private String descrip;

    Chemical() {
        name = "null";
        rate = 0;
        descrip = "null";
    }

    Chemical(String name, double rate, String descrip) {
        this.name = name;
        this.rate = rate;
        this.descrip = descrip;
    }

    String getName() {
        return name;
    }

    double getRate() {
        return rate;
    }

    String getDescrip() {
        return descrip;
    }
}
