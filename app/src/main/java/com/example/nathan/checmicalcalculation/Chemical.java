package com.example.nathan.checmicalcalculation;

/**
 * Created by Nathan on 10/30/2017.
 */

public class Chemical {
    private String name;
    private double rateLow;
    private double rateHigh;
    private String EPA;
    private char chemClass;
    //hrs
    private double rainfast;
    //days
    private double PHI;

    Chemical() {
        name = "null";
        rateLow = 0;
        rateHigh = 0;
        EPA = "null";
        chemClass = '0';
        rainfast = 0;
        PHI = 0;
    }

    Chemical(String name, double rateLow, double rateHigh, String EPA, char chemClass, double rainfast, double PHI) {
        this.name = name;
        this.rateLow = rateLow;
        this.rateHigh = rateHigh;
        this.EPA = EPA;
        this.chemClass = chemClass;
        this.rainfast = rainfast;
        this.PHI = PHI;
    }

    String getName() {
        return name;
    }
    double getLowRate() {
        return rateLow;
    }
    double getHighRate() {
        return rateHigh;
    }
    String getEPA() {
        return EPA;
    }
    char getChemClass() {return chemClass;}
    double getRainfast() {return rainfast;}
    double getPHI() {return PHI;}

    void setName(String name) {
        this.name = name;
    }
    void setLowRate(double rateLow) {
        this.rateLow = rateLow;
    }
    void setHighRate(double rateHigh) {
        this.rateHigh = rateHigh;
    }
    void setEPA(String EPA) {
        this.EPA = EPA;
    }
    void setChemClass(char chemClass) {
        this.chemClass = chemClass;
    }
    void setRainfast(double rainfast) {
        this.rainfast = rainfast;
    }
    void setPHI(double PHI) {
        this.PHI = PHI;
    }
}
