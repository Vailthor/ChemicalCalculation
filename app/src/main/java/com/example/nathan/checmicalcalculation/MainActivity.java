package com.example.nathan.checmicalcalculation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String  PREFS_NAME = "MyPrefsFile";
    public static final String CHEM_FILE = "chemicals.txt";
    private static final String TAG  = "MainActivity";
    List<Chemical> chemicalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        chemicalList = new ArrayList<>();
        int numChemicals = load();
        firstTime(numChemicals);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String acres = settings.getString("acres", "");
        String tankSize = settings.getString("tankSize", "");
        String gPA = settings.getString("gPA", "");
        String gPT = settings.getString("gPT", "");
        ((EditText)findViewById(R.id.acres)).setText(acres);
        ((EditText)findViewById(R.id.tankSize)).setText(tankSize);
        ((EditText)findViewById(R.id.galPerAcre)).setText(gPA);
        ((EditText)findViewById(R.id.galPerTank)).setText(gPT);
        checkList();
        //Change!!
        //Dylan's Change!
        //Super push Dylan
    }

    protected int load() {
        Gson gson = new Gson();
        int count = 0;
        try {
            FileReader fileReader = new FileReader(getFilesDir() + "/" + CHEM_FILE);
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

    protected void save() {
        Gson gson = new Gson();
        try {
            deleteFile(CHEM_FILE);
            File file = new File(getFilesDir(), CHEM_FILE);
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

    protected void onPause()
    {
        super.onPause();
        SharedPreferences.Editor edit = getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putString("acres", ((EditText)findViewById(R.id.acres)).getText().toString());
        edit.putString("tankSize", ((EditText)findViewById(R.id.tankSize)).getText().toString());
        edit.putString("gPA", ((EditText)findViewById(R.id.galPerAcre)).getText().toString());
        edit.putString("gPT", ((EditText)findViewById(R.id.galPerTank)).getText().toString());
        edit.apply();
    }

    protected void onStop() {
        super.onStop();
        save();
    }

    void addValues(View view) {
        Intent intent = new Intent();
        startActivity(intent);
    }

    void firstTime(int numChemicals) {


        if (numChemicals < 1) {
            Log.i(TAG, "File read incorrectly \n");
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
