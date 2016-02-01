package com.rohith.lbs;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Getaddress extends AppCompatActivity {


    String cityname;

    TextView latTextView;
    TextView longTextView;
    TextView locTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getaddress);

        latTextView = (TextView) findViewById(R.id.LattextView);
        longTextView = (TextView) findViewById(R.id.LongtextView);
        locTextView = (TextView) findViewById(R.id.LocationtextView);

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));

        Geocoder gcd=new Geocoder(getBaseContext(),Locale.getDefault());
        latTextView.setText(String.valueOf(location.getLatitude()));
        longTextView.setText(String.valueOf(location.getLongitude()));
        Log.d("Tag", "1");


        List<Address> addresses;

            try {
                addresses=gcd.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                if (addresses.size()>0){
                    cityname=addresses.get(0).getLocality().toString();
                    locTextView.setText(cityname);
                }
            }catch (IOException e){
                e.printStackTrace();
            }

    }


}




