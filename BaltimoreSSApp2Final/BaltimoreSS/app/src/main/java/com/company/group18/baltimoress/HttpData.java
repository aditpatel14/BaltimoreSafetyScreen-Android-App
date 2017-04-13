/*
Citing outside sources used as tutorials:
-some of the material used here was not in the scope of the course the following websites were used
for learning purposes and as a guide to help develop the android app.

https://www.androidtutorialpoint.com/intermediate/android-map-app-showing-current-location-android/
http://stackoverflow.com/questions/20762001/how-to-set-seekbar-min-and-max-value
https://github.com/eddydn/GetCoordinatesGeocode/blob/master/app/src/main/java/dev/edmt/getcoordinatesgeocode/MainActivity.java
http://danielnugent.blogspot.ca/2015/06/updated-jsonparser-with.html
 */

package com.company.group18.baltimoress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpData {

    public HttpData() {

    }

    /**
     * @param requestURL The url you request to access 
     * @return the data you get from the online file 
     */
    public String getHTTPData(String requestURL)
    {
        URL url;
        String response = "";
        try{
            url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            int responseCode = conn.getResponseCode();

            //Reading from the online file 
            if(responseCode == HttpsURLConnection.HTTP_OK){
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while((line = br.readLine()) != null)
                    response+=line;
            }
            else
                response = "";

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}