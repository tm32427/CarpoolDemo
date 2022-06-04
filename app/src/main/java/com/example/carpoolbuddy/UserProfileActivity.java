package com.example.carpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        textView = findViewById(R.id.userEmailText);

        textView.setText("Welcome: " + mUser.getEmail());
    }

    public void seeVehicles(View v){
        Intent intent = new Intent(this, VehiclesInfoActivity.class);
        startActivity(intent);
    }

    public void signOut(View v){
        mAuth.signOut();
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }

    public void addVehicles(View v){
        Intent intent = new Intent(this,AddVehicleActivity.class);
        startActivity(intent);
    }


}