package com.github.saintedlittle.stalkerapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.github.saintedlittle.stalkerapp.service.ServiceBinding;
import com.github.saintedlittle.stalkerapp.service.StalkerService;

import androidx.appcompat.app.AppCompatActivity;

import com.github.saintedlittle.stalkerapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.github.saintedlittle.stalkerapp.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        startService(new Intent(this, StalkerService.class));

        this.finish();

    }

}