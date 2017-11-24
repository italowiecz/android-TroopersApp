package com.italo.troopersapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.italo.troopersapp.model.Affiliation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Spinner sItems = findViewById(R.id.planets_spinner);

        List<Affiliation> spinnerArray = new ArrayList<>();
        spinnerArray.add(Affiliation.GALACTIC_REPUBLIC);
        spinnerArray.add(Affiliation.GALACTIC_EMPIRE);
        spinnerArray.add(Affiliation.FIRST_ORDER);

        ArrayAdapter<Affiliation> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sItems.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        adapterView.getItemAtPosition(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
