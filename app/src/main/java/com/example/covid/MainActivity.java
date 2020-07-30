package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tvCases,tvRecover,tvCritical, tvActive,tvTodaycase,tvTotaldeath,tvTodaydeath, tvAffectcountry;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCases = findViewById(R.id.tv_cases);
        tvRecover = findViewById(R.id.tv_recovered);
        tvCritical = findViewById(R.id.tv_critical);
        tvActive = findViewById(R.id.tv_active);
        tvTodaycase = findViewById(R.id.tv_Todaycases);
        tvTotaldeath = findViewById(R.id.tv_totalDeath);
        tvTodaydeath = findViewById(R.id.tv_todayDeaths);
        tvAffectcountry = findViewById(R.id.tv_affectcountry);

        simpleArcLoader = findViewById(R.id.loader);
        scrollView = findViewById(R.id.scroll_state);
        pieChart = findViewById(R.id.piechart);
        
        fetchdata();

    }

    private void fetchdata() {
        String url = "https://corona.lmao.ninja/v2/all/";
        simpleArcLoader.start();
        StringRequest request = new StringRequest(Request.Method.GET,url,
        new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    tvCases.setText(jsonObject.getString("cases"));
                    tvActive.setText(jsonObject.getString("active"));
                    tvAffectcountry.setText(jsonObject.getString("affectedCountries"));
                    tvCritical.setText(jsonObject.getString("critical"));
                    tvRecover.setText(jsonObject.getString("recovered"));
                    tvTodaycase.setText(jsonObject.getString("todayCases"));
                    tvTodaydeath.setText(jsonObject.getString("todayDeaths"));
                    tvTotaldeath.setText(jsonObject.getString("deaths"));

                    pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#FFA726")));
                    pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tvRecover.getText().toString()), Color.parseColor("#66BB6A")));
                    pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvTotaldeath.getText().toString()), Color.parseColor("#EF5350")));
                    pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#29B6F6")));
                    pieChart.startAnimation();

                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);

                } catch (JSONException e) {
                    e.printStackTrace();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                }


            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void goTrackCountry(View view) {
        startActivity(new Intent(getApplicationContext(),countries.class));
    }
}
