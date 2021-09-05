package com.example.velm.iot.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.velm.iot.R;
import com.example.velm.iot.adapter.GatewayNodeAdapter;
import com.example.velm.iot.api.HttpHandler;
import com.example.velm.iot.model.LightCtrl;
import com.example.velm.iot.model.Nodes;
import com.example.velm.iot.utils.Constants;
import com.example.velm.iot.utils.SharedPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velmmuru on 8/9/2017.
 */

public class GatewayNodeActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private Context context;
    private GatewayNodeAdapter gatewayNodeAdapter;

    private List<String> list;

    private Nodes currentNode;


    String TAG ="GatewayNodeActivity";

    private String clientNode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("GATEWAY");
        setContentView(R.layout.gateway_node_activity);
        context = this;
        currentNode = new Nodes();
        clientNode = new SharedPreference().getClientNode(context);
        Log.d("Nodename = ",clientNode);
        initView();



        /*Call<List<Object>> nodesCall = AppApiImpl.getInstance().getNodes();


        nodesCall.enqueue(new Callback<List<Object>>() {
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                if(response.body() != null){

                    Log.d(TAG,"response.body() = "+response.body());

                    JsonParser parser = new JsonParser();
                    JsonObject obj = parser.parse(response.body().toString()).getAsJsonObject();
                    Set<Map.Entry<String,JsonElement>> set = obj.entrySet();

                    Log.d(TAG,set.toString());

                    for (int i = 0; i < response.body().size();i++){

                        try {
                            JSONObject jsonObject = new JSONObject(response.body().get(i).toString());

                            String clientName = jsonObject.getString("clientName");

                            Log.d(TAG,"clientName = "+clientName);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    //Nodes nodes = new Nodes();

                }
            }

            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {

            }
        });
*/



    }

    private void initView(){

        list = new ArrayList<>();
        list.add("ONE");

        list.add("TWO");
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);



        new GetNodes().execute();
    }
    private class GetNodes extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();
            String url = "http://10.0.2.2:3000/api/nodes/grid";
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);
            Log.d("TE",jsonStr.toString());
            try {
                JSONArray jArray = new JSONArray(jsonStr);
                for (int i = 0; i < jArray.length(); i++) {
                    Nodes nodes = new Nodes();
                    Log.d("JSOB",jArray.getString(i).toString());

                    JSONObject obj = jArray.getJSONObject(i);
                    String clientName = obj.getString("clientName");
                    if(clientName.equals("c"+clientNode)){
                        currentNode.setClientName(clientName);
                        Log.d("clientName = ",clientName);

                        JSONObject lightCtrl = obj.getJSONObject("lightCtrl");
                        List<LightCtrl> lightCtrls = getLightCtrl(lightCtrl,true);


                        Log.d("After LIGHTS",currentNode.toString());
                        JSONObject motorCtrl = obj.getJSONObject("motorCtrl");
                        lightCtrls.addAll(getLightCtrl(motorCtrl,false));
                        //currentNode.setMotorCtrl(motorCtrls);


                        JSONObject waterLevel = obj.getJSONObject("waterLevel");
                        List<LightCtrl> waterCtrls = getWaterCtrl(waterLevel);
                        lightCtrls.addAll(getWaterCtrl(waterLevel));
                        //currentNode.setWaterLevel(waterCtrls);

                        currentNode.setLightCtrl(lightCtrls);
                        break;
                    } else {
                        continue;
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.d("ALL",currentNode.toString());
            return null;
        }

        private List<LightCtrl> getWaterCtrl(JSONObject waterCtrl){

            List<LightCtrl> waterCtrls = new ArrayList<>();
            if(waterCtrl != null){
                LightCtrl WaterCtrl1 = new LightCtrl();
                try {
                    boolean isFinished = true;
                    int j = 0;
                    while (isFinished){
                        JSONObject ob = waterCtrl.getJSONObject(j+"");
                        if(ob != null){
                            Log.d("OB ",ob.toString());
                            int sensorValue = ob.getInt("sensorValue");
                            WaterCtrl1.setCtrlName(Constants.WATER_LEVEL);
                            WaterCtrl1.setSensorValue(sensorValue);
                            WaterCtrl1.setNodeNumber(j);
                        } else {
                            isFinished = false;
                        }
                        j = j + 1;
                        waterCtrls.add(WaterCtrl1);
                    }

                }catch (Exception e){
                    Log.d("Exe","ex");
                }
            }

            return waterCtrls;
        }

        private List<LightCtrl> getLightCtrl(JSONObject lightCtrl,boolean isLight) {

            List<LightCtrl> lightCtrls = new ArrayList<>();
            if(lightCtrl != null){
                try {
                    boolean isFinished = true;
                    int j = 0;
                    while (isFinished){
                        LightCtrl lightCtrl1 = new LightCtrl();
                        JSONObject ob = lightCtrl.getJSONObject(j+"");
                        if(ob != null){
                            boolean status = ob.getBoolean("onOff");
                            Log.d(" j =",j+"");
                            Log.d("status ",status+"");
                            if(isLight){
                                lightCtrl1.setNodeNumber(j);
                                lightCtrl1.setCtrlName(Constants.LIGHT_CTRL);
                            } else {
                                lightCtrl1.setNodeNumber(j);
                                lightCtrl1.setCtrlName(Constants.MOTOR_CTRL);
                            }

                            lightCtrl1.setOnOff(status);
                        } else {
                            isFinished = false;
                        }

                        j = j + 1;
                        lightCtrls.add(lightCtrl1);
                    }

                }catch (Exception e){
                    Log.d("Exe","ex");
                }

            }

            return lightCtrls;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            gatewayNodeAdapter = new GatewayNodeAdapter(context,currentNode,clientNode);


            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(gatewayNodeAdapter);

        }
    }

}
