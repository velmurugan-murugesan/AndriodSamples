package com.velmurugan.bustrackingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        AppCompatEditText edUsername = findViewById(R.id.edUsername);
        AppCompatEditText edPassword = findViewById(R.id.edPassword);

        Button loginButton = findViewById(R.id.btnLogin);



        loginButton.setOnClickListener(v -> {

            switch (Objects.requireNonNull(edUsername.getText()).toString()) {
                case "admin":
                    Intent adminIntent = new Intent(this, AdminActivity.class);
                    startActivity(adminIntent);
                    finish();
                    break;
                case "driver":
                    Intent driverIntent = new Intent(this, DriverActivity.class);
                    startActivity(driverIntent);
                    finish();
                    break;
                default:
                    Intent studentIntent = new Intent(this, StudentActivity.class);
                    startActivity(studentIntent);
                    finish();
                    break;
            }
        });

    }
}
