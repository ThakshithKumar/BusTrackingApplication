package com.example.bustrackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button UserBtn;
    public Button ConductorBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserBtn = findViewById(R.id.userBtn);
        UserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InputBusDetails.class);
                startActivity(intent);
            }
        });

        ConductorBtn = findViewById(R.id.shareLocationBtn);
        ConductorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConductorLogin.class);
                startActivity(intent);
            }
        });
    }


}