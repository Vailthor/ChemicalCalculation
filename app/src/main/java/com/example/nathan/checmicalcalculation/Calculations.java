package com.example.nathan.checmicalcalculation;

/**
 * Created by Dylan Pratt on 11/5/2017.
 */
// This class will house the math and logic we need the app to preform
public class Calculations {


    double acresAppliedPerTank(int tanksize, int liquidAppliedPerAcre) {
        double acresApplied = tanksize/liquidAppliedPerAcre;
        return acresApplied;
        //Tank Size/ Liquid applied per acre = acres applied per tank

    }
    //acres applied per tank
    double TotalGallonsPerTank(double acresApplied, double ChemRatePerAcre) {
        double TotalGallonsPerTank =acresApplied * ChemRatePerAcre;
        return TotalGallonsPerTank;

        //acres applied * chemical rate per acre = total chemicals per tank
    }

    double ouncesToGallons(double ounces) {//converts ounces to gallons
        double gallons = ounces/128;
        return gallons;
    }

    double gallonsToOunces(double gallons) {//converts ounces to gallons
        double ounces = gallons/128;
        return ounces;
    }
}
