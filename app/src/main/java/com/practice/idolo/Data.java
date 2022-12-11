package com.practice.idolo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Data extends AppCompatActivity {

    CardView twitterbutton;
    CardView instagrambutton;
    CardView youtubebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_data);

        twitterbutton = findViewById(R.id.twitter_button);
        instagrambutton = findViewById(R.id.instagram_button);
        youtubebutton = findViewById(R.id.youtube_button);

        Buttons();
    }

    void Buttons() {

        twitterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Data.this, Twitter.class);
                startActivity(intent);

            }
        });

        instagrambutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Data.this, Instagram.class);
                startActivity(intent);
            }
        });

        youtubebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Data.this,Youtube.class);
                startActivity(intent);
            }
        });

    }
}