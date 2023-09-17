package com.example.bustrackingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bustrackingapp.databinding.ActivityConductorLoginBinding;

public class ConductorLogin extends AppCompatActivity {
    ActivityConductorLoginBinding binding;

    String cUserName="",cPswd="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConductorLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cUserName = binding.cUsrName.getText().toString();
                cPswd = binding.cPswd.getText().toString();
                if(cUserName.equals("con") && cPswd.equals("password")){
                    Intent intent = new Intent(ConductorLogin.this, ShareLocation2.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ConductorLogin.this,"InValid Credentials!...Try Again!!", Toast.LENGTH_SHORT ).show();
                }

            }
        });
    }
}