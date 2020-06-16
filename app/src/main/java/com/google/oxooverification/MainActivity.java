package com.google.oxooverification;

import android.os.Bundle;

import com.google.rippleeffects.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public String customView() {
        return "";
    }

    @Override
    public String packageName() {
        return BuildConfig.APPLICATION_ID;
    }
}