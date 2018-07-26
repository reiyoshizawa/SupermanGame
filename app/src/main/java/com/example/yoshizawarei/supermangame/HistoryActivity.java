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

public class HistoryActivity extends AppCompatActivity {

    private TextView toHistory;
    private String email;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Score");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    email = (String) dataSnapshot.child("email").getValue();
//                    score = (int) dataSnapshot.child("score").getValue();

                    // このforループで、Todoごとのkey, title, isDoneが取得できているので、
                    // Todoクラスを利用し、Hashmapに追加するなどして保存する。
                }
                // 保存した情報を用いた描画処理などを記載する。
            }


//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
////                String value = dataSnapshot.getValue(String.class);
//                email = dataSnapshot.child("email").getValue();
////                Log.d(TAG, "Value is: " + value);
//            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        toHistory = (TextView) findViewById(R.id.toHistory);
        toHistory.setText(email);
    }
}
