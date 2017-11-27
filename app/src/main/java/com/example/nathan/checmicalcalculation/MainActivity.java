package com.example.nathan.checmicalcalculation;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


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
        //Change!!
        //Dylan's Change!
        //Super push Dylan
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
        Log.i(TAG, "crash5");
        double chemPerTank = Calculations.TotalGallonsPerTank(acresPerTank, data.chemicalList.get(0).getRate());
        Log.i(TAG, "crash6");

        ((EditText)findViewById(R.id.galPerTank)).setText(Double.toString(chemPerTank));
    }



}
