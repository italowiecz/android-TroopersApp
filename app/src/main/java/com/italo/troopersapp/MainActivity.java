package com.italo.troopersapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.italo.troopersapp.adapter.TrooperAdapter;
import com.italo.troopersapp.model.Trooper;
import com.italo.troopersapp.repository.TrooperRepository;
import com.italo.troopersapp.util.Constants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TrooperAdapter.OnItemClickListener, View.OnLongClickListener {

    private RecyclerView rvTroopers;

    private ArrayList<Trooper> troopers;

    private TrooperAdapter trooperAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvTroopers = findViewById(R.id.troopers_rv);
        populateRecyclerView();
    }

    private void populateRecyclerView() {
        troopers = TrooperRepository.tryGettingFromSharedPreferences(getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE));
        rvTroopers.setLayoutManager(new LinearLayoutManager(this));
        trooperAdapter = new TrooperAdapter(troopers, this, this);
        rvTroopers.setAdapter(trooperAdapter);
    }

    @Override
    public void onItemClick(Trooper trooper) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Constants.TROOPER_EXTRA, trooper);
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(final View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Excluir Trooper");
        alertDialogBuilder
                .setMessage("Tem certeza que deseja excluir este trooper?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int position = rvTroopers.getChildLayoutPosition(view);
                        troopers.remove(position);
                        trooperAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Trooper excluído!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).show();

        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        TrooperRepository.saveToSharedPreferences(troopers, getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.plus_itemm:
                Toast.makeText(this, "ADICIONAR TROOPER", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, AddActivity.class);
                intent.putExtra(Constants.TROOPER_LIST, troopers);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
