package com.example.bustrackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.bustrackingapp.databinding.ActivityInputBusDetailsBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.GeoApiContext;

public class InputBusDetails extends AppCompatActivity {
    ActivityInputBusDetailsBinding binding;
    public Button LocateBus;
    AutoCompleteTextView UserBusNumber;
    AutoCompleteTextView UserDestination;
    //private GeoApiContext geoApiContext;
    //private FusedLocationProviderClient fusedLocationProviderClient;
    //private LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInputBusDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        UserBusNumber = binding.userBusNumber;
        UserDestination = binding.userDestination;

        String[] busNumbers = getResources().getStringArray(R.array.busnumbers);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,busNumbers);
        UserBusNumber.setAdapter(adapter);



        LocateBus = findViewById(R.id.locateBus);

        LocateBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*String userSelectedDestination = UserDestination.getText().toString();
                getCurrentLocationAndRecommendBus(userSelectedDestination);*/
                String userSelectedBusNumber = UserBusNumber.getText().toString();
                Intent intent = new Intent(InputBusDetails.this, UserMapsActivity.class);
                intent.putExtra("UserSelectedBusNumber", userSelectedBusNumber);
                startActivity(intent);
            }
        });


    }
}