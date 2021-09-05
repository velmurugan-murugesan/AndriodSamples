package com.example.velm.taskmanager.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.velm.taskmanager.R;
import com.example.velm.taskmanager.dbhandler.DatabaseHandler;
import com.example.velm.taskmanager.models.User;

/**
 * Created by velm on 9/8/2017.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{


    EditText editTextRegisterName,editTextRegisterPin,editTextRegisterConfirmPin;
    Button buttonRegister;
    Context context;
    String registerName,registerPin,registerConfirmPin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        context = this;
        initview();
    }

    private void initview(){
        editTextRegisterName = (EditText)findViewById(R.id.editTextRegisterName);
        editTextRegisterPin = (EditText)findViewById(R.id.editTextRegisterPin);
        editTextRegisterConfirmPin = (EditText)findViewById(R.id.editTextRegisterconfirmPin);
        buttonRegister = (Button)findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(this);

    }

    private boolean validatePin(){

        registerPin = editTextRegisterPin.getText().toString();
        registerConfirmPin = editTextRegisterConfirmPin.getText().toString();
        int registerPinLength = String.valueOf(registerPin).length();
        int registerConfirmPinLength = String.valueOf(registerConfirmPin).length();
        if(registerPin != null && registerConfirmPin != null ){
            if(registerPinLength == 4 && registerConfirmPinLength == 4) {
                if(registerPin.equals(registerConfirmPin)){
                    return true;
                }else{
                    Toast.makeText(context,"Pin and ConfirmPin should be same",Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else {
                Toast.makeText(context,"Pin Length should be 4",Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(context,"Invalid Input",Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private boolean validateName(){
        registerName = editTextRegisterName.getText().toString();
        if(registerName != null && registerName.trim().length() > 3 ){
            return true;
        } else {
            Toast.makeText(context,"Name Invalid",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean saveUser(){

        if(validateName() && validatePin()){
            DatabaseHandler db = new DatabaseHandler(this);
            if(!db.checkUser(editTextRegisterName.getText().toString())){
                try {
                    User user = new User(registerName,Integer.parseInt(registerPin));
                    db.addUser(user);
                    return true;
                } catch (Exception e){
                    Toast.makeText(context,"Invalid Input",Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(context,"user already registered",Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.buttonRegister:

                if(saveUser()){
                    Toast.makeText(context,"Register succssful",Toast.LENGTH_SHORT).show();
                    Intent loginInetnt = new Intent(context,LoginActivity.class);
                    startActivity(loginInetnt);
                    finish();
                }
                break;

            default:
                break;

        }
    }
}
