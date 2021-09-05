package com.example.velm.iot.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.velm.iot.R;
import com.example.velm.iot.adapter.GatewayAdapter;
import com.example.velm.iot.model.Gateway;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velmmuru on 9/18/2017.
 */

public class GatewayActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gateway_activity);

        context = this;
        recyclerView = (RecyclerView)findViewById(R.id.gatewayRecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Gateway> gatewayList = new ArrayList<>();
        Gateway gateway1 = new Gateway();
        gateway1.setIp("12.12.12.12");
        gateway1.setClientName("Client 1");
        gatewayList.add(gateway1);

        Gateway gateway2 = new Gateway();
        gateway2.setIp("21.21.12.12");
        gateway2.setClientName("Client 2");
        gatewayList.add(gateway2);

        GatewayAdapter gatewayAdapter = new GatewayAdapter(context,gatewayList);

        recyclerView.setAdapter(gatewayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gateway_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_create) {
            Intent settingIntent = new Intent(context,CreateGatewayActivity.class);
            startActivity(settingIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
