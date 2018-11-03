package com.example.sam.randomtravelerapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ShowResaults extends AppCompatActivity {
   public String Daysfrom, Daysto, Price, From;
    public String Resaults="";
    public String [] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_resaults);
        Intent intent = getIntent();
        Daysfrom = intent.getStringExtra("Fday");
        Daysto = intent.getStringExtra("Tday");
        Price = intent.getStringExtra("Price");
        From = intent.getStringExtra("from");
    }


    private class fetchdataApi extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("https://api.skypicker.com/flights?fly_from="+From+"&price_to="+Price
                                                    +"&nights_in_dst_from="+Daysfrom
                                                        +"&nights_in_dst_to="+Daysto
                                                           +"&type_flights=lcc");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                InputStream inputs = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputs));
                String line = "";
                StringBuilder sb= new StringBuilder();

                //for Vodka
                while((line=br.readLine())!= null){
                    sb.append(line+"\n");
                }
                inputs.close();
                Resaults=sb.toString();


            }catch (Exception e){
                e.printStackTrace();
            }

            //try parse json data
            try{
                JSONArray ja=new JSONArray(Resaults);
                JSONObject jo=null;
                data=new String[ja.length()];


                for (int i=0;i<=ja.length();i++){
                    jo=ja.getJSONObject(i);
                    data[i]="Απο"+" "+jo.getString("cityFrom")+" "+"Για"+" "+jo.getString("cityΤο");
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }



            return null;
        }
    }
}