package com.example.nathan.checmicalcalculation;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public static final String  PREFS_NAME = "MyPrefsFile";
    private static final String TAG  = "MainActivity";

    Data data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        data = Data.getInstance(getApplication());
        populateSpinner();
        //Tom: Changing the comments to see if I fixed the problem on my computer.
    }

    //Populates the spinner with chemical name data
    private void populateSpinner() {
        ArrayList<String> chemStrings = new ArrayList<>();
        for (Chemical c : data.chemicalList) {
            chemStrings.add(c.getName());
        }

        Spinner spinner = new Spinner(this);
        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, chemStrings);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner) findViewById(R.id.chemSpin);
        spinner.setAdapter(spinAdapter);
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
        data.save();
    }

    void addValues(View view) {
        Intent intent = new Intent();
        startActivity(intent);
    }



    void calculateTank(View view)
    {
        double acres = Double.parseDouble(((EditText)findViewById(R.id.acres)).getText().toString());
        double tankSize = Double.parseDouble(((EditText)findViewById(R.id.tankSize)).getText().toString());
        double galPerAcre = Double.parseDouble(((EditText)findViewById(R.id.galPerAcre)).getText().toString());
        Log.i(TAG, "tS-" + tankSize + " gpa-" + galPerAcre);
        double acresPerTank = Calculations.acresAppliedPerTank(tankSize, galPerAcre);
        double chemPerTank = Calculations.TotalGallonsPerTank(acresPerTank, data.chemicalList.get(0).getLowRate());

        ((EditText)findViewById(R.id.galPerTank)).setText(Double.toString(chemPerTank));
    }



}
