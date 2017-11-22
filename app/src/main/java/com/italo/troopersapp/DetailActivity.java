package com.italo.troopersapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.italo.troopersapp.model.Affiliation;
import com.italo.troopersapp.model.Trooper;
import com.italo.troopersapp.util.Constants;
import com.italo.troopersapp.util.ResourceUtil;
import com.squareup.picasso.Picasso;

/**
 * Created by italo on 18/11/2017.
 */

public class DetailActivity extends AppCompatActivity {

    private TextView tvTrooperDescription;
    private ImageView imvTrooperImage;
    private ImageView imvTrooperAffiliation;
    private Trooper trooper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        componentsInitialize();

        trooperBind();
    }

    private void componentsInitialize() {
        tvTrooperDescription = findViewById(R.id.trooper_text);
        imvTrooperImage = findViewById(R.id.trooper_image);
        imvTrooperAffiliation = findViewById(R.id.trooper_affiliation);
}

    private void trooperBind() {
        trooper = (Trooper) getIntent().getSerializableExtra(Constants.TROOPER_EXTRA);
        tvTrooperDescription.setText(trooper.getDescription());
        imvTrooperAffiliation.setImageResource(ResourceUtil.getResourceBasedOnAffiliation(trooper.getAffiliation()));
        setTitle(trooper.getName());
        Picasso.with(this).load(trooper.getImageUrl()).into(imvTrooperImage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.trooper_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.star_item:
                Toast.makeText(this, "FAVORITAR TROOPER", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
