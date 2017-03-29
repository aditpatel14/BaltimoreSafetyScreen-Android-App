package com.company.group18.baltimoress.javafiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;

/**
 * Created by Zack on 3/28/2017.
 */

public class HTTPDataHandler {

    public HTTPDataHandler () {}

    public String GetHTTPData (String requestUrl){
        URL url;
        String response = "";
        try{
            url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/x~www~form~urlencoded");
            conn.setDoOutput(true);
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null)
                    response += line;
            }
            else
                response = "";

        } catch (ProtocolException e){
            e.printStackTrace();
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch  (IOException e){
            e.printStackTrace();
        }
        return response;
    }
}


