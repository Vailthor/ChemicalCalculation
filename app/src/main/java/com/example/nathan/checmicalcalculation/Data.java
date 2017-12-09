package com.example.nathan.checmicalcalculation;

import android.app.Application;
import android.util.Log;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Nathan
 */
class Data {

    private static final String CHEM_FILE = "chemicals.txt";
    private static final String FIELD_FILE = "fields.txt";
    private static final String TAG  = "MainActivity";

    private static Data instance;
    List<Chemical> chemicalList;
    List<Field> fieldList;
    List<Tank> tankList;
    boolean changesChem;
    boolean changesField;

    Application app;

    /**
     * Returns the instance of the data object, which contains the lists for chemicals, fields, and tanks
     * @param application Used to find the current file directory
     * @return instance of the singleton object
     */
    public static synchronized Data getInstance(Application application) {
        if (instance == null)
            instance = new Data(application);
        return instance;
    }



    private Data(Application application) {
        app = application;
        chemicalList = new ArrayList<>();
        fieldList = new ArrayList<>();
        changesChem = false;
        changesField = false;

        deleteFiles();

        load();
        checkLists();
    }

    /**
     * Reads information from files into there appropriate list
     */
    private void load() {
        Gson gson = new Gson();
        int countChem = 0;
        int countField = 0;

        //Read chemicals from file into chemicalList
        try {
            FileReader fileReader = new FileReader(app.getFilesDir() + "/" + CHEM_FILE);
            BufferedReader in = new BufferedReader(fileReader);

            String temp;
            Chemical chem;
            Log.i(TAG, "Reading Chemicals\n");
            while((temp = in.readLine())!= null) {
                chem = gson.fromJson(temp, Chemical.class);
                chemicalList.add(chem);
                countChem++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Read fields from file into fieldList
        try {
            File file = new File(app.getFilesDir() + "/" + FIELD_FILE);
            if (file.exists()) {
                FileReader fileReader = new FileReader(app.getFilesDir() + "/" + FIELD_FILE);
                BufferedReader in = new BufferedReader(fileReader);

                String temp;
                Field field;
                Log.i(TAG, "Reading Fields\n");
                while ((temp = in.readLine()) != null) {
                    field = gson.fromJson(temp, Field.class);
                    fieldList.add(field);
                    countField++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        firstTime(countChem, countField);
    }

    /**
     * Takes information from the lists and saves it into the files if there has been changes
     */
    public void save() {

        Gson gson = new Gson();
        if (changesChem) {
            try {
                app.deleteFile(CHEM_FILE);
                File file = new File(app.getFilesDir() + "/" + CHEM_FILE);
                PrintWriter out;
                out = new PrintWriter(file);
                for (Chemical c : chemicalList) {
                    String userJson = gson.toJson(c);
                    Log.i(TAG, userJson + "\n");
                    out.println(userJson);
                }
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (changesField) {
            try {
                app.deleteFile(FIELD_FILE);
                File file = new File(app.getFilesDir() + "/" + FIELD_FILE);
                PrintWriter out;
                out = new PrintWriter(file);
                for (Field f : fieldList) {
                    String userJson = gson.toJson(f);
                    Log.i(TAG, userJson + "\n");
                    out.println(userJson);
                }
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * If there was no file for a list then the default values are set for that list
     * @param numChemicals How many chemicals were read from the file
     * @param numFields How many fields were read from the file
     */
    private void firstTime(int numChemicals, int numFields) {

        if (numChemicals == 0) {
            Log.i(TAG, "Adding Chemical Defaults\n");
            changesChem = true;
            chemicalList.add(new Chemical("QUILT XCEL FUNGICIDE [2.5G]", 10.5, 14, "100-1324",'f',1,7));
            chemicalList.add(new Chemical("AGRI STAR 2,4-D AMINE 4 [2.5G]", 40, 80, "42750-19",'h',4,7));
            chemicalList.add(new Chemical("AGRI STAR MCPA ESTER 4 [2.5G]", 10, 20, "42750-23",'h',4,0));
            chemicalList.add(new Chemical("DUPONT COMBO PACK 8 [BZ]", -1, -1, "-1",'h',-1,-1));
            chemicalList.add(new Chemical("ROUNDUP POWER MAX [265BG]", 20, 44, "524-549",'h',1,14));
            chemicalList.add(new Chemical("Poast", 30, 40, "7969-58",'h',1,14));
            chemicalList.add(new Chemical("SENTRALLAS [2.5G]", -1, -1, "-1",'h',-1,-1));
            chemicalList.add(new Chemical("STARANE FLEX [2.5G]", -1, -1, "-1",'h',-1,-1));
            chemicalList.add(new Chemical("WEEDMASTER [2.5G]", -1, -1, "-1",'h',-1,-1));
            chemicalList.add(new Chemical("AD-MAX 90, PHT [2.5G]", -1, -1, "-1",'s',-1,-1));
            chemicalList.add(new Chemical("DELETE-IT, PHT [1G]", -1, -1, "-1",'s',-1,-1));
            chemicalList.add(new Chemical("DRIFT-FIANT [2.5G]", -1, -1, "-1",'s',-1,-1));
            chemicalList.add(new Chemical("LOAD UP, PHT [2.5G]", -1, -1, "-1",'s',-1,-1));
            chemicalList.add(new Chemical("WETCIT [2.5G]", -1, -1, "-1",'s',-1,-1));





        }

        //default field values
        if (numFields == 0) {
            changesField = true;
            fieldList.add(new Field("Pep 7", "Potatoes", 275, 1, 400.00));
            fieldList.add(new Field("Pep 8", "Winter Wheat", 205, 125, 4.25));
            fieldList.add(new Field("Pep 9", "Potatoes", 71.9, 1, 400.00));
            fieldList.add(new Field("Pep 10", "Winter Wheat", 136.14, 125, 4.25));
            fieldList.add(new Field("Pep 11", "Winter Wheat", 128, 125, 4.25));
            fieldList.add(new Field("Pep 12", "Winter Wheat", 134.26, 125, 4.25));
            fieldList.add(new Field("Pep 13", "Winter Wheat", 120, 125, 4.25));
            fieldList.add(new Field("Pep 14", "Winter Wheat", 125, 125, 4.25));
            fieldList.add(new Field("Pep 15", "Winter Wheat", 134.15, 125, 4.25));
            fieldList.add(new Field("Pep 16", "Potatoes", 125, 1, 400.00));
            fieldList.add(new Field("Pep 17", "Potatoes", 129.86, 1, 400.00));
            fieldList.add(new Field("Pep 18", "Potatoes", 128.62, 1, 400.00));
            fieldList.add(new Field("Pep 19", "Timothy", 125, 6, 149.80));
            fieldList.add(new Field("Pep 20", "Timothy", 124.83, 6, 149.80));
            fieldList.add(new Field("Pep 21", "Timothy", 123.1, 6, 149.80));
            fieldList.add(new Field("Pep 1440", "Winter Wheat", 40.8, 125, 4.25));
            fieldList.add(new Field("Pep 40", "Winter Wheat", 124.28, 125, 4.25));
            fieldList.add(new Field("Funk 4", "Alfalfa", 125, 6, 140.10));
            fieldList.add(new Field("Funk 5", "Alfalfa", 157.6, 6, 140.10));
            fieldList.add(new Field("Funk 6", "Alfalfa", 115, 6, 140.10));
            fieldList.add(new Field("Funk 7", "Alfalfa", 55.93, 6, 140.10));
            fieldList.add(new Field("Funk 8", "Alfalfa", 34.42, 6, 140.10));
            fieldList.add(new Field("Funk 9", "Alfalfa", 90, 6, 140.10));
            fieldList.add(new Field("Funk 10", "Alfalfa", 41.31, 6, 140.10));
            fieldList.add(new Field("Heaney 3", "Winter Wheat", 127, 125, 4.25));
            fieldList.add(new Field("Heaney 4", "Winter Wheat", 73, 125, 4.25));
        }

    }

    private void checkLists() {
        String chemicals = "Chemicals: ";
        for (Chemical c : chemicalList)
            chemicals += c.getName() + " ";
        Log.i(TAG, chemicals + "\n");

        String fields = "Fields: ";
        for (Field f : fieldList)
            fields += f.getName() + " ";
        Log.i(TAG, fields + "\n");
    }

    private void deleteFiles() {

        app.deleteFile(CHEM_FILE);
        app.deleteFile(FIELD_FILE);
    }

    /**
     * Will take a chemical and add it to the chemicalList
     * @param newChem Chemical to add
     */
    public void addChemical(Chemical newChem) {
        chemicalList.add(newChem);
        changesChem = true;
    }

    /**
     * Change the information of a Chemical already in the chemicalList
     * @param spot To Change
     * @param name To Change
     * @param rateLow To Change
     * @param rateHigh To Change
     * @param EPA To Change
     * @param chemClass To Change
     * @param rainfast To Change
     * @param PHI To Change
     */
    public void changeChemicalInfo(int spot, String name, double rateLow, double rateHigh, String EPA, char chemClass, double rainfast, double PHI) {
        Chemical toChange = chemicalList.get(spot);
        toChange.setName(name);
        toChange.setLowRate(rateLow);
        toChange.setHighRate(rateHigh);
        toChange.setEPA(EPA);
        toChange.setChemClass(chemClass);
        toChange.setRainfast(rainfast);
        toChange.setPHI(PHI);
        changesChem = true;
    }
}
