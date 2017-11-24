package com.italo.troopersapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.italo.troopersapp.model.Affiliation;
import com.italo.troopersapp.model.Trooper;
import com.italo.troopersapp.repository.TrooperRepository;
import com.italo.troopersapp.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private Spinner sAffiliation;
    private EditText etTrooperName;
    private Button bAddTrooper;
    private List<String> spinnerArray;
    private Trooper newTrooper;
    private ArrayList<Trooper> troopers;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        findComponents();

        populateSpinnerArray();

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sAffiliation.setAdapter(spinnerAdapter);

        bAddTrooper.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);
                troopers = TrooperRepository.tryGettingFromSharedPreferences(sharedPreferences);
                troopers.add(createNewTrooper());
                TrooperRepository.saveToSharedPreferences(troopers, sharedPreferences);
                toast = Toast.makeText(getApplicationContext(), "TROOPER ADICIONADO", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private Trooper createNewTrooper() {
        newTrooper = new Trooper();
        newTrooper.setId(troopers.size() + 1);
        newTrooper.setName(etTrooperName.getText().toString());
        newTrooper.setAffiliation(getTrooperAffiliation(sAffiliation.getSelectedItem().toString()));
        newTrooper.setDescription("Teste");
        newTrooper.setImageUrl("http://diariodonordeste.verdesmares.com.br/polopoly_fs/1.1444352.1448825056!/image/image.jpg_gen/derivatives/landscape_310/image.jpg");
        return newTrooper;
    }

    private Affiliation getTrooperAffiliation(String affiliation) {
        switch (affiliation){
            case "Galactic Republic":
                return Affiliation.GALACTIC_REPUBLIC;
            case "Galactic Empire":
                return Affiliation.GALACTIC_EMPIRE;
            case "First Order":
                return Affiliation.FIRST_ORDER;
            default:
                return null;
        }
    }

    private void populateSpinnerArray() {
        spinnerArray = new ArrayList<>();
        spinnerArray.add("Galactic Republic");
        spinnerArray.add("Galactic Empire");
        spinnerArray.add("First Order");
    }

    private void findComponents() {
        sAffiliation = findViewById(R.id.sAffiliation);
        etTrooperName = findViewById(R.id.etTrooperName);
        bAddTrooper = findViewById(R.id.bAddTrooper);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.voltar:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
