package com.example.yoshizawarei.supermangame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StartActivity extends AppCompatActivity{
    private GridView gridview;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the GridView selected/clicked item text
                int selectedItem = position;
//                System.out.println(selectedItem);

                Intent intent = getIntent();
                String email = intent.getStringExtra("email");
                getIntent().getIntExtra("SCORE", 0);

                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                intent2.putExtra("LEVEL", "1");
                intent2.putExtra("email",email);
                intent2.putExtra("character", position);
                startActivity(intent2);


                // Display the selected/clicked item text and position on TextView
//                tv.setText("GridView item clicked : " +selectedItem
//                        + "\nAt index position : " + position);
            }
        });

    }

//    public void level1Start(View view) {
//        Intent intent = getIntent();
//        String email = intent.getStringExtra("email");
//        System.out.println(email);
//        getIntent().getIntExtra("SCORE", 0);
//
//        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
//        intent2.putExtra("LEVEL", "1");
//        intent2.putExtra("email",email);
//        startActivity(intent2);
//    }
//
    public void level2Start(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("LEVEL", "2");
        startActivity(intent);
    }

    public void level3Start(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("LEVEL", "3");
        startActivity(intent);
    }

}


