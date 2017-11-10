package com.example.nathan.checmicalcalculation;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String  PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

}
