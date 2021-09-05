package com.example.velm.iot.api;


import com.example.velm.iot.model.Gateway;
import com.example.velm.iot.model.LightHistory;
import com.example.velm.iot.model.Schedule;
import com.example.velm.iot.model.WaterLevelHistory;

import java.util.List;

import retrofit2.Call;

/**
 * Created by velmmuru on 8/17/2017.
 */

public class AppApiImpl implements AppApi {



    private static AppApiImpl appApiImpl;


    public static AppApiImpl getInstance(){
        if(appApiImpl == null){
            return appApiImpl = new AppApiImpl();
        }
        return appApiImpl;
    }

    private AppApi appApi;

    private AppApi getApiService(){
        if (appApi == null){
           return appApi = ApiClient.getClient().create(AppApi.class);
        }
        return appApi;
    }


    @Override
    public Call<List<WaterLevelHistory>> getWaterLevel(String controllerName , int nodeNumber) {
        return getApiService().getWaterLevel(controllerName,nodeNumber);
    }

    @Override
    public Call<List<LightHistory>> getLightHistory(String controllerName, int nodeNumber) {
        return getApiService().getLightHistory(controllerName,nodeNumber);
    }

    @Override
    public Call<Schedule> createSchedule(Schedule schedule) {

        return getApiService().createSchedule(schedule);
    }

    @Override
    public Call<Schedule> deleteSchedule(int id) {

        return  getApiService().deleteSchedule(id);
    }

    @Override
    public Call<Schedule> getSchedule(int id) {
        return getApiService().getSchedule(id);
    }

    @Override
    public Call<List<Gateway>> getGateways() {
        return getApiService().getGateways();
    }

    @Override
    public Call<List<Object>> getNodes() {
        return getApiService().getNodes();
    }
}
