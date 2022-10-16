package com.example.codeatlas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class Profile extends AppCompatActivity {

    Button signOut;
    DatabaseReference reference;
    FirebaseUser user;
    String userID;
    RadioGroup rdGroup,unitGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        // type casting variables
        signOut = findViewById(R.id.bt_SignOut);
        rdGroup = findViewById(R.id.modeRadioGroup);
        unitGroup = findViewById(R.id.unitSelector);
        // initialize navigation View
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);


    }
}