package com.example.velm.iot.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.velm.iot.R;

/**
 * Created by velmmuru on 8/9/2017.
 */

public class CreateGatewayActivity extends AppCompatActivity {


    private Button buttonSaveGateway;
    private Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_gateway_activity);
        context = this;
        initView();
    }

    private void initView(){

        buttonSaveGateway = (Button)findViewById(R.id.buttonSaveGateway);

        buttonSaveGateway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gatewayIntent = new Intent(context,GatewayActivity.class);

                startActivity(gatewayIntent);
            }
        });
    }
}
