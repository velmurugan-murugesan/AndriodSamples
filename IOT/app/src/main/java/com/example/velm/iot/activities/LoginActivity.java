package com.example.velm.iot.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.velm.iot.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Context context;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;
        initView();
    }


    private void initView(){
         buttonLogin = (Button)findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.buttonLogin:
                Intent mainActivityIntent = new Intent(context,DashBoardActivity.class);
                startActivity(mainActivityIntent);
                finish();
                break;
            default:
                break;
        }

    }
}
