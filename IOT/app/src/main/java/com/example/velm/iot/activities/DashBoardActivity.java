package com.example.velm.iot.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.velm.iot.R;
import com.example.velm.iot.api.ApiClient;
import com.example.velm.iot.api.AppApi;
import com.example.velm.iot.model.Favorites;
import com.example.velm.iot.model.LightHistory;
import com.example.velm.iot.model.WaterLevelHistory;
import com.example.velm.iot.utils.SharedPreference;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by velmmuru on 8/9/2017.
 */

public class DashBoardActivity extends AppCompatActivity {

    private Context context;
    private Call<List<LightHistory>> call;

    private ArrayList<com.github.mikephil.charting.data.BarEntry> BarEntrys ;
    private ArrayList<String> BarEntryLabels ;
    private BarDataSet Bardataset ;
    private BarData BARDATA ;

    private LinearLayout parent;

   // LineChart lineChart;
   private ArrayList<Entry> lineEntry ;
    private ArrayList<String> lineEntryLabels ;
    private LineDataSet lineDataset;

    private List<WaterLevelHistory> waterLevelsLevelList;
    private List<LightHistory> lightHistoryList;

    private List<Favorites> favorites;
    private List<Favorites> favorites1;

    private final String TAG = "DashBoardActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        context = this;
        initView();
    }

    private void initView(){

        parent = (LinearLayout)findViewById(R.id.liearLayout);

        favorites = new ArrayList<>();
        favorites1 = new ArrayList<>();

        favorites = new SharedPreference().getFavorites(context);

        if(favorites != null){
            loadDashboardData();
        }

    }

    private void loadDashboardData(){

        parent.removeAllViews();
        Log.d("DASHBOARD",favorites.toString());
        waterLevelsLevelList = new ArrayList<>();

        lightHistoryList = new ArrayList<>();
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(DashBoardActivity.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setTitle("ProgressDialog bar example");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
        progressDoalog.show();
        for (int i =0;i < favorites.size();i++){
            final Favorites favorite = favorites.get(i);


            LinearLayout linearLayout1 = new LinearLayout(context);
            FrameLayout.LayoutParams ladderParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout1.setLayoutParams(ladderParams);

            TextView textView = new TextView(context);
            textView.setText(favorite.getNodeName());

            Button button = new Button(context);
            button.setText("REMOVE");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    favorites.remove(favorite);
                    new SharedPreference().saveFavorites(context,favorites);
                    loadDashboardData();
                }
            });

            linearLayout1.addView(textView);
            linearLayout1.addView(button);

            linearLayout1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    Log.d(TAG,"LONG CLICK");
                    return false;
                }
            });

            parent.addView(linearLayout1);

            BarChart chart = null;
            LineChart lineChart = null;
            if(favorite.getGraphType().equals("BarChart")){
                chart = new BarChart(getApplicationContext());
                chart.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 450));
                parent.addView(chart);
            } else {
                lineChart = new LineChart(getApplicationContext());
                lineChart.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 450));
                parent.addView(lineChart);
            }

            if(favorite.getControllerName().equals("waterLevel")){
                waterLevelService(favorite,chart,lineChart);
            } else {
                lightHistory(favorite,chart,lineChart);
            }

        }

        progressDoalog.dismiss();
    }



    private void waterLevelService(final Favorites favorites, final BarChart chart,final LineChart lineChart){
        AppApi apiService = ApiClient.getClient().create(AppApi.class);

        Call<List<WaterLevelHistory>> call = apiService.getWaterLevel(favorites.getControllerName(),favorites.getNodeNumber());


        call.enqueue(new Callback<List<WaterLevelHistory>>() {
            @Override
            public void onResponse(Call<List<WaterLevelHistory>> call, Response<List<WaterLevelHistory>> response) {
                Log.d("success call","");
                waterLevelsLevelList = (List<WaterLevelHistory>) response.body();

                if(favorites.getGraphType().equals("BarChart")){
                    startBarChart(chart);
                } else {
                    startLineChart(lineChart);
                }
            }

            @Override
            public void onFailure(Call<List<WaterLevelHistory>> call, Throwable t) {
                Log.d("failed call = ",t.fillInStackTrace().toString());
            }
        });
    }

    private void lightHistory(final Favorites favorites, final BarChart chart, final LineChart lineChart){
        AppApi apiService = ApiClient.getClient().create(AppApi.class);

        call = apiService.getLightHistory(favorites.getControllerName(),favorites.getNodeNumber());

        /*Callback<List<LightHistory>> name = new Callback<List<LightHistory>>() {
            @Override
            public void onResponse(Call<List<LightHistory>> call, Response<List<LightHistory>> response) {

            }

            @Override
            public void onFailure(Call<List<LightHistory>> call, Throwable t) {

            }
        };

        call.enqueue(name);*/


        call.enqueue(new Callback<List<LightHistory>>() {
            @Override
            public void onResponse(Call<List<LightHistory>> call, Response<List<LightHistory>> response) {
                Log.d("success call","");
                lightHistoryList = (List<LightHistory>) response.body();
                Log.d("waterLevelsLevelList = ",lightHistoryList.toString());
                if(favorites.getGraphType().equals("BarChart")){
                    lightHistoryBarChart(chart);
                } else {
                    lightHistoryLineChart(lineChart);
                }

            }

            @Override
            public void onFailure(Call<List<LightHistory>> call, Throwable t) {

            }
        });

    }

    private void lightHistoryLineChart(LineChart lineChart){
        lineEntry = new ArrayList<>();
        lineEntryLabels = new ArrayList<>();

        for (int i = 0; i< lightHistoryList.size();i++){
            LightHistory lightHistory = lightHistoryList.get(i);

            if(lightHistory.getValue().equals("true")){
                lineEntry.add(new Entry(1, i));
            } else {
                lineEntry.add(new Entry(01, i));
            }
            lineEntryLabels.add(lightHistory.getLastDate());
        }

        lineDataset = new LineDataSet(lineEntry, "Water level");

        LineData data = new LineData(lineEntryLabels, lineDataset);
        lineDataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        lineDataset.setDrawCubic(true);
        lineDataset.setDrawFilled(true);
        lineChart.setData(data);
        lineChart.animateY(3000);
    }


    private void lightHistoryBarChart(BarChart chart){

        //lineEntry = new ArrayList<>();
        //lineEntryLabels = new ArrayList<>();

        BarEntrys = new ArrayList<>();
        BarEntryLabels = new ArrayList<String>();


        for (int i = 0; i< lightHistoryList.size();i++){
            LightHistory lightHistory = lightHistoryList.get(i);

            if(lightHistory.getValue().equals("true")){
                BarEntrys.add(new BarEntry(1, i));
                //lineEntry.add(new Entry(1, i));
            } else {
                BarEntrys.add(new BarEntry(0, i));
                //lineEntry.add(new Entry(01, i));
            }
            //lineEntryLabels.add(lightHistory.getLastDate());
            BarEntryLabels.add(lightHistory.getLastDate());
        }
        Bardataset = new BarDataSet(BarEntrys, "Light status");
        BARDATA = new BarData(BarEntryLabels, Bardataset);
        Bardataset.setColor(R.color.lightbar_color);
        chart.setData(BARDATA);
        chart.animateY(3000);
    }



    private void startLineChart(LineChart lineChart){

        lineEntry = new ArrayList<>();
        lineEntryLabels = new ArrayList<String>();


        AddValuesToLineChart();
        lineDataset = new LineDataSet(lineEntry, "Water level");

        LineData data = new LineData(lineEntryLabels, lineDataset);
        lineDataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        lineDataset.setDrawCubic(true);
        lineDataset.setDrawFilled(true);

        lineChart.setData(data);
        lineChart.animateY(5000);
    }

    private void startBarChart(BarChart chart){
        BarEntrys = new ArrayList<>();
        BarEntryLabels = new ArrayList<String>();

        AddValuesToBarEntry();

        Bardataset = new BarDataSet(BarEntrys, "Water level");

        BARDATA = new BarData(BarEntryLabels, Bardataset);

        Bardataset.setColors(ColorTemplate.JOYFUL_COLORS);

        chart.setData(BARDATA);

        chart.animateY(3000);

        //parent.addView(barChart);

       /* lineDataset = new LineDataSet(lineEntry, "Water level");


        LineData data = new LineData(lineEntryLabels, lineDataset);
        lineDataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        lineDataset.setDrawCubic(true);
        lineDataset.setDrawFilled(true);

        lineChart.setData(data);
        lineChart.animateY(5000);*/


    }

    private void AddValuesToLineChart(){
        for (int i = 0; i< waterLevelsLevelList.size();i++){
            lineEntry.add(new Entry(waterLevelsLevelList.get(i).getValue(), i));
            lineEntryLabels.add(waterLevelsLevelList.get(i).getLastDate());
        }
    }

    private void AddValuesToBarEntry(){
        for (int i = 0; i< waterLevelsLevelList.size();i++){
            WaterLevelHistory waterLevel = waterLevelsLevelList.get(i);
            BarEntrys.add(new BarEntry(waterLevel.getValue(), i));
            BarEntryLabels.add(waterLevel.getLastDate());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_gateway) {
            Intent gatewayListActivityIntent = new Intent(context,GatewayActivity.class);
            startActivity(gatewayListActivityIntent);
            return true;
        }

        /*if (id == R.id.action_settings) {
            Intent settingsActivityIntent = new Intent(context,CreateGatewayActivity.class);
            startActivity(settingsActivityIntent);
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(call != null){
            call = null;
            Log.d("call not null","st null");
        }
    }
}
