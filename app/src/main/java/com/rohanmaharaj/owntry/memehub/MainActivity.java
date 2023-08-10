package com.rohanmaharaj.owntry.memehub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    private long backPrsdTime;
    private Toast tst;
    ImageView imgvw;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgvw = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.prgsbar);
        loadMeme();
    }


    public void shareMeme(View v){
        Intent shareInt = new Intent(Intent.ACTION_SEND);
        shareInt.setType("text/plain");
        shareInt.putExtra(Intent.EXTRA_TEXT, "Hey, Checkout this Amazing Meme from the App Created by Rohan...");
        Intent.createChooser(shareInt, "Share this Meme via..");
        startActivity(shareInt);
    }

    public void nextMeme(View v){
        progressBar.setVisibility(View.VISIBLE);
        loadMeme();
    }

    private void loadMeme() {
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue rq = Volley.newRequestQueue(MainActivity.this);
        String url = "https://meme-api.herokuapp.com/gimme";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
            url, null, response -> {
                try {
                    String memeImgUrl = response.getString("url");
                    Log.d("come", "onResponse: I am tryinng");
                    progressBar.setVisibility(View.GONE);
                    Glide.with(MainActivity.this).load(memeImgUrl).into(imgvw);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }, error -> Log.d("Err", "That Didn't Work"));
        rq.add(jsonObjectRequest);
    }


    @Override
    public void onBackPressed() {
        if(backPrsdTime+2000>System.currentTimeMillis()){
            tst.cancel();
            super.onBackPressed();
            return;
        }else {
            tst = Toast.makeText(this, "Press back again to Exit", Toast.LENGTH_SHORT);
            tst.show();
        }
        backPrsdTime = System.currentTimeMillis();
    }
}