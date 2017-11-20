package com.example.nathan.checmicalcalculation;

import android.app.Application;
import android.content.Context;
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
 * Created by Nathan on 11/19/2017.
 */

public class Data {

    public static final String CHEM_FILE = "chemicals.txt";
    private static final String TAG  = "MainActivity";

    private static Data instance;
    List<Chemical> chemicalList;
    List<Field> fieldList;
    List<Tank> tankList;
    boolean changes;

    Application app;

    public static Data getInstance(Application application) {
        if (instance == null)
            instance = new Data(application);
        return instance;
    }



    private Data(Application application) {
        app = application;
        chemicalList = new ArrayList<>();
        changes = false;
        firstTime(load());
        checkList();
    }

    private int load() {
        Gson gson = new Gson();
        int count = 0;
        try {
            FileReader fileReader = new FileReader(app.getFilesDir() + "/" + CHEM_FILE);
            BufferedReader in = new BufferedReader(fileReader);

            String temp;
            Chemical chem;
            while((temp = in.readLine())!= null) {
                chem = gson.fromJson(temp, Chemical.class);
                chemicalList.add(chem);
                count++;
                Log.i(TAG, chem.getName() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void save() {

        if (changes) {
            Gson gson = new Gson();
            try {
                app.deleteFile(CHEM_FILE);
                Log.i(TAG, "PROBLEM");
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
    }



    void firstTime(int numChemicals) {


        if (numChemicals < 1) {
            Log.i(TAG, "File read incorrectly \n");
            changes = true;
            chemicalList.clear();
            Chemical chem1 = new Chemical("Roundup", 2.4, "Weed & Grass Killer");
            Chemical chem2 = new Chemical("Rackmax", 2.2, "Plant Growth Hormone");
            chemicalList.add(chem1);
            chemicalList.add(chem2);
        }
    }

    void checkList() {
        String chemicals = "Chemicals: ";
        for (Chemical c : chemicalList)
            chemicals += c.getName() + " ";
        Log.i(TAG, chemicals + "\n");
    }
}
