package com.example.nathan.checmicalcalculation;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public static final String  PREFS_NAME = "MyPrefsFile";
    public static final String EXTRA_MESSAGE = "com.example.nathan.chemicalcalculation.MESSAGE";
    private static final String TAG  = "MainActivity";

    private Data data;
    int spinnerPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = Data.getInstance(getApplication());
        start();

        Spinner spinner = (Spinner) findViewById(R.id.chemSpin);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                double low = data.chemicalList.get(position).getLowRate();
                double high = data.chemicalList.get(position).getHighRate();
                ((EditText)findViewById(R.id.rate)).setText(low + " - " + high);
                spinnerPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        //Tom: Changing the comments to see if I fixed the problem on my computer.
    }

    //Populates the spinner with chemical name data
    private void start() {
        ArrayList<String> chemStrings = new ArrayList<>();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String acres = settings.getString("acres", "");
        String tankSize = settings.getString("tankSize", "");
        String gPA = settings.getString("gPA", "");
        String gPT = settings.getString("gPT", "");
        ((EditText)findViewById(R.id.acres)).setText(acres);
        ((EditText)findViewById(R.id.tankSize)).setText(tankSize);
        ((EditText)findViewById(R.id.galPerAcre)).setText(gPA);
        ((EditText)findViewById(R.id.galPerTank)).setText(gPT);

        for (Chemical c : data.chemicalList) {
            chemStrings.add(c.getName());
        }

        Spinner spinner = new Spinner(this);
        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, chemStrings);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner) findViewById(R.id.chemSpin);
        spinner.setAdapter(spinAdapter);

        EditText galPerTank = (EditText) findViewById(R.id.galPerTank);
        galPerTank.setEnabled(false);
        EditText rate = (EditText) findViewById(R.id.rate);
        rate.setEnabled(false);
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


    void calculateTank(View view) {
        double acres;
        double tankSize;
        double galPerAcre;
        try {
            acres = Double.parseDouble(((EditText) findViewById(R.id.acres)).getText().toString());
        } catch (NumberFormatException e) {
            acres = 0;
        }
        try {
            tankSize = Double.parseDouble(((EditText) findViewById(R.id.tankSize)).getText().toString());
        } catch (NumberFormatException e)
        {
            tankSize = 0;
        }
        try {
            galPerAcre = Double.parseDouble(((EditText) findViewById(R.id.galPerAcre)).getText().toString());
        } catch(NumberFormatException e)
        {
            galPerAcre = 1;
        }
        Log.i(TAG, "tS-" + tankSize + " gpa-" + galPerAcre);
        double acresPerTank = Calculations.acresAppliedPerTank(tankSize, acres);
        double chemPerTank = Calculations.TotalGallonsPerTank(acresPerTank, galPerAcre);
        double galPerTankNum = Calculations.truncate(((chemPerTank)));

        ((EditText) findViewById(R.id.galPerTank)).setText(Double.toString(galPerTankNum));
    }

    void openAddChem(View view) {
        Intent intent = new Intent(this, AddChemicalInfo.class);
        intent.putExtra(EXTRA_MESSAGE, "farming");
        Log.i(TAG, "Intent Created, about to start add chem application\n");
        startActivity(intent);
    }

}
