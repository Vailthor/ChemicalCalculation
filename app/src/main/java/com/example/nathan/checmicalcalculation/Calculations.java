package com.example.nathan.checmicalcalculation;

/**
 * Created by Dylan Pratt on 11/5/2017.
 */
// This class will house the math and logic we need the app to preform
public class Calculations {


    double acresAppliedPerTank(double tanksize, double liquidAppliedPerAcre) {
        double acresAppliedPerTank = tanksize/liquidAppliedPerAcre;
        return acresAppliedPerTank;
        //Tank Size/ Liquid applied per acre = acres applied per tank

        //TankZize is measured in Gallons

        //This Method is assuming the Liquid applied per acre is measure in Gallons
        //We will need to check and make sure this is always the case.

    }
    //acres applied per tank
    double TotalGallonsPerTank(double acresApplied, double ChemRatePerAcre) {
        //Right now this method presumes that all the chemical's per acre is measured in acres
        //We will have to make sure that is the case. If not they we will have to rewrite this.
        //Maybe save the per Acre measurement in the chemical object????

        double TotalOuncesPerTank =acresApplied * ChemRatePerAcre;

        double TotalGallonsPerTank = ouncesToGallons(TotalOuncesPerTank);

        return TotalGallonsPerTank;

        //acres applied * chemical rate per acre = total chemicals per tank
    }

    double ouncesToGallons(double ounces) {//converts ounces to gallons
        double gallons = ounces/128;
        return gallons;
    }

    double gallonsToOunces(double gallons) {//converts gallons to  Ounces
        double ounces = gallons/128;
        return ounces;
    }
}
