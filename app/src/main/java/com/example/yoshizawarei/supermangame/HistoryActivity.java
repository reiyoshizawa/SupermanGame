package com.example.yoshizawarei.supermangame;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ListView mListView;

    private ArrayList<UserScore> arrayList;

    public ArrayList<UserScore> getArrayList() {
        return arrayList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        arrayList = new ArrayList<>();
        mListView = findViewById(R.id.mListView);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Score");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                showData(snapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        mListView.setAdapter(adapter);
    }

    private void showData(DataSnapshot snapshot) {
        for(DataSnapshot ds : snapshot.getChildren()) {
            UserScore userScore = ds.getValue(UserScore.class);
            arrayList.add(userScore);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
            mListView.setAdapter(adapter);
        }
    }
}
