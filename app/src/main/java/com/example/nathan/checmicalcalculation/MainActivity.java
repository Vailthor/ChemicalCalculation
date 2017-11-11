package com.example.nathan.checmicalcalculation;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String  PREFS_NAME = "MyPrefsFile";
    public static final String CHEM_FILE = "chemicals";
    List<Chemical> chemicalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        chemicalList = new ArrayList<>();
        load();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String acres = settings.getString("acres", "DEFAULT");
        String tankSize = settings.getString("tankSize", "DEFAULT");
        String gPA = settings.getString("gPA", "DEFAULT");
        String gPT = settings.getString("gPT", "DEFAULT");
        ((EditText)findViewById(R.id.acres)).setText(acres);
        ((EditText)findViewById(R.id.tankSize)).setText(tankSize);
        ((EditText)findViewById(R.id.galPerAcre)).setText(gPA);
        ((EditText)findViewById(R.id.galPerTank)).setText(gPT);
        //Change!!
        //Dylan's Change!
        //Super push Dylan
    }

    protected void load() {
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader(CHEM_FILE);
            BufferedReader in = new BufferedReader(fileReader);
            int count = 0;
            String temp;
            Chemical chem;
            while((temp = in.readLine())!= null) {
                chem = gson.fromJson(temp, Chemical.class);
                chemicalList.set(count, chem);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void save() {
        Gson gson = new Gson();
        try {
            PrintWriter out = null;
            out = new PrintWriter(CHEM_FILE);
            for (Chemical c : chemicalList) {
                String userJson = gson.toJson(c);
                out.println(userJson);
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
        edit.commit();
    }

    protected void onClose() {
        save();
    }

}
