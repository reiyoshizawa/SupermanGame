package com.example.yoshizawarei.supermangame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {

    private BgmPlayer bgm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        TextView highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);

        int score = getIntent().getIntExtra("SCORE", 0);
        scoreLabel.setText(score + "");

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);

        if (score > highScore) {
            highScoreLabel.setText("High Score : " + score);

            // Save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.commit();

        } else {
            highScoreLabel.setText("High Score: " + highScore);
        }

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        intent.putExtra("email",email);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Score");
        // We can also chain the two calls together
        myRef.push().setValue(new UserScore(email, score));
    }

    public void tryAgainLevel1(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("LEVEL", "1");
        int character = getIntent().getIntExtra("character", 0);
        intent.putExtra("character", character);
        startActivity(intent);
    }

    public void tryAgainLevel2(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("LEVEL", "2");
        int character = getIntent().getIntExtra("character", 0);
        intent.putExtra("character", character);
        startActivity(intent);
    }

    public void tryAgainLevel3(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("LEVEL", "3");
        int character = getIntent().getIntExtra("character", 0);
        intent.putExtra("character", character);
        startActivity(intent);
    }

    public void toHistory(View view) {
        Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
        startActivity(intent);
    }
}
