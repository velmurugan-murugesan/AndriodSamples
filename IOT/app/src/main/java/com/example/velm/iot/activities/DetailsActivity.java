package com.example.velm.iot.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

public class DetailsActivity extends AppCompatActivity {


    private List<WaterLevelHistory> waterLevelsLevelList;

    private List<LightHistory> lightHistoryList;

    private BarChart barChart;
    private ArrayList<com.github.mikephil.charting.data.BarEntry> BarEntry ;
    private ArrayList<String> BarEntryLabels ;
    private BarDataSet Bardataset ;
    private BarData BARDATA ;

    private LineChart lineChart;
    private ArrayList<Entry> lineEntry ;
    private ArrayList<String> lineEntryLabels ;
    private LineDataSet lineDataset;
    private String controllerName;
    private String nodeName;
    private int nodeNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        initView();
    }

    private void initView(){

        barChart = (BarChart) findViewById(R.id.barchart);

        barChart.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getApplicationContext(),"BARCHART",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        lineChart = (LineChart) findViewById(R.id.linechart);

        waterLevelsLevelList = new ArrayList<>();
        lightHistoryList = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();


        nodeName = bundle.getString("nodeName");
        controllerName = bundle.getString("controllerName");
        nodeNumber = bundle.getInt("nodeNumber");

        Log.d("Node",nodeName+" "+controllerName+""+ nodeNumber);

        if(controllerName.equals("waterLevel")){
            waterLevelService(controllerName,nodeNumber);
        } else {
            lightHistory(controllerName,nodeNumber);
        }
    }

    private void waterLevelService(final String controllerName, int nodeNumber){
        AppApi apiService = ApiClient.getClient().create(AppApi.class);

        Call<List<WaterLevelHistory>> call = apiService.getWaterLevel(controllerName,nodeNumber);


        call.enqueue(new Callback<List<WaterLevelHistory>>() {
            @Override
            public void onResponse(Call<List<WaterLevelHistory>> call, Response<List<WaterLevelHistory>> response) {
                Log.d("success call","");
                waterLevelsLevelList = (List<WaterLevelHistory>) response.body();

                Log.d("waterLevelsLevelList = ",waterLevelsLevelList.toString());
                startGraph(controllerName);
            }

            @Override
            public void onFailure(Call<List<WaterLevelHistory>> call, Throwable t) {
                Log.d("failed call = ",t.fillInStackTrace().toString());
            }
        });
    }

    private void lightHistory(final String controllerName, int nodeNumber){
        AppApi apiService = ApiClient.getClient().create(AppApi.class);

        Call<List<LightHistory>> call = apiService.getLightHistory(controllerName,nodeNumber);

        call.enqueue(new Callback<List<LightHistory>>() {
            @Override
            public void onResponse(Call<List<LightHistory>> call, Response<List<LightHistory>> response) {
                Log.d("success call","");
                lightHistoryList = (List<LightHistory>) response.body();

                Log.d("waterLevelsLevelList = ",lightHistoryList.toString());
                lightHistoryGraph(controllerName);
            }

            @Override
            public void onFailure(Call<List<LightHistory>> call, Throwable t) {

            }
        });

    }

    private void lightHistoryGraph(String controllerName){

        lineEntry = new ArrayList<>();
        lineEntryLabels = new ArrayList<>();

        BarEntry = new ArrayList<>();
        BarEntryLabels = new ArrayList<String>();


        for (int i = 0; i< lightHistoryList.size();i++){
            LightHistory lightHistory = lightHistoryList.get(i);

            if(lightHistory.getValue().equals("true")){
                BarEntry.add(new BarEntry(1, i));
                lineEntry.add(new Entry(1, i));
            } else {
                BarEntry.add(new BarEntry(0, i));
                lineEntry.add(new Entry(01, i));
            }
            lineEntryLabels.add(lightHistory.getLastDate());
            BarEntryLabels.add(lightHistory.getLastDate());
        }


        Bardataset = new BarDataSet(BarEntry, controllerName);

        BARDATA = new BarData(BarEntryLabels, Bardataset);

        Bardataset.setColor(R.color.lightbar_color);

        barChart.setData(BARDATA);

        barChart.animateY(3000);



        lineDataset = new LineDataSet(lineEntry, controllerName);


        LineData data = new LineData(lineEntryLabels, lineDataset);
        lineDataset.setColor(R.color.lightbar_color);
        lineDataset.setDrawCubic(true);
        lineDataset.setDrawFilled(true);

        lineChart.setData(data);
        lineChart.animateY(5000);
    }

    private void startGraph(String controllerName){
        BarEntry = new ArrayList<>();
        BarEntryLabels = new ArrayList<String>();

        lineEntry = new ArrayList<>();
        lineEntryLabels = new ArrayList<>();


        AddValuesToBarEntry();


        Bardataset = new BarDataSet(BarEntry, controllerName);

        BARDATA = new BarData(BarEntryLabels, Bardataset);

        Bardataset.setColors(ColorTemplate.JOYFUL_COLORS);

        barChart.setData(BARDATA);

        barChart.animateY(3000);

        lineDataset = new LineDataSet(lineEntry, controllerName);
        LineData data = new LineData(lineEntryLabels, lineDataset);
        lineDataset.setColors(ColorTemplate.JOYFUL_COLORS); //
        lineDataset.setDrawCubic(true);
        lineDataset.setDrawFilled(true);

        lineChart.setData(data);
        lineChart.animateY(5000);
    }

    private void AddValuesToBarEntry(){

        for (int i = 0; i< waterLevelsLevelList.size();i++){
            WaterLevelHistory waterLevel = waterLevelsLevelList.get(i);
            BarEntry.add(new BarEntry(waterLevel.getValue(), i));
            BarEntryLabels.add(waterLevel.getLastDate());

            lineEntry.add(new Entry(waterLevel.getValue(), i));
            lineEntryLabels.add(waterLevel.getLastDate());

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorite, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Bundle bundle = getIntent().getExtras();
        nodeName = bundle.getString("nodeName");
        controllerName = bundle.getString("controllerName");
        nodeNumber = bundle.getInt("nodeNumber");
        boolean isFavo = false;
        Favorites favorite = new Favorites(nodeName,controllerName,nodeNumber,"Barchart");
        List<Favorites> favorites = new ArrayList<Favorites>();
        favorites = new SharedPreference().getFavorites(getApplicationContext());

        if(favorites != null){
            for(int i = 0; i< favorites.size();i++){

                if(favorites.get(i).getControllerName().equals(favorite.getControllerName()) &&
                        favorites.get(i).getNodeName().equals(favorite.getNodeName())){
                    Log.d("ALREDY","EXIST");
                    isFavo = true;
                    break;
                }
            }
        }

        if(isFavo){
            menu.findItem(R.id.favorite).setTitle("Remove Favo");
        } else {
            menu.findItem(R.id.favorite).setTitle("Add to Favo");
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.favorite) {

            boolean isFavo = false;
            Favorites favorite = new Favorites(nodeName,controllerName,nodeNumber,"BarChart");
            List<Favorites> favorites = new ArrayList<>();
            favorites = new SharedPreference().getFavorites(getApplicationContext());
            int i = 0;
            if(favorites != null){
                for(i = 0; i< favorites.size();i++){

                    if(favorites.get(i).getControllerName().equals(favorite.getControllerName()) &&
                            favorites.get(i).getNodeName().equals(favorite.getNodeName())){
                        Log.d("ALREDY","EXIST");
                        isFavo = true;
                        break;
                    }
                }
            }

            if(isFavo){
                Log.d("FAV",favorite.toString());
                Log.d("FAVs",favorites.toString());
                favorites.remove(i);
                Log.d(" AFTER FAVs",favorites.toString());
                new SharedPreference().saveFavorites(getApplicationContext(),favorites);
                //favorites.remove(favorite);
                //new SharedPreference().removeFavorite(getApplicationContext(),favorite);

                Toast.makeText(getApplicationContext(),"Removed from Favorite",Toast.LENGTH_SHORT).show();
                item.setTitle("Add to Favo");
            } else {
                new SharedPreference().addFavorite(getApplicationContext(),favorite);
                Toast.makeText(getApplicationContext(),"Added to Favorite",Toast.LENGTH_SHORT).show();
                item.setTitle("Remove Favo");
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
