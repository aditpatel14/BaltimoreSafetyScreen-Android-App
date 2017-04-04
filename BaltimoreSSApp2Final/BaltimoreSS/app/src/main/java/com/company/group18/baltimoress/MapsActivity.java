
/*
outside sources:
https://www.androidtutorialpoint.com/intermediate/android-map-app-showing-current-location-android/
 */

package com.company.group18.baltimoress;


import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.location.Location;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    public static ArrayList<ArrayList<String>> dataList;
    InputStream in;
    BufferedReader reader;

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        dataList = new ArrayList<ArrayList<String>>();
        if(DataManipulation.arrestList.size() <= 0) {
            try {
                in = this.getAssets().open("BPD_Arrests_Formatted.txt");
                reader = new BufferedReader(new InputStreamReader(in));
                String line = reader.readLine();
                int counter = 0;
                for (int j = 0; j < 100000; j++) {
                    //Scans line from input file into string
                    String current = reader.readLine();
                    //edits string to remove opening and closing brackets
                    current = current.replaceAll("[\t]", "%");
                    //Splits String to remove unnecessary characters and makes array
                    String[] temp = current.split("[%]");
                    counter++;
                    ArrayList<String> item = new ArrayList<String>();
                    for (int i = 0; i < temp.length; i++) {
                        String itemParse = temp[i];
                        item.add(itemParse);
                    }
                    //Adds array made form scanned string into jobs ArrayList to used for testing
                    dataList.add(item);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            DataManipulation.fillArrestList(dataList);

            //sort arrestList by location
            DataManipulation.sorted = DataManipulation.arrestList.toArray(new Arrest[DataManipulation.arrestList.size()]);
            DataManipulation.sorted = MergeSort.mergeSort(DataManipulation.sorted);

            dataList.clear();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //Initialize Google Play Services
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//
//        // Add a marker in Sydney and move the camera
//        LatLng toronto = new LatLng(43.6532, -79.38);
//        mMap.addMarker(new MarkerOptions().position(toronto).title("Toronto"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(toronto));

        com.company.group18.baltimoress.Location currentUserLocation = new com.company.group18.baltimoress.Location(39.2970007586, -76.5793864662);

        ArrayList<Arrest> closestList =  DataManipulation.findClosestArrests(DataManipulation.sorted, currentUserLocation, 0.010);

        for(Arrest a: closestList){
            LatLng temp = new LatLng(a.getLocation().getXcrd(), a.getLocation().getYcrd());
            mMap.addMarker(new MarkerOptions().position(temp).title(a.toString()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(temp));

            //move map camera
            mMap.moveCamera(CameraUpdateFactory.newLatLng(temp));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        }

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }



    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(10000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("My Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        mCurrLocationMarker = mMap.addMarker(markerOptions);



        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }





}
