package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class detailActivity extends AppCompatActivity {
    private int positionCountry;
    TextView tvCountry,tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        tvCountry = findViewById(R.id.tvCountry);
        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTotalDeaths = findViewById(R.id.tvDeaths);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);

        tvCountry.setText(countries.countryModelsList.get(positionCountry).getCountry());
        tvCases.setText(countries.countryModelsList.get(positionCountry).getCases());
        tvRecovered.setText(countries.countryModelsList.get(positionCountry).getRecovered());
        tvCritical.setText(countries.countryModelsList.get(positionCountry).getCritical());
        tvActive.setText(countries.countryModelsList.get(positionCountry).getActivecase());
        tvTodayCases.setText(countries.countryModelsList.get(positionCountry).getTodaycases());
        tvTotalDeaths.setText(countries.countryModelsList.get(positionCountry).getDeaths());
        tvTodayDeaths.setText(countries.countryModelsList.get(positionCountry).getTodaydeaths());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
