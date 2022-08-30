package com.example.cnvus.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {
    public static String getJsonFromNet(String path){
         HttpURLConnection  connection = null;
         InputStream inputStream = null;
         StringBuilder sb;
        BufferedReader  reader = null;
        String json="";
        try {
            URL url=new URL(path);
            connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            if (connection.getResponseCode()==HttpURLConnection.HTTP_OK) {
               inputStream=connection.getInputStream();
                  reader=new BufferedReader(new InputStreamReader(inputStream));
                  String line;
                  sb=new StringBuilder();
                while ((line=reader.readLine())!=null) {
                    sb.append(line);
                }
                json=sb.toString();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (inputStream!=null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader!=null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null) {
                connection.disconnect();
            }

        }
        return json;
    }
}
