package com.example.yoshizawarei.supermangame;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity{
    private FirebaseAuth mAuth;
    private static final String TAG = "LoginActivity";
    private EditText email_et;
    private EditText pw_et;
    private Button logIn_button;
    private Button signUp_button;
//    private ListView mListView;
//
//
//    private ArrayList<UserScore> arrayList;
//
//    public ArrayList<UserScore> getArrayList() {
//        return arrayList;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        email_et = findViewById(R.id.email_et);
        pw_et = findViewById(R.id.pw_et);
        logIn_button = findViewById(R.id.logIn_button);
        signUp_button = findViewById(R.id.signUp_button);

//        arrayList = new ArrayList<>();
//        mListView = findViewById(R.id.mListView);
//
//
//        // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("Score");
//
//        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                showData(snapshot);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
////                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//
//        // Add a header to the ListView
////        LayoutInflater inflater = getLayoutInflater();
////        ViewGroup history_header = (ViewGroup)inflater.inflate(R.layout.history_header,mListView,false);
////        mListView.addHeaderView(history_header);
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
//        mListView.setAdapter(adapter);
    }

//    private void showData(DataSnapshot snapshot) {
//        for(DataSnapshot ds : snapshot.getChildren()) {
//            UserScore userScore = ds.getValue(UserScore.class);
//            arrayList.add(userScore);
//        }
//    }

    @Override
    protected void onStart() {
        super.onStart();
//        logIn_button.setOnClickListener(this);
    }

    // root ---- User1 -- id
    //                 -- score
    //                 -- ...

    //      ---- User2 -- id
    //                 -- score

//    @Override
//    public void onClick(View v) {
//
//
//    }

    public void logIn(View view) {
        final String email = email_et.getText().toString();
        String password = pw_et.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                            intent.putExtra("email",email);
                            startActivity(intent);
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void signUp(View view) {
        final String email = email_et.getText().toString();
        String password = pw_et.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                            intent.putExtra("email",email);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }
}
