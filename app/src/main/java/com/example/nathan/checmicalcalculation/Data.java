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

public class Data {

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
        load();

        checkLists();
    }

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



    private void firstTime(int numChemicals, int numFields) {


        if (numChemicals == 0) {
            Log.i(TAG, "File read incorrectly \n");
            changesChem = true;
            chemicalList.clear();
            Chemical chem1 = new Chemical("Roundup", 2.4, "Weed & Grass Killer");
            Chemical chem2 = new Chemical("Rackmax", 2.2, "Plant Growth Hormone");
            chemicalList.add(chem1);
            chemicalList.add(chem2);
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
}
