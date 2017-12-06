package com.example.nathan.checmicalcalculation;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Dylan Pratt on 11/5/2017.
 */
// This class will house the math and logic we need the app to preform
public class Calculations {

    /**
     * acresAppliedPerTank takes the tanksize and the liquidAppliedPerAcre to figue out the acresAppied per tank
     */
    static double acresAppliedPerTank(double tanksize, double liquidAppliedPerAcre) {
        double acresAppliedPerTank = tanksize/liquidAppliedPerAcre;
        return acresAppliedPerTank;
        //Tank Size/ Liquid applied per acre = acres applied per tank

        //TankZize is measured in Gallons

        //This Method is assuming the Liquid applied per acre is measure in Gallons
        //We will need to check and make sure this is always the case.

    }
    //acres applied per tank
    static double TotalGallonsPerTank(double acresApplied, double ChemRatePerAcre) {

        double TotalOuncesPerTank =acresApplied * ChemRatePerAcre;

        double TotalGallonsPerTank = ouncesToGallons(TotalOuncesPerTank);

        return TotalGallonsPerTank;

        //acres applied * chemical rate per acre = total chemicals per tank
    }
    /**
     * converts ounces to gallons
     */
    static double ouncesToGallons(double ounces) {//converts ounces to gallons
        double gallons = ounces/128;
        return gallons;
    }
    /**
     * Converts gallons to ounces
     */
    static double gallonsToOunces(double gallons) {//converts gallons to  Ounces
        double ounces = gallons/128;
        return ounces;
    }
    /**
     * ???????????????????????????
     */
    static double truncate(double num) {
        Double toBeTruncated = num;
        Double truncated = BigDecimal.valueOf(toBeTruncated)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
        return truncated;
    }
}
