package com.joesoft.gads2020leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);


    }

    @Override
    protected void onResume() {
        super.onResume();
        openLeaderBoard();
    }

    private void openLeaderBoard() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LandingActivity.this, LeaderboardActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
}
}
