package com.example.velm.taskmanager.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.velm.taskmanager.R;
import com.example.velm.taskmanager.dbhandler.DatabaseHandler;
import com.example.velm.taskmanager.models.User;
import com.example.velm.taskmanager.utils.Utils;

/**
 * Created by velm on 9/8/2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    EditText editTextLoginName,editTextLoginPin;
    Button buttonLogin;
    TextView textViewRegister;
    SharedPreferences sharedpreferences;
    DatabaseHandler db ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLogin();
        setContentView(R.layout.login_activity);
        context = this;
        initview();
    }

    private void initview(){

        editTextLoginName = (EditText)findViewById(R.id.editTextLoginName);
        editTextLoginPin = (EditText)findViewById(R.id.editTextLoginPin);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
        textViewRegister = (TextView) findViewById(R.id.textViewRegister);
        textViewRegister.setOnClickListener(this);

    }

    private void checkLogin(){

        sharedpreferences = getSharedPreferences(Utils.MyPREF, Context.MODE_PRIVATE);
        db = new DatabaseHandler(this);
        String userName = sharedpreferences.getString("user_name",null);

        if(userName != null){
            Intent mainActivityIntent = new Intent(this,MainActivity.class);
            startActivity(mainActivityIntent);
            finish();
        }
    }


    private boolean validateUser(){

        String userName = editTextLoginName.getText().toString();
        String userPin = editTextLoginPin.getText().toString();

        if(userName != null && userPin != null){

            if(userName.trim().length() > 3 && userPin.length() > 3){

                User user = db.validateUser(userName,userPin);

                if(user != null){
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("user_name", user.getName());
                    editor.putInt("user_id", user.getId());
                    editor.putInt("user_pin", user.getPin());
                    editor.commit();
                    Toast.makeText(context,"Login Success",Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    Toast.makeText(context,"Login Failed ",Toast.LENGTH_SHORT).show();
                }
            } else {

                Toast.makeText(context,"Invalid Input ",Toast.LENGTH_SHORT).show();
            }
        }

        return false;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.buttonLogin:
                if(validateUser()){
                    Intent mainActivityIntent = new Intent(context,MainActivity.class);
                    startActivity(mainActivityIntent);
                    finish();
                }
                break;

            case  R.id.textViewRegister:
                Intent registerActivityIntent = new Intent(context,RegisterActivity.class);
                startActivity(registerActivityIntent);
                break;

            default:

                break;
        }
    }
}
