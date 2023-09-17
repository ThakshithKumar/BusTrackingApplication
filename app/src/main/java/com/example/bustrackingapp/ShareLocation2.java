package com.example.bustrackingapp;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.bustrackingapp.databinding.ActivityShareLocation2Binding;
import com.example.bustrackingapp.databinding.ActivityUserMapsBinding;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Granularity;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.Priority;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class ShareLocation2 extends AppCompatActivity{


    ActivityShareLocation2Binding binding;
    FusedLocationProviderClient client;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String BusNumber, FromLocation, ToLocation, BusLatitude, BusLongitude, BusSpeed;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityShareLocation2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AutoCompleteTextView busNumber = binding.busNumber;
        AutoCompleteTextView fromLocation = binding.fromLocation;
        AutoCompleteTextView toLocation = binding.toLocation;
        loading = (ProgressBar)binding.progressBar;
        loading.setVisibility(View.GONE);

        String[] busNumbers = getResources().getStringArray(R.array.busnumbers);
        ArrayAdapter<String> busAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,busNumbers);
        busNumber.setAdapter(busAdapter);

        String[] ToFromLocations = getResources().getStringArray(R.array.tofromLocations);
        ArrayAdapter<String> locationsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,ToFromLocations);

        fromLocation.setAdapter(locationsAdapter);
        toLocation.setAdapter(locationsAdapter);


        client = (FusedLocationProviderClient)LocationServices.getFusedLocationProviderClient(this);

        binding.shareLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BusNumber = binding.busNumber.getText().toString();
                ToLocation = binding.toLocation.getText().toString();
                FromLocation = binding.fromLocation.getText().toString();

                Dexter.withContext(getApplicationContext())
                        .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Log.d("loca2", "workingggggggg");
                                getLocationUpdates();
                                loading.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }

            });

            binding.stopShareLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dexter.withContext(getApplicationContext())
                            .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                            .withListener(new PermissionListener() {
                                @Override
                                public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                    Log.d("loc", "stoppingg");
                                    stopLocationUpdates();
                                    loading.setVisibility(View.GONE);
                                }

                                @Override
                                public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                    permissionToken.continuePermissionRequest();
                                }
                            }).check();
                }
            });
        }

    private void getLocationUpdates() {
        LocationRequest locationRequest = new LocationRequest.Builder(1000)
                .setGranularity(Granularity.GRANULARITY_FINE)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .setMinUpdateDistanceMeters(0)
                .setMaxUpdates(Integer.MAX_VALUE)
                .build();

        LocationSettingsRequest locationSettingsRequest = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
                .build();


        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest).addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {

            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                if (task.isSuccessful()) {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    client.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
                    Toast.makeText(getApplicationContext(),"Sharing Location of this Device",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (task.getException() instanceof ResolvableApiException) {
                        try {
                            ResolvableApiException resolvableApiException = (ResolvableApiException) task.getException();
                            resolvableApiException.startResolutionForResult(ShareLocation2.this, 1001);
                        } catch (IntentSender.SendIntentException e) {
                            Log.d("exception", " " + e);
                        }
                    } else {

                    }
                }
            }
        });
    }

    public LocationCallback locationCallback = new LocationCallback() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            if(locationResult == null){
                return;
            }

            for(Location location: locationResult.getLocations()){
                Log.d("demo", "BusLocationResult: \n Latitude: " + location.getLatitude() + "\n Longitude: " +
                        location.getLongitude()+ "Speed " + String.valueOf(location.getSpeed()) );


                BusLatitude = String.valueOf(location.getLatitude());
                BusLongitude = String.valueOf(location.getLongitude());
                BusSpeed = String.valueOf(location.getSpeed());
                Buses buses = new Buses(BusNumber,FromLocation,ToLocation,BusLatitude,BusLongitude,BusSpeed);
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference =  firebaseDatabase.getReference("Buses");
                databaseReference.child(BusNumber).setValue(buses).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });

            }

        }
    };

    public void stopLocationUpdates(){
        client.removeLocationUpdates(locationCallback);
        Log.d("remove", "Stopping Location Sharing!!!");
        Toast.makeText(getApplicationContext(),"Location Sharing is Stopped",Toast.LENGTH_SHORT).show();
    }
}





