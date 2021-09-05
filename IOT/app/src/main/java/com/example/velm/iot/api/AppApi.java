package com.example.velm.iot.api;


import com.example.velm.iot.model.Gateway;
import com.example.velm.iot.model.LightHistory;
import com.example.velm.iot.model.Schedule;
import com.example.velm.iot.model.WaterLevelHistory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by velmmuru on 8/9/2017.
 */

public interface AppApi {

    @GET("nodes/cnode0/history/{controllerName}/{nodeNumber}")
    Call<List<WaterLevelHistory>> getWaterLevel(@Path("controllerName") String controllerName, @Path("nodeNumber") int nodeNumber);


    @GET("nodes/cnode0/history/{controllerName}/{nodeNumber}")
    Call<List<LightHistory>> getLightHistory(@Path("controllerName") String controllerName, @Path("nodeNumber") int nodeNumber);


    @POST("nodes/scheduler/")
    Call<Schedule> createSchedule(@Body Schedule schedule);

    @DELETE("nodes/scheduler/{id}")
    Call<Schedule> deleteSchedule(@Path("id") int id);

    @GET("nodes/scheduler/{id}")
    Call<Schedule> getSchedule(@Path("id") int id);

    @GET("nodes/")
    Call<List<Gateway>> getGateways();

    @GET("nodes/grid")
    Call<List<Object>> getNodes();
}
