package com.example.sam.randomtravelerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShowResaults extends AppCompatActivity {
String Daysfrom,Daysto,Price,From;
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
}
