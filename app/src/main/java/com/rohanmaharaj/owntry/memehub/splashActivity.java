package com.rohanmaharaj.owntry.memehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent intu = new Intent(this, MainActivity.class);
        new Handler().postDelayed(() -> {
            startActivity(intu);
            finish();
        }, 3210);
    }
}