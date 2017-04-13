


/*
Citing outside sources used as tutorials:
-some of the material used here was not in the scope of the course the following websites were used
for learning purposes and as a guide to help develop the android app.

https://www.androidtutorialpoint.com/intermediate/android-map-app-showing-current-location-android/
http://stackoverflow.com/questions/20762001/how-to-set-seekbar-min-and-max-value
https://github.com/eddydn/GetCoordinatesGeocode/blob/master/app/src/main/java/dev/edmt/getcoordinatesgeocode/MainActivity.java
http://danielnugent.blogspot.ca/2015/06/updated-jsonparser-with.html
 */


/**
 * This class controls the main activity on android. It is the activity that starts up when you open
 * the app
 */

package com.company.group18.baltimoress;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener{

    public static ArrayList<ArrayList<String>> dataList;
    InputStream in;
    BufferedReader reader;

    Button getSearchLocation;
    EditText searchBar;
    public static SeekBar sBar;
    public static double barValue = 0.007;


    private static GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    Hashtable<String,Arrest> markersOnMap = new Hashtable<String, Arrest>();

    public static TextView arrestID, age, sex, race, arrestDate, arrestTime, arrestLocation, incidentOffense, incidentLocation,  charge;
    public static TextView chargeDescription, district, post, neighbourhood;



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




        getSearchLocation = (Button)findViewById(R.id.button_search);
        searchBar = (EditText)findViewById(R.id.searchEdittext);


        getSearchLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    new GetCoordinates().execute(searchBar.getText().toString().replace(" ", "+"));

                if(searchBar.getText().equals("Baltimore") != true) {
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    View focusedView = getCurrentFocus();
                    if (focusedView != null) {
                        inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                    }

                }
            }
        });



        sBar = (SeekBar)findViewById(R.id.seekBar2);
        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Toast.makeText(MapsActivity.this, progress+"", Toast.LENGTH_SHORT).show();// display toast
                barValue = progress/10000.0 + 0.002;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
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


//        LatLng toronto = new LatLng(43.6532, -79.38);
//        mMap.addMarker(new MarkerOptions().position(toronto).title("Toronto"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(toronto));

        com.company.group18.baltimoress.Location currentUserLocation = new com.company.group18.baltimoress.Location(39.2970007586, -76.5793864662);

        LatLng tempCurrentUserLocation = new LatLng(currentUserLocation.getXcrd(), currentUserLocation.getYcrd());

        mMap.addMarker(new MarkerOptions()
                .position(tempCurrentUserLocation)
                .title("Your Location")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.lg2))
        );

        mMap.moveCamera(CameraUpdateFactory.newLatLng(tempCurrentUserLocation));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        ArrayList<Arrest> closestList =  DataManipulation.findClosestArrests(DataManipulation.sorted, currentUserLocation, barValue);

        int count = 1;
        for(Arrest a: closestList){
            LatLng temp = new LatLng(a.getLocation().getXcrd(), a.getLocation().getYcrd());

            mMap.addMarker(new MarkerOptions()
                    .position(temp)
                    .title(count+" "+a.getArrestID())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .draggable(true)
            );


            markersOnMap.put(count+" "+a.getArrestID(),a);
            count++;
        }

        //Closest to your location----------------
        if(DataManipulation.sorted.length!=0) {
            Arrest b = ClosestArrestFind.find(currentUserLocation);
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(b.getLocation().getXcrd(), b.getLocation().getYcrd()))
                    .title(count + " " + b.getArrestID())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            );
            setData(b);
        }
        //----------------------------------------

        Toast.makeText(MapsActivity.this, markersOnMap.size()+" ", Toast.LENGTH_SHORT).show();// display toast


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {

            @Override
            public boolean onMarkerClick(Marker arg0) {
                if(arg0.getTitle().equals("Your Location")) { // if marker source is clicked
                    Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                }

                Arrest a = markersOnMap.get(arg0.getTitle());
                if(a != null) {
                    setData(a);
                }

                return true;
            }

        });


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
    public void onConnectionSuspended(int i) {}

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
    public void onConnectionFailed(ConnectionResult connectionResult) {}

    /*
    This method is used to dynamically fill the information about each arrest when you click
    on a map marker.
     */
    public void setData (Arrest arrest){
        arrestID = (TextView)findViewById(R.id.ArrestID);
        age = (TextView)findViewById(R.id.age);
        sex = (TextView)findViewById (R.id.sex);
        race = (TextView)findViewById(R.id.Race);
        arrestDate = (TextView) findViewById(R.id.date);
        arrestTime = (TextView) findViewById(R.id.time);
        arrestLocation = (TextView) findViewById(R.id.ArrestLocation);
        incidentOffense = (TextView) findViewById(R.id.incidentOffense);
        incidentLocation = (TextView) findViewById(R.id.inncidentLocation);
        charge = (TextView)findViewById (R.id.charge);
        chargeDescription = (TextView) findViewById(R.id.chargeDescription);
        district = (TextView)findViewById(R.id.district);
        post = (TextView)findViewById(R.id.post);
        neighbourhood = (TextView) findViewById(R.id.neighbourhood );


        arrestID.setText("Arrest ID: " + arrest.getArrestID()+"\n");
        age.setText("Age: " + arrest.getAge () +"\n");
        sex.setText("Sex: " + arrest.getSex ()+"\n");
        race.setText("Race: " + arrest.getRace () +"\n");
        arrestDate.setText("Arrest Date: " + arrest.getDate ()+"\n");
        arrestTime.setText("Arrest Time: " + arrest.getTime()+"\n");
        arrestLocation.setText("Arrest Location: " + arrest.getArrestLocation()+"\n");
        incidentOffense.setText("Incident Offense: " + arrest.getIncidentOffence()+"\n");
        incidentLocation.setText("Incident Location: " + arrest.getIncidentLocation()+"\n");
        charge.setText("Charge Location: " + arrest.getCharge()+"\n");
        chargeDescription.setText("Charge Description: " + arrest.getChargeDescription()+"\n");
        neighbourhood.setText("Neighbourhood: " + arrest.getNeighborhood()+"\n");
        district.setText("District: " + arrest.getDistrict()+"\n");
        post.setText("Post: " + arrest.getPost()+"\n");
    }



    /*
    Inner private class used to get the coordinates from google api call.
     */
    private class GetCoordinates extends AsyncTask<String,Void,String> {
        ProgressDialog dialog = new ProgressDialog(MapsActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait....");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String response;
            try{
                String address = strings[0];
                HttpData http = new HttpData();
                String url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s",address);
                response = http.getHTTPData(url);
                return response;
            }
            catch (Exception ex)
            {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                JSONObject jsonObject = new JSONObject(s);

                String lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                        .getJSONObject("location").get("lat").toString();
                String lng = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                        .getJSONObject("location").get("lng").toString();

                Toast.makeText(MapsActivity.this, lat+" "+lng, Toast.LENGTH_SHORT).show();// display toast

                mMap.clear();
                markersOnMap.clear();


                com.company.group18.baltimoress.Location currentUserLocation = new com.company.group18.baltimoress.Location(Double.parseDouble(lat), Double.parseDouble(lng));
                LatLng tempCurrentUserLocation = new LatLng( Double.parseDouble(lat), Double.parseDouble(lng));

                if(lat.length() <= 0 || lng.length() <= 0){
                    currentUserLocation = new com.company.group18.baltimoress.Location(39.2970007586, -76.5793864662);
                    tempCurrentUserLocation = new LatLng(currentUserLocation.getXcrd(), currentUserLocation.getYcrd());
                }

                mMap.addMarker(new MarkerOptions()
                        .position(tempCurrentUserLocation)
                        .title("Your Location")
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.lg2))
                );

                mMap.moveCamera(CameraUpdateFactory.newLatLng(tempCurrentUserLocation));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                ArrayList<Arrest> closestList =  DataManipulation.findClosestArrests(DataManipulation.sorted, currentUserLocation, barValue);

                int count = 1;
                for(Arrest a: closestList){
                    LatLng temp = new LatLng(a.getLocation().getXcrd(), a.getLocation().getYcrd());

                    mMap.addMarker(new MarkerOptions()
                            .position(temp)
                            .title(count+" "+a.getArrestID())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    );


                    markersOnMap.put(count+" "+a.getArrestID(),a);
                    count++;
                }
                //Closest to your location----------------
                if(DataManipulation.sorted.length!=0) {
                    Arrest b = ClosestArrestFind.find(currentUserLocation);
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(b.getLocation().getXcrd(), b.getLocation().getYcrd()))
                            .title(count + " " + b.getArrestID())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    );
                    setData(b);
                }
                //----------------------------------------
                Toast.makeText(MapsActivity.this, markersOnMap.size()+" ", Toast.LENGTH_SHORT).show();// display toast


                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
                {

                    @Override
                    public boolean onMarkerClick(Marker arg0) {
                        if(arg0.getTitle().equals("Your Location")) { // if marker source is clicked
                            Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                        }

                        Arrest a = markersOnMap.get(arg0.getTitle());
                        if(a != null) {
                            setData(a);
                        }

                        return true;
                    }

                });

                if(dialog.isShowing())
                    dialog.dismiss();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
