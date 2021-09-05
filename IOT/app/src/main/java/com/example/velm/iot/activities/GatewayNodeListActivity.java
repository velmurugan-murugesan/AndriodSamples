package com.example.velm.iot.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.velm.iot.R;
import com.example.velm.iot.adapter.GatewayNodeListAdapter;
import com.example.velm.iot.api.AppApiImpl;
import com.example.velm.iot.model.Gateway;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by velmmuru on 8/11/2017.
 */

public class GatewayNodeListActivity extends AppCompatActivity {

    private ListView gatewayListview;
    private GatewayNodeListAdapter gatewayNodeListAdapter;
    private Context context;

    private List<Gateway> gatewayList;
    private Call<List<Gateway>> gatewaysCall;
    private final String TAG="GatewayNodeListActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gateway_node_list_activity);

        context = this;
        initView();
    }

    private void initView(){
        gatewayListview = (ListView)findViewById(R.id.gatewayListview);
        gatewayList = new ArrayList<>();
        loadGatewayNodeList();
    }


    private void loadGatewayNodeList(){
         gatewaysCall = AppApiImpl.getInstance().getGateways();

        gatewaysCall.enqueue(new Callback<List<Gateway>>() {
            @Override
            public void onResponse(Call<List<Gateway>> call, Response<List<Gateway>> response) {

                gatewayList = response.body();
                Log.d(TAG,gatewayList.toString());
                gatewayNodeListAdapter = new GatewayNodeListAdapter(context,gatewayList);
                gatewayListview.setAdapter(gatewayNodeListAdapter);
            }

            @Override
            public void onFailure(Call<List<Gateway>> call, Throwable t) {

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(gatewaysCall.isExecuted()){
            gatewaysCall.cancel();
            Log.d("gatewaysCall setto null","nul;l");
        }
    }
}
