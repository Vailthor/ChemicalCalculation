package com.example.nathan.checmicalcalculation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddChemicalInfo extends AppCompatActivity {

    private static final String TAG  = "AddChemicalActivity";

    private Data data;
    int spinnerPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chemical_info);

        Log.i(TAG, "Activity Starting\n");

        data = Data.getInstance(getApplication());
        setSpinner();

        Spinner spinner = (Spinner) findViewById(R.id.chemSpin);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (position != 0) {
                    Log.i(TAG, "Setting Edit Texts\n");
                    Chemical currentChemical = data.chemicalList.get(position - 1);
                    ((EditText)findViewById(R.id.chemName)).setText(currentChemical.getName());
                    ((EditText)findViewById(R.id.epaNum)).setText(currentChemical.getEPA());
                    ((EditText)findViewById(R.id.chemClass)).setText(Character.toString(currentChemical.getChemClass()));
                    ((EditText)findViewById(R.id.rateLow)).setText(Double.toString(currentChemical.getLowRate()));
                    ((EditText)findViewById(R.id.rateHigh)).setText(Double.toString(currentChemical.getHighRate()));
                    ((EditText)findViewById(R.id.rainfast)).setText(Integer.toString(currentChemical.getRainfast()));
                    ((EditText)findViewById(R.id.phi)).setText(Integer.toString(currentChemical.getPHI()));
                }
                else {
                    ((EditText)findViewById(R.id.chemName)).setText("");
                    ((EditText)findViewById(R.id.epaNum)).setText("");
                    ((EditText)findViewById(R.id.chemClass)).setText("");
                    ((EditText)findViewById(R.id.rateLow)).setText("");
                    ((EditText)findViewById(R.id.rateHigh)).setText("");
                    ((EditText)findViewById(R.id.rainfast)).setText("");
                    ((EditText)findViewById(R.id.phi)).setText("");
                }
                spinnerPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    private void setSpinner() {
        ArrayList<String> chemStrings = new ArrayList<>();
        chemStrings.add("New Chemical");


        for (Chemical c : data.chemicalList) {
            chemStrings.add(c.getName());
        }

        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, chemStrings);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.chemSpin);
        spinner.setAdapter(spinAdapter);

    }
    void save(View view) {
        String chemName = ((EditText)findViewById(R.id.chemName)).getText().toString();
        String epaNum = ((EditText)findViewById(R.id.epaNum)).getText().toString();
        char chemClass = ((EditText)findViewById(R.id.chemClass)).getText().toString().charAt(0);
        double rateLow = Double.parseDouble(((EditText)findViewById(R.id.rateLow)).getText().toString());
        double rateHigh = Double.parseDouble(((EditText)findViewById(R.id.rateHigh)).getText().toString());
        int rainfast = Integer.parseInt(((EditText)findViewById(R.id.rainfast)).getText().toString());
        int phi = Integer.parseInt(((EditText)findViewById(R.id.phi)).getText().toString());
        if (spinnerPosition == 0) {
            Chemical newChem = new Chemical(chemName, rateLow, rateHigh, epaNum, chemClass, rainfast, phi);
            data.addChemical(newChem);
        }
        else {
            data.changeChemicalInfo(spinnerPosition - 1,chemName, rateLow, rateHigh, epaNum, chemClass, rainfast, phi);

        }
        setSpinner();
    }

}
