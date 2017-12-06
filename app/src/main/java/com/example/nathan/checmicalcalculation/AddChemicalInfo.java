package com.example.nathan.checmicalcalculation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddChemicalInfo extends AppCompatActivity {

    private Data data;
    int spinnerPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chemical_info);

        data = Data.getInstance(getApplication());

        Spinner spinner = (Spinner) findViewById(R.id.chemSpin);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (position != 0) {
                    Chemical currentChemical = data.chemicalList.get(position);
                    ((EditText)findViewById(R.id.chemName)).setText(currentChemical.getName());
                    ((EditText)findViewById(R.id.epaNum)).setText(currentChemical.getEPA());
                    ((EditText)findViewById(R.id.chemClass)).setText(currentChemical.getChemClass());
                    ((EditText)findViewById(R.id.rateLow)).setText(Double.toString(currentChemical.getLowRate()));
                    ((EditText)findViewById(R.id.rateHigh)).setText(Double.toString(currentChemical.getHighRate()));
                    ((EditText)findViewById(R.id.rainfast)).setText(Integer.toString(currentChemical.getRainfast()));
                    ((EditText)findViewById(R.id.phi)).setText(Integer.toString(currentChemical.getPHI()));
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
    void save() {
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
            data.changeChemicalInfo(spinnerPosition,chemName, rateLow, rateHigh, epaNum, chemClass, rainfast, phi);

        }
    }

}
